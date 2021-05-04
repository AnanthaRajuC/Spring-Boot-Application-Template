package io.github.anantharajuc.sbat.core_backend.security.jwt.service;

import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshToken;

public interface RefreshTokenService 
{
	RefreshToken generateRefreshToken(String stage, String username);
	
	void validateRefreshToken(String token);
	
	void deleteByToken(String token, String username);
}