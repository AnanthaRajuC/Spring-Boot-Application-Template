package io.github.anantharajuc.sbat.core_backend.security.jwt.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.sbat.core_backend.security.SecurityProperties;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshToken;
import io.github.anantharajuc.sbat.core_backend.security.jwt.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService
{
	private final RefreshTokenRepository refreshTokenRepository;
	private final SecurityProperties securityProperties;

	@Override
	public RefreshToken generateRefreshToken(String username)
	{
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setUsername(username);

		return refreshTokenRepository.save(refreshToken);
	}

	@Override
	// Keep the deletion of an expired token when rejecting it.
	@Transactional(noRollbackFor=BadCredentialsException.class)
	public RefreshToken rotateRefreshToken(String token, String expectedUsername)
	{
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new BadCredentialsException("Invalid Refresh Token"));

		if (expectedUsername != null && !expectedUsername.equals(refreshToken.getUsername()))
		{
			log.warn("Refresh token presented for a different username");

			throw new BadCredentialsException("Invalid Refresh Token");
		}

		refreshTokenRepository.delete(refreshToken);

		if (isExpired(refreshToken))
		{
			throw new BadCredentialsException("Refresh Token expired, please login again");
		}

		return generateRefreshToken(refreshToken.getUsername());
	}

	@Override
	public void deleteByToken(String token, String username)
	{
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new BadCredentialsException("Invalid Refresh Token"));

		if (!refreshToken.getUsername().equals(username))
		{
			throw new BadCredentialsException("Token, Username mismatch!");
		}

		refreshTokenRepository.delete(refreshToken);
	}

	private boolean isExpired(RefreshToken refreshToken)
	{
		Instant createdDate = refreshToken.getCreatedDate();

		return createdDate == null || createdDate.plus(securityProperties.refreshTokenValidity()).isBefore(Instant.now());
	}
}
