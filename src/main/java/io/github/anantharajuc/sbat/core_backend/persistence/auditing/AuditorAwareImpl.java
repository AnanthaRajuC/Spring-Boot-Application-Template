package io.github.anantharajuc.sbat.core_backend.persistence.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Auditor Aware Implementation.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
public class AuditorAwareImpl implements AuditorAware<String>
{
	@Override
	public Optional<String> getCurrentAuditor() 
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		
		// Changes made outside of a request, e.g. at startup or in @Async methods, have no authentication.
		if (loggedInUser == null || loggedInUser.getName() == null)
		{
			return Optional.of("system");
		}

		return Optional.of(loggedInUser.getName());
	}
}
