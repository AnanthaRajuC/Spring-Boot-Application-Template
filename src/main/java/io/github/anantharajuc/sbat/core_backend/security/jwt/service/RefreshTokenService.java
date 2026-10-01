package io.github.anantharajuc.sbat.core_backend.security.jwt.service;

import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshToken;

public interface RefreshTokenService
{
	/** Creates a new refresh token for the given user. */
	RefreshToken generateRefreshToken(String username);

	/**
	 * Validates a refresh token and replaces it with a new one (rotation), so every refresh token can be used once.
	 *
	 * @param token            refresh token presented by the client
	 * @param expectedUsername username sent by the client, may be {@code null}
	 * @return the new refresh token, whose username is the owner of the presented token
	 */
	RefreshToken rotateRefreshToken(String token, String expectedUsername);

	void deleteByToken(String token, String username);
}
