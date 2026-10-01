package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import io.github.anantharajuc.sbat.core_backend.security.jwt.model.AuthenticationResponse;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshTokenDTO;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.VerificationToken;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserLogin;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserSignup;

public interface AuthenticationService
{
	void signup(UserSignup userSignup);

	void fetchUserAndEnable(VerificationToken verificationToken);

	String verifyAccount(String token);

	String generateVerificationToken(User user);

	AuthenticationResponse login(UserLogin userLogin);

	AuthenticationResponse refreshToken(RefreshTokenDTO refreshTokenDTO);
}
