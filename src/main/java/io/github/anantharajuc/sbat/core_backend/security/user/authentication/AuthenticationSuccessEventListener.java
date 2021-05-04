package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * Authentication Success Event Listener.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
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
    	log.info("-----> AuthenticationSuccessEventListener");
    	
        final String xfHeader = request.getHeader("X-Forwarded-For");
        
        //LoginAttemptService is notified of the IP address from where the successful attempt originated.
        if (xfHeader == null) 
        {
        	log.info("-----> AuthenticationSuccessEventListener : xfHeader == null");
        	
            loginAttemptService.loginSucceeded(request.getRemoteAddr());
        } 
        else 
        {
        	log.info("-----> AuthenticationSuccessEventListener : xfHeader != null");
        	
            loginAttemptService.loginSucceeded(xfHeader.split(",")[0]);
        }
    }

}