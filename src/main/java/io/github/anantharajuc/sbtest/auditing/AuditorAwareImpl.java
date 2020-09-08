package io.github.anantharajuc.sbtest.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
