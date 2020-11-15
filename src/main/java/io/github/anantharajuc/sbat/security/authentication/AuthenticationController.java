package io.github.anantharajuc.sbat.security.authentication;

import io.github.anantharajuc.sbat.api.ResourcePaths;
import io.github.anantharajuc.sbat.security.jwt.model.AuthenticationResponse;
import io.github.anantharajuc.sbat.security.jwt.model.RefreshToken;
import io.github.anantharajuc.sbat.security.jwt.service.RefreshTokenServiceImpl;
import io.github.anantharajuc.sbat.security.user.model.UserLogin;
import io.github.anantharajuc.sbat.security.user.model.UserSignup;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

@RestController
@RequestMapping(value=ResourcePaths.Authentication.V1.ROOT)
@AllArgsConstructor
@Log4j2
public class AuthenticationController 
{
	private final AuthenticationServiceImpl authServiceImpl;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;
	
	@PostMapping(value=ResourcePaths.Authentication.V1.SIGNUP)
    public ResponseEntity<String> signup(@RequestBody UserSignup userSignup) 
	{
		log.info("-----> AuthenticationController");
		
		authServiceImpl.signup(userSignup);
		
        return ResponseEntity.status(OK).body("User Registration Successful! Activate your account by following the instructions in the verification email.");
    }
	
	@GetMapping(value=ResourcePaths.Authentication.V1.VERIFICATION)
    public ResponseEntity<String> verifyAccount(@PathVariable String token) 
	{
        return new ResponseEntity<>(authServiceImpl.verifyAccount(token), OK);
    } 
	
	@PostMapping(value=ResourcePaths.Authentication.V1.LOGIN)
    public AuthenticationResponse login(@RequestBody UserLogin userLogin) 
	{
		return authServiceImpl.login(userLogin);
    }
	
	@PostMapping(value=ResourcePaths.Authentication.V1.REFRESH_TOKEN)
	public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshToken refreshToken) 
	{
		return authServiceImpl.refreshToken(refreshToken);
	}
	
	@PostMapping(value=ResourcePaths.Authentication.V1.LOGOUT)
	public ResponseEntity<String> logout(@RequestBody RefreshToken refreshToken)
	{
		refreshTokenServiceImpl.deleteByToken(refreshToken.getToken(), refreshToken.getUsername());
		
		return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully.");
	}
}
