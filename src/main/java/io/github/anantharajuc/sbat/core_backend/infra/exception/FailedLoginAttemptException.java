package io.github.anantharajuc.sbat.core_backend.infra.exception;

import org.springframework.security.authentication.LockedException;

/**
 * Failed Login Attempt Exception, thrown while the client is blocked after too many failed login attempts.
 *
 * It is an {@link org.springframework.security.core.AuthenticationException}, so it results in a regular
 * authentication failure (401 / login error page) rather than a server error.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
public class FailedLoginAttemptException extends LockedException
{
	private static final long serialVersionUID = 1L;

	public FailedLoginAttemptException()
	{
		 super("blocked");
	}
}
