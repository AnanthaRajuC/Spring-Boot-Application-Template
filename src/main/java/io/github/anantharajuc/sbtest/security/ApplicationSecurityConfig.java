package io.github.anantharajuc.sbtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/sbat/login").defaultSuccessUrl("/sbat/index")
		.failureUrl("/sbat/error").permitAll()
		.and()
	    .httpBasic()
		.and()
		.logout().permitAll();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService()
	{
		UserDetails johnDoeUser = User.builder()
										.username("johnDoe")
										.password(passwordEncoder.encode("password"))
										.roles("PERSON") 
										.build();
		
		UserDetails AdminUser1 = User.builder()
										.username("AdminUser")
										.password(passwordEncoder.encode("password"))
										.roles("ADMIN") 
										.build();
		
		return new InMemoryUserDetailsManager(johnDoeUser,AdminUser1);
	}
}