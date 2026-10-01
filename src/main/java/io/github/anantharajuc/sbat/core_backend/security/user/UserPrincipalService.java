package io.github.anantharajuc.sbat.core_backend.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.github.anantharajuc.sbat.core_backend.infra.exception.FailedLoginAttemptException;
import io.github.anantharajuc.sbat.core_backend.security.user.authentication.LoginAttemptService;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * User Principal Service
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Service
@AllArgsConstructor
public class UserPrincipalService implements UserDetailsService
{
	private final UserRepository appUserRepository;
	private final LoginAttemptService loginAttemptService;

	/**
	 * Used for password based authentication (form login, HTTP Basic, API login, remember-me). Rejects clients
	 * whose IP address is blocked after too many failed login attempts.
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
	{
		String ip = currentClientIp();

		if (ip != null && loginAttemptService.isBlocked(ip))
		{
			log.info("-----> Login rejected, {} is blocked after too many failed login attempts.", ip);

			throw new FailedLoginAttemptException();
		}

		return loadUserForToken(username);
	}

	/**
	 * Used for already verified credentials, such as the subject of a signed JWT.
	 */
	public UserDetails loadUserForToken(String username)
	{
		User user = appUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

		return new UserPrincipal(user);
	}

	private static String currentClientIp()
	{
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

		if (attributes instanceof ServletRequestAttributes servletRequestAttributes)
		{
			// The remote address honours X-Forwarded-For only when server.forward-headers-strategy is enabled,
			// so clients cannot spoof it to dodge or trigger the block.
			return servletRequestAttributes.getRequest().getRemoteAddr();
		}

		return null;
	}
}
