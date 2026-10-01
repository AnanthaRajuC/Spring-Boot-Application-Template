package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.AllArgsConstructor;

/**
 * Authentication Success Event Listener.
 *
 * Notifies the {@link LoginAttemptService} of the client IP address the attempt originated from. The remote
 * address honours X-Forwarded-For only when {@code server.forward-headers-strategy} is enabled.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Component
@AllArgsConstructor
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent>
{
    private final LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e)
    {
        // A bearer token is not a login attempt, it must not reset the failed login counter.
        if (e.getAuthentication().getCredentials() instanceof Jwt)
        {
            return;
        }

        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes attributes)
        {
            loginAttemptService.loginSucceeded(attributes.getRequest().getRemoteAddr());
        }
    }
}
