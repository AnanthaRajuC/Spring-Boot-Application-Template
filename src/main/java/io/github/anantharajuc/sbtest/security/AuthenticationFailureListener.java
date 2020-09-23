package io.github.anantharajuc.sbtest.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> 
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) 
    {  	
    	log.info("-----> AuthenticationFailureListener : onApplicationEvent");
    	
    	log.info(request.getRequestURI());
    	log.info(request.getCharacterEncoding());
    	log.info(request.getMethod());
    	log.info(request.getProtocol());
    	log.info(request.getLocale());
    	log.info(request.getLocalPort());
    	log.info(request.getServerPort());
    	
        final String xfHeader = request.getHeader("X-Forwarded-For");
        
        if (xfHeader == null) 
        {
        	log.info("-----> AuthenticationFailureListener xfHeader == null : ");
        	
            loginAttemptService.loginFailed(request.getRemoteAddr());
        } 
        else 
        {
        	log.info("-----> AuthenticationFailureListener xfHeader ! = null : ");
        	
            loginAttemptService.loginFailed(xfHeader.split(",")[0]);
        }
    }
}