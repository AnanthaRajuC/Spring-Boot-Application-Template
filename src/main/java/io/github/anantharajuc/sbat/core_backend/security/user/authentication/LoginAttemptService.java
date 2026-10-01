package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import io.github.anantharajuc.sbat.core_backend.security.SecurityProperties;
import lombok.extern.log4j.Log4j2;

/**
 * Login Attempt Service
 *
 * Counts failed login attempts per client IP address. Once {@code sbat.security.max-login-attempts} is reached
 * the IP address is blocked until {@code sbat.security.login-block-duration} has passed since the last failure.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Service
public class LoginAttemptService
{
	private final int maxAttempts;

	//The number of wrong attempts per IP address is stored in this cache
	private final Cache<String, AtomicInteger> attemptsCache;

	public LoginAttemptService(SecurityProperties securityProperties)
	{
		this.maxAttempts = securityProperties.maxLoginAttempts();
		this.attemptsCache = Caffeine.newBuilder()
				.expireAfterWrite(securityProperties.loginBlockDuration())
				.maximumSize(100_000)
				.build();
	}

	//Successful authentication resets the unsuccessful login attempts counter
	public void loginSucceeded(final String key)
	{
		attemptsCache.invalidate(key);
	}

	//Unsuccessful authentication attempt increases the number of attempts for that IP
	public void loginFailed(final String key)
	{
		int attempts = attemptsCache.asMap().compute(key, (k, v) -> v == null ? new AtomicInteger(1) : new AtomicInteger(v.get() + 1)).get();

		log.info("Unsuccessful Login Attempt from {} : {}/{}", key, attempts, maxAttempts);
	}

	public boolean isBlocked(final String key)
	{
		AtomicInteger attempts = attemptsCache.getIfPresent(key);

		return attempts != null && attempts.get() >= maxAttempts;
	}
}
