package io.github.anantharajuc.sbtest.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import static io.github.anantharajuc.sbtest.security.ApplicationUserRole.*;

@Log4j2
@Service
public class ApplicationUserService implements UserDetailsService
{	
	@Autowired
	AppUserRepository appUserRepository;
	
	Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public UserDetails loadUserByUsername(String username)
	{	
		log.info("-----> loadUserByUsername : "+username);
		
		AppUser appuser = appUserRepository.findByUsername(username)
								.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

		
		if(appuser.getGrantedAuthorities().equals(PERSON.toString())) 
		{			
			authorities = PERSON.getGrantedAuthorities();
			
			log.info("-----> PERSON.getGrantedAuthorities() : "+authorities);
		}
		else if(appuser.getGrantedAuthorities().equals(ADMIN.toString()))
		{
			authorities = ADMIN.getGrantedAuthorities();
			
			log.info("-----> ADMIN.getGrantedAuthorities() : "+authorities);
		}
		else if(appuser.getGrantedAuthorities().equals(ADMINTRAINEE.toString()))
		{
			authorities = ADMINTRAINEE.getGrantedAuthorities();
			
			log.info("-----> ADMINTRAINEE.getGrantedAuthorities() : "+authorities);
		}

		return new UserDetails() 
		{
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() 
			{
				return appuser.isEnabled();
			}
			
			@Override
			public boolean isCredentialsNonExpired() 
			{
				return appuser.isCredentialsNonExpired();
			}
			
			@Override
			public boolean isAccountNonLocked() 
			{
				return appuser.isAccountNonLocked();
			}
			
			@Override
			public boolean isAccountNonExpired() 
			{
				return appuser.isAccountNonExpired();
			}
			
			@Override
			public String getUsername() 
			{
				return appuser.getUsername();
			}
			
			@Override
			public String getPassword() 
			{
				return appuser.getPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() 
			{										
					return authorities;	
			}
		};		
	}
} 