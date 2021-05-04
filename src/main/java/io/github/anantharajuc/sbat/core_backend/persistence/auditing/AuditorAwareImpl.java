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
		
		String username = loggedInUser.getName();
		
		return Optional.of(username);
	}
}
