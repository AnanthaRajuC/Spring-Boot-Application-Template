package io.github.anantharajuc.sbat.core_backend.api.rate_limiting;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

/**
 * API Rate Limiting Configuration.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Configuration
@AllArgsConstructor
public class RateLimitingConfiguration implements WebMvcConfigurer
{
	private RateLimitInterceptor interceptor;
	
	@Override 
    public void addInterceptors(InterceptorRegistry registry) 
	{
        registry.addInterceptor(interceptor).addPathPatterns("/api/v1/person/**","/management/api/v1/person/**");
    }
}
