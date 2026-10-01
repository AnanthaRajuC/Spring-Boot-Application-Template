package io.github.anantharajuc.sbat.core_backend.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import io.github.anantharajuc.sbat.core_backend.security.user.UserPrincipalService;
import lombok.AllArgsConstructor;

/**
 * Turns a verified JWT into an authentication backed by the current {@link UserDetails} of its subject, so that
 * roles, permissions and account status always come from the database and expressions such as
 * {@code authentication.principal.username} work the same as for form login.
 */
@Component
@AllArgsConstructor
public class JwtUserAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>
{
	private final UserPrincipalService userPrincipalService;
	private final AccountStatusUserDetailsChecker accountStatusChecker = new AccountStatusUserDetailsChecker();

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt)
	{
		UserDetails userDetails = userPrincipalService.loadUserForToken(jwt.getSubject());

		accountStatusChecker.check(userDetails);

		return UsernamePasswordAuthenticationToken.authenticated(userDetails, jwt, userDetails.getAuthorities());
	}
}
