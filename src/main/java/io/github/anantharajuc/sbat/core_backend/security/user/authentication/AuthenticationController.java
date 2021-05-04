package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import io.github.anantharajuc.sbat.core_backend.api.ResourcePaths;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.AuthenticationResponse;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshToken;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshTokenDTO;
import io.github.anantharajuc.sbat.core_backend.security.jwt.service.RefreshTokenServiceImpl;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserLogin;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserSignup;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
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
public class AuthenticationController 
{
	private final AuthenticationServiceImpl authenticationServiceImpl;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;
	private final ModelMapper modelMapper;
	
	@PostMapping(value=ResourcePaths.Authentication.V1.SIGNUP)
    public ResponseEntity<String> signup(@RequestBody UserSignup userSignup) 
	{
		authenticationServiceImpl.signup(userSignup);
		
        return ResponseEntity.status(OK).body("User Registration Successful! Activate your account by following the instructions in the verification email.");
    }
	
	@GetMapping(value=ResourcePaths.Authentication.V1.VERIFICATION)
    public ResponseEntity<String> verifyAccount(@PathVariable String token) 
	{
        return new ResponseEntity<>(authenticationServiceImpl.verifyAccount(token), OK);
    } 
	
	@PostMapping(value=ResourcePaths.Authentication.V1.LOGIN)
    public AuthenticationResponse login(@RequestBody UserLogin userLogin) 
	{
		return authenticationServiceImpl.login(userLogin);
    }
	
	@PostMapping(value=ResourcePaths.Authentication.V1.REFRESH_TOKEN)
	public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) 
	{
		RefreshToken refreshToken;
		refreshToken = modelMapper.map(refreshTokenDTO, RefreshToken.class);
		
		return authenticationServiceImpl.refreshToken(refreshToken);
	}
	
	@PostMapping(value=ResourcePaths.Authentication.V1.LOGOUT)
	public ResponseEntity<String> logout(@RequestBody RefreshTokenDTO refreshTokenDTO)
	{
		refreshTokenServiceImpl.deleteByToken(refreshTokenDTO.getToken(), refreshTokenDTO.getUsername());
		
		return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully.");
	}
}
