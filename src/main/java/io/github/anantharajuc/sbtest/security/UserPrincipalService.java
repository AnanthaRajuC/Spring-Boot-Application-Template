package io.github.anantharajuc.sbtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.authentication.User;
import io.github.anantharajuc.sbtest.authentication.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserPrincipalService implements UserDetailsService
{
	@Autowired
	UserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
	{
		log.info("-----> loadUserByUsername  : "+username);
		
		User user = appUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
		
		return new UserPrincipal(user);
	}
}
