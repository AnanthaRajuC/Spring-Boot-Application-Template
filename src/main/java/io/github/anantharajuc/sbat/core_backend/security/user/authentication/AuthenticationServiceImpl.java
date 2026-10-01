package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.sbat.core_backend.email.Email;
import io.github.anantharajuc.sbat.core_backend.email.EmailServiceImpl;
import io.github.anantharajuc.sbat.core_backend.infra.exception.OtherExceptions;
import io.github.anantharajuc.sbat.core_backend.security.jwt.JwtProvider;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.AuthenticationResponse;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshToken;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshTokenDTO;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.VerificationToken;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.VerificationTokenEnum;
import io.github.anantharajuc.sbat.core_backend.security.jwt.repository.VerificationTokenRepository;
import io.github.anantharajuc.sbat.core_backend.security.jwt.service.RefreshTokenServiceImpl;
import io.github.anantharajuc.sbat.core_backend.security.user.model.Role;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserLogin;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserSignup;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.RoleRepository;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import io.github.anantharajuc.sbat.core_backend.service.impl.OtherServicesImpl;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService
{
	/** Role given to self-registered users. */
	private static final String DEFAULT_ROLE = "ROLE_PERSON";

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailServiceImpl mailServiceImpl;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;
	private final OtherServicesImpl otherServicesImpl;
	private final JwtProvider jwtProvider;
	private final AuthenticationManager authenticationManager;
	private final String mailBody;

	public AuthenticationServiceImpl(UserRepository userRepository,
									 RoleRepository roleRepository,
									 PasswordEncoder passwordEncoder,
									 VerificationTokenRepository verificationTokenRepository,
									 EmailServiceImpl mailServiceImpl,
									 RefreshTokenServiceImpl refreshTokenServiceImpl,
									 OtherServicesImpl otherServicesImpl,
									 JwtProvider jwtProvider,
									 @Lazy AuthenticationManager authenticationManager,
									 @Value("${mail.body}") String mailBody)
	{
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailServiceImpl = mailServiceImpl;
		this.refreshTokenServiceImpl = refreshTokenServiceImpl;
		this.otherServicesImpl = otherServicesImpl;
		this.jwtProvider = jwtProvider;
		this.authenticationManager = authenticationManager;
		this.mailBody = mailBody;
	}

	@Override
	@Transactional
	public void signup(UserSignup userSignup)
	{
		log.info("-----> AuthenticationServiceImpl signup");

		if (userRepository.existsByUsernameOrEmail(userSignup.getUsername(), userSignup.getEmail()))
		{
			throw new OtherExceptions("Username or email is already registered.");
		}

		Role role = roleRepository.findByName(DEFAULT_ROLE).orElseThrow(() -> new OtherExceptions(DEFAULT_ROLE + " is not configured"));

		User user = new User();
		user.setUsername(userSignup.getUsername());
		user.setEmail(userSignup.getEmail());
		user.setPassword(passwordEncoder.encode(userSignup.getPassword()));
		// The account stays disabled until the e-mail address is verified.
		user.setEnabled(false);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setRoles(new ArrayList<>(List.of(role)));

		userRepository.save(user);

		String token = generateVerificationToken(user);

		mailServiceImpl.sendMail(new Email(otherServicesImpl.getMailSubject(), user.getEmail(), mailBody+token+" This link is valid for the next "+otherServicesImpl.getVerificationTokenValidity().toString()+" minutes.",otherServicesImpl.getMailReplyTo()));
	}

	@Override
	public void fetchUserAndEnable(VerificationToken verificationToken)
	{
		String username = verificationToken.getUser().getUsername();

		User user = userRepository.findByUsername(username).orElseThrow(() -> new OtherExceptions("User does not exist"));

		user.setEnabled(true);

		userRepository.save(user);
	}

	@Override
	@Transactional
	public String verifyAccount(String token)
	{
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);

		if(verificationToken.isPresent() && verificationToken.get().getStatus().equals(VerificationTokenEnum.UNVERIFIED) &&Instant.now().isBefore(verificationToken.get().getExpiryDate()))
		{
			verificationToken.get().setStatus(VerificationTokenEnum.VERIFIED);

			verificationTokenRepository.save(verificationToken.get());

			fetchUserAndEnable(verificationToken.get());
		}
		else if(verificationToken.isPresent() && verificationToken.get().getStatus().equals(VerificationTokenEnum.VERIFIED))
		{
			return "Account already verified.";
		}
		else if(!verificationToken.isPresent())
		{
			return "invalid verification token, please check the verification link.";
		}
		else
		{
			// Expired: remove the token and the never activated account so the user can register again.
			User user = verificationToken.get().getUser();

			verificationTokenRepository.delete(verificationToken.get());

			if (user != null && !user.isEnabled())
			{
				userRepository.delete(user);
			}

			return "token expired! Please register again.";
		}

		return "Account Activated Successfully. Login to the application to start using it.";
	}

	@Override
	public String generateVerificationToken(User user)
	{
		String token = UUID.randomUUID().toString();

		VerificationToken verificationToken = new VerificationToken();

		verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(Instant.now().plus(otherServicesImpl.getVerificationTokenValidity(), ChronoUnit.MINUTES));
        verificationToken.setStatus(VerificationTokenEnum.UNVERIFIED);

        verificationTokenRepository.save(verificationToken);

		return token;
	}

	@Override
	public AuthenticationResponse login(UserLogin userLogin)
	{
		Authentication authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(userLogin.getUsername(), userLogin.getPassword()));

		return issueTokens(authentication.getName(), refreshTokenServiceImpl.generateRefreshToken(authentication.getName()));
	}

	@Override
	public AuthenticationResponse refreshToken(RefreshTokenDTO refreshTokenDTO)
	{
		// The new access token is issued for the owner of the stored refresh token, never for a client supplied username.
		RefreshToken refreshToken = refreshTokenServiceImpl.rotateRefreshToken(refreshTokenDTO.getToken(), refreshTokenDTO.getUsername());

		return issueTokens(refreshToken.getUsername(), refreshToken);
	}

	private AuthenticationResponse issueTokens(String username, RefreshToken refreshToken)
	{
		JwtProvider.IssuedToken accessToken = jwtProvider.generateTokenWithUserName(username);

		return AuthenticationResponse.builder()
				.authenticationToken(accessToken.token())
				.refreshToken(refreshToken.getToken())
				.expiresAt(accessToken.expiresAt())
				.username(username)
				.build();
	}
}
