package io.github.anantharajuc.sbat.core_backend.security.jwt;

import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import io.github.anantharajuc.sbat.core_backend.infra.exception.OtherExceptions;
import io.github.anantharajuc.sbat.core_backend.security.SecurityProperties;
import lombok.extern.log4j.Log4j2;

/**
 * JWT signing and verification keys.
 *
 * The RSA key pair is read from the key store configured through {@code sbat.security.jwt.*}. When no key store
 * is configured an ephemeral key pair is generated, so issued tokens are only valid until the next restart.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@Configuration
public class JwtConfiguration
{
	@Bean
	public KeyPair jwtKeyPair(SecurityProperties securityProperties)
	{
		SecurityProperties.Jwt jwt = securityProperties.jwt();

		if (jwt.keyStore() == null)
		{
			log.warn("No JWT key store configured (sbat.security.jwt.key-store), generating an ephemeral RSA key pair. Tokens will not survive a restart.");

			return generateKeyPair();
		}

		return loadKeyPair(jwt);
	}

	@Bean
	public JwtEncoder jwtEncoder(KeyPair jwtKeyPair)
	{
		RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) jwtKeyPair.getPublic())
				.privateKey((RSAPrivateKey) jwtKeyPair.getPrivate())
				.build();

		return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(rsaKey)));
	}

	@Bean
	public JwtDecoder jwtDecoder(KeyPair jwtKeyPair)
	{
		return NimbusJwtDecoder.withPublicKey((RSAPublicKey) jwtKeyPair.getPublic()).build();
	}

	private static KeyPair generateKeyPair()
	{
		try
		{
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);

			return generator.generateKeyPair();
		}
		catch (GeneralSecurityException e)
		{
			throw new OtherExceptions("Unable to generate the JWT key pair", e);
		}
	}

	private static KeyPair loadKeyPair(SecurityProperties.Jwt jwt)
	{
		char[] password = jwt.keyStorePassword() == null ? new char[0] : jwt.keyStorePassword().toCharArray();

		try (InputStream inputStream = jwt.keyStore().getInputStream())
		{
			// KeyStore.getInstance(File, ...) is not usable with classpath resources, PKCS12 also reads JKS files.
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(inputStream, password);

			PrivateKey privateKey = (PrivateKey) keyStore.getKey(jwt.keyAlias(), password);

			if (privateKey == null || keyStore.getCertificate(jwt.keyAlias()) == null)
			{
				throw new OtherExceptions("No key entry named '" + jwt.keyAlias() + "' in the JWT key store");
			}

			return new KeyPair(keyStore.getCertificate(jwt.keyAlias()).getPublicKey(), privateKey);
		}
		catch (Exception e)
		{
			throw new OtherExceptions("Unable to load the JWT key store " + jwt.keyStore(), e);
		}
	}
}
