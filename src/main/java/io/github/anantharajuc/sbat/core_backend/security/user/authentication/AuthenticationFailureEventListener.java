package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * Authentication Failure Event Listener.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> 
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) 
    {  	
    	log.info("-----> AuthenticationFailureListener");
    	
        final String xfHeader = request.getHeader("X-Forwarded-For");
        
        //LoginAttemptService is notified of the IP address from where the unsuccessful attempt originated.
        if (xfHeader == null) 
        {
        	log.info("-----> AuthenticationFailureListener xfHeader == null");
        	
            loginAttemptService.loginFailed(request.getRemoteAddr());
        } 
        else 
        {
        	log.info("-----> AuthenticationFailureListener xfHeader != null");
        	
            loginAttemptService.loginFailed(xfHeader.split(",")[0]);
        }
    }
}