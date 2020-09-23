package io.github.anantharajuc.sbtest.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> 
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) 
    {
    	log.info("-----> -----> Login Success : ");
    	
    	log.info(request.getRequestURI());
    	log.info(request.getCharacterEncoding());
    	log.info(request.getMethod());
    	log.info(request.getProtocol());
    	log.info(request.getLocale());
    	log.info(request.getLocalPort());
    	log.info(request.getServerPort());
    	log.info(request.getUserPrincipal());
    	
        final String xfHeader = request.getHeader("X-Forwarded-For");
        
        if (xfHeader == null) 
        {
        	log.info("-----> AuthenticationSuccessEventListener : xfHeader == null ");
        	
            loginAttemptService.loginSucceeded(request.getRemoteAddr());
        } 
        else 
        {
        	log.info("-----> AuthenticationSuccessEventListener : xfHeader != null ");
        	
            loginAttemptService.loginSucceeded(xfHeader.split(",")[0]);
        }
    }

}