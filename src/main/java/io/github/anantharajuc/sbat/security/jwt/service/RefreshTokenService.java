package io.github.anantharajuc.sbat.security.jwt.service;

import io.github.anantharajuc.sbat.security.jwt.model.RefreshToken;

public interface RefreshTokenService 
{
	RefreshToken generateRefreshToken(String stage, String username);
	
	void validateRefreshToken(String token);
	
	void deleteByToken(String token, String username);
}