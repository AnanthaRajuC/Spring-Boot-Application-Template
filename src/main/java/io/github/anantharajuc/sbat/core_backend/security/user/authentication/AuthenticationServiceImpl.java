package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.sbat.core_backend.email.Email;
import io.github.anantharajuc.sbat.core_backend.email.EmailServiceImpl;
import io.github.anantharajuc.sbat.core_backend.infra.exception.OtherExceptions;
import io.github.anantharajuc.sbat.core_backend.security.jwt.JwtProvider;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.AuthenticationResponse;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshToken;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.VerificationToken;
import io.github.anantharajuc.sbat.core_backend.security.jwt.model.VerificationTokenEnum;
import io.github.anantharajuc.sbat.core_backend.security.jwt.repository.VerificationTokenRepository;
import io.github.anantharajuc.sbat.core_backend.security.jwt.service.RefreshTokenServiceImpl;
import io.github.anantharajuc.sbat.core_backend.security.user.model.Permission;
import io.github.anantharajuc.sbat.core_backend.security.user.model.Role;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserLogin;
import io.github.anantharajuc.sbat.core_backend.security.user.model.UserSignup;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import io.github.anantharajuc.sbat.core_backend.service.impl.OtherServicesImpl;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService
{
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailServiceImpl mailServiceImpl;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;
	private final OtherServicesImpl otherServicesImpl;
	
	public AuthenticationServiceImpl(ModelMapper modelMapper, 
			 UserRepository userRepository, 
			 PasswordEncoder passwordEncoder, 
			 VerificationTokenRepository verificationTokenRepository, 
			 EmailServiceImpl mailServiceImpl,
			 RefreshTokenServiceImpl refreshTokenServiceImpl,
			 OtherServicesImpl otherServicesImpl) 
	{
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailServiceImpl = mailServiceImpl;
		this.refreshTokenServiceImpl = refreshTokenServiceImpl;
		this.otherServicesImpl = otherServicesImpl;
	}
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${mail.body}")
	private String mailBody;

	@Override
	public void signup(UserSignup userSignup) 
	{
		log.info("-----> AuthenticationServiceImpl signup");
		
		otherServicesImpl.loadApplicationSettings();
		
		User user;
		
		user = modelMapper.map(userSignup, User.class);
		
		user.setPassword(passwordEncoder.encode(userSignup.getPassword())); 
		user.setEnabled(false); 
		user.setAccountNonExpired(false);
		user.setAccountNonLocked(false);
		user.setCredentialsNonExpired(false);
		
		Role role = new Role();
		role.setName("ROLE_PERSON");
		
		List<Role> list=new ArrayList<>();  
		list.add(role);

		Permission permission = new Permission();
		permission.setName("PERSON_READ");
		List<Permission> permissions=new ArrayList<>(); 
		permissions.add(permission);

		role.setPermissions(permissions);
		
		user.setRoles(list);

		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		
		mailServiceImpl.sendMail(new Email(otherServicesImpl.getMailSubject(), user.getEmail(), mailBody+token+" This link is valid for the next "+otherServicesImpl.getVerificationTokenValidity().toString()+" minute.",otherServicesImpl.getMailReplyTo()));		
	}

	@Override
	public void fetchUserAndEnable(VerificationToken verificationToken) 
	{
		String username = verificationToken.getUser().getUsername();
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new OtherExceptions("User does not exist"));

		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		
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
			verificationTokenRepository.deleteById(verificationToken.get().getId()); 
			userRepository.deleteById(verificationToken.get().getId());
			
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
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);

		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenServiceImpl.generateRefreshToken("LOGIN",userLogin.getUsername()).getToken()) 
				.expiresAt(Instant.now().plusMillis(otherServicesImpl.getJwtExpirationTime()))
				.username(userLogin.getUsername())
				.build();
	}

	@Override
	public AuthenticationResponse refreshToken(RefreshToken refreshToken) 
	{
		refreshTokenServiceImpl.validateRefreshToken(refreshToken.getToken());

		String token = jwtProvider.generateTokenWithUserName(refreshToken.getUsername());
		
		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenServiceImpl.generateRefreshToken("POST_LOGIN",refreshToken.getUsername()).getToken()) 
				.expiresAt(Instant.now().plusMillis(otherServicesImpl.getJwtExpirationTime()))
				.username(refreshToken.getUsername())
				.build();
	}
}
