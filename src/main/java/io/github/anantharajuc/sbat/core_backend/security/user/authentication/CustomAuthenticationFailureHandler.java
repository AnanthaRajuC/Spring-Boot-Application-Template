package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.log4j.Log4j2;

/**
 * Custom Authentication Failure Handler
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler 
{

    @Autowired
    private MessageSource messages;

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception)  
    {
        setDefaultFailureUrl("/login?error=true");

        try 
        {
			super.onAuthenticationFailure(request, response, exception);
		} 
        catch (IOException | ServletException e) 
        {
			log.error("onAuthenticationFailure",e);
		}

        final Locale locale = localeResolver.resolveLocale(request);

        String errorMessage = messages.getMessage("message.badCredentials", null, locale);

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) 
        {
            errorMessage = messages.getMessage("auth.message.disabled", null, locale);
        } 
        else if (exception.getMessage().equalsIgnoreCase("User account has expired")) 
        {
            errorMessage = messages.getMessage("auth.message.expired", null, locale);
        } 
        else if (exception.getMessage().equalsIgnoreCase("blocked")) 
        {
            errorMessage = messages.getMessage("auth.message.blocked", null, locale);
        } 
        else if (exception.getMessage().equalsIgnoreCase("unusual location")) 
        {
            errorMessage = messages.getMessage("auth.message.unusual.location", null, locale);
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}