package io.github.anantharajuc.sbat.core_backend.infra.exception;

/**
 * Failed Login Attempt Exception
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
public class FailedLoginAttemptException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public FailedLoginAttemptException()
	{
		 super("Failed login attempt...");
	}
}
