package io.github.anantharajuc.sbat.core_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import io.github.anantharajuc.sbat.core_backend.security.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * Application Security Configuration
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserDetailsService userDetailsService;
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception 
	{
        return super.authenticationManagerBean();
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
            "/lang",
            "/h2-console/**"
    };	
    
    private static final String[] SWAGGER_MATCHERS = 
    {
    		"/v2/api-docs",
    		"/api/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.csrf()
			.disable()
		.authorizeRequests()		
			.antMatchers("/api/v1/auth/**").permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers(SWAGGER_MATCHERS).permitAll()	
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
				.permitAll()
		.and()
			.exceptionHandling()
				.accessDeniedPage("/403");
		
		http
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		//https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser
		http
			.headers()
			.frameOptions()
			.sameOrigin();
		
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
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception 
	{
        authenticationManagerBuilder
        	.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}
}