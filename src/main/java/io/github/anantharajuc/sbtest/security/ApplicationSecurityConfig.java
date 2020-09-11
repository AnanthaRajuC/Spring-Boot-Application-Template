package io.github.anantharajuc.sbtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.TimeUnit;

@Log4j2
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	UserPrincipalService userPrincipalService;

	
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
		.csrf()
			.disable()
		.authorizeRequests()		
			.antMatchers(PUBLIC_MATCHERS).permitAll()					
			.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/sbat/login")
				.defaultSuccessUrl("/sbat/index")
				.failureUrl("/sbat/error")
				.permitAll()
				.passwordParameter("sbat-password")
				.usernameParameter("sbat-username") 
		.and()
			.rememberMe() 													
				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
				.key("some-strong-key") 
				.rememberMeParameter("remember-me") 
	    .and()
	    	.httpBasic()
		.and()
			.logout()
			.logoutUrl("/logout")
			.clearAuthentication(true)
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID","remember-me")
			.logoutSuccessUrl("/sbat/index") 
			.permitAll();
		
		http
			.sessionManagement()
			.maximumSessions(1)
			.sessionRegistry(sessionRegistry());
	}
	
	@Bean
    public SessionRegistry sessionRegistry() 
	{
        return new SessionRegistryImpl();
    }
	
	@Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() 
	{
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() 
    {
    	log.info("-----> DaoAuthenticationProvider : ");
    	
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userPrincipalService);
        
        return provider;
    }
}