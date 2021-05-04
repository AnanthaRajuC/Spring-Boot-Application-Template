package io.github.anantharajuc.sbat.core_backend.api.rate_limiting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.anantharajuc.sbat.core_backend.api.APIutil;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.extern.log4j.Log4j2;

/**
 * API Rate Limit Interceptor
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@Component
public class RateLimitInterceptor implements HandlerInterceptor
{
	private APIUsageTiersService pricingPlanService;
	
	@Autowired
    public RateLimitInterceptor(APIUsageTiersService pricingPlanService) 
	{
    	this.pricingPlanService = pricingPlanService;
	}
	
	/**
     * @see HandlerInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
    {       
    	String apiKey = request.getHeader(APIutil.HEADER_API_KEY);
    	
        if (apiKey == null || apiKey.isEmpty()) 
        {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header: " + APIutil.HEADER_API_KEY);

            log.error("Missing Header: " + APIutil.HEADER_API_KEY);
            
            return false;
        }

        Bucket tokenBucket = pricingPlanService.resolveBucket(apiKey);
        
        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) 
        {
            response.addHeader(APIutil.HEADER_LIMIT_REMAINING, String.valueOf(probe.getRemainingTokens()));
            
            log.info("Remaining API requests count : "+probe.getRemainingTokens());
            
            return true;
        } 
        else 
        {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.addHeader(APIutil.HEADER_RETRY_AFTER, String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "You have exhausted your API Request Quota. Check Rate Limiting details in response headers."); // 429
            
            log.info("You have exhausted your API Request Quota. Check Rate Limiting details in response headers.");

            return false;
        }
    }
}
