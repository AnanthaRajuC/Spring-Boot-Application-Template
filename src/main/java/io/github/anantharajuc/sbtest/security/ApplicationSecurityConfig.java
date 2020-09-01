package io.github.anantharajuc.sbtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static io.github.anantharajuc.sbtest.security.ApplicationUserRole.*;
import static io.github.anantharajuc.sbtest.security.ApplicationUserPermission.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{
	private final PasswordEncoder passwordEncoder;

	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder)
	{
		this.passwordEncoder = passwordEncoder;
	}
	
	/** Public URLs. */
    private static final String[] PUBLIC_MATCHERS = 
    {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/sbat/index/**",
            "/sbat/error/**",
            "/lang"
    };	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.csrf().disable()
		.authorizeRequests()
		
			.antMatchers(PUBLIC_MATCHERS).permitAll()		
			
			.antMatchers("/api/**").hasRole(PERSON.name())	
			
			.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
			.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(PERSON_UPDATE.getPermission())	
			.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(PERSON_CREATE.getPermission())	
			.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(PERSON_DELETE.getPermission())		
			
			.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/sbat/login")
				.defaultSuccessUrl("/sbat/index")
				.failureUrl("/sbat/error")
				.permitAll()
		.and()
	    	.httpBasic()
		.and()
			.logout()
			.permitAll();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService()
	{
		UserDetails johnDoeUser = User.builder()
										.username("johndoe")
										.password(passwordEncoder.encode("password"))
										.authorities(PERSON.getGrantedAuthorities())
										.build();
		
		UserDetails AdminUser = User.builder()
										.username("AdminUser")
										.password(passwordEncoder.encode("password"))
										.authorities(ADMIN.getGrantedAuthorities())
										.build();
		
		UserDetails AdminTraineeUser = User.builder()
										.username("AdminTraineeUser")
										.password(passwordEncoder.encode("password"))
										.authorities(ADMINTRAINEE.getGrantedAuthorities())
										.build();
		
		return new InMemoryUserDetailsManager(johnDoeUser,AdminUser,AdminTraineeUser);
	}
}