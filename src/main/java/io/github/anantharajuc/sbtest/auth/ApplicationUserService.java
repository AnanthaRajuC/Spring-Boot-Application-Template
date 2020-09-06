package io.github.anantharajuc.sbtest.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.security.Role;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ApplicationUserService implements UserDetailsService
{	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
	{	
		log.info("-----> username            : "+username);

		AppUser appuser = appUserRepository.findByUsername(username)
								.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

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
				Set<String> roleAndPermissions = new HashSet<>();
				
				List<Role> roles = appuser.getRoles();
				
				for (Role role : roles)
				{
					roleAndPermissions.add("ROLE_" + role.getName());
					
					log.info("-----> Role                : "+role.getName());
					
					for(int i = 0; i<role.getPermissions().size(); i++)
					{
						log.info("-----> Permission          : "+role.getPermissions().get(i).getName());
						
						roleAndPermissions.add(role.getPermissions().get(i).getName());
					}
				}
				
				String[] roleNames = new String[roleAndPermissions.size()];
				
				log.info("-----> Roles & Permissions : "+roleAndPermissions.toString());			
				
				Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
				
				return authorities;	
			}
		};		
	}
} 