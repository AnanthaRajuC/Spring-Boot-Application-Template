package io.github.anantharajuc.sbat.core_backend.security.jwt;

import java.time.Instant;

import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.core_backend.security.SecurityProperties;
import io.github.anantharajuc.sbat.core_backend.service.impl.OtherServicesImpl;
import lombok.AllArgsConstructor;

/**
 * Issues RS256 signed access tokens. Tokens are verified by Spring Security's OAuth2 resource server support,
 * see {@link io.github.anantharajuc.sbat.core_backend.security.ApplicationSecurityConfiguration}.
 */
@Service
@AllArgsConstructor
public class JwtProvider
{
	private final JwtEncoder jwtEncoder;
	private final OtherServicesImpl otherServicesImpl;
	private final SecurityProperties securityProperties;

	/**
	 * @param username subject of the token
	 * @return the signed token together with its expiry
	 */
	public IssuedToken generateTokenWithUserName(String username)
	{
		Instant now = Instant.now();
		Instant expiresAt = now.plusSeconds(otherServicesImpl.getJwtExpirationTime());

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer(securityProperties.jwt().issuer())
				.subject(username)
				.issuedAt(now)
				.expiresAt(expiresAt)
				.build();

		String token = jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.with(SignatureAlgorithm.RS256).build(), claims)).getTokenValue();

		return new IssuedToken(token, expiresAt);
	}

	public record IssuedToken(String token, Instant expiresAt)
	{
	}
}
