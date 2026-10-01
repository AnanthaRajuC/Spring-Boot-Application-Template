package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.AllArgsConstructor;

/**
 * Authentication Failure Event Listener.
 *
 * Notifies the {@link LoginAttemptService} of the client IP address the attempt originated from. The remote
 * address honours X-Forwarded-For only when {@code server.forward-headers-strategy} is enabled.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Component
@AllArgsConstructor
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>
{
    private final LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e)
    {
        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes attributes)
        {
            loginAttemptService.loginFailed(attributes.getRequest().getRemoteAddr());
        }
    }
}
