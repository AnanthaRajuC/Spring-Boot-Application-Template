package io.github.anantharajuc.sbat.core_backend.security;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.Resource;

/**
 * Security settings bound from the {@code sbat.security.*} properties.
 *
 * @param jwt                    JWT signing key settings
 * @param refreshTokenValidity   how long a refresh token can be exchanged for a new access token
 * @param rememberMeKey          secret used to sign remember-me cookies, random per start when blank
 * @param maxLoginAttempts       failed login attempts allowed per client IP before it is blocked
 * @param loginBlockDuration     how long a client IP stays blocked
 */
@ConfigurationProperties("sbat.security")
public record SecurityProperties(
		@DefaultValue Jwt jwt,
		@DefaultValue("7d") Duration refreshTokenValidity,
		String rememberMeKey,
		@DefaultValue("5") int maxLoginAttempts,
		@DefaultValue("15m") Duration loginBlockDuration)
{
	/**
	 * @param keyStore         PKCS12 or JKS key store holding the RSA signing key pair, ephemeral key pair when unset
	 * @param keyStorePassword password of the key store and of the key entry
	 * @param keyAlias         alias of the key entry
	 * @param issuer           value of the {@code iss} claim
	 */
	public record Jwt(Resource keyStore, String keyStorePassword, @DefaultValue("sbat") String keyAlias, @DefaultValue("sbat") String issuer)
	{
	}
}
