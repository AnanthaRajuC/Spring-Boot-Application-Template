package io.github.anantharajuc.sbat.core_backend.security;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.util.StringUtils;

import io.github.anantharajuc.sbat.core_backend.security.user.UserPrincipalService;
import lombok.extern.log4j.Log4j2;

/**
 * Application Security Configuration.
 *
 * Two filter chains are configured:
 * <ul>
 * <li>REST API, RBAC and actuator endpoints: stateless, authenticated with a JWT bearer token (issued by
 * {@code /api/v1/auth/login}) or HTTP Basic.</li>
 * <li>Everything else (the Thymeleaf UI): form login with an HTTP session, remember-me and CSRF protection.</li>
 * </ul>
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class ApplicationSecurityConfiguration
{
	/** Public URLs of the web UI. */
	private static final String[] PUBLIC_MATCHERS =
	{
			"/",
			"/webjars/**",
			"/css/**",
			"/js/**",
			"/fonts/**",
			"/images/**",
			"/favicon.ico",
			"/error",
			"/403",
			"/sbat/index/**",
			"/sbat/login",
			"/lang"
	};

	/** OpenAPI document and Swagger UI, disabled in the production profile. */
	private static final String[] API_DOCS_MATCHERS =
	{
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html"
	};

	@Bean
	@Order(1)
	public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http, JwtUserAuthenticationConverter jwtUserAuthenticationConverter)
	{
		http
			.securityMatcher("/api/**", "/rbac/**", "/actuator/**")
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/v1/auth/**").permitAll()
				.requestMatchers("/api/generic-hello", "/api/personalized-hello").permitAll()
				.requestMatchers("/actuator/health", "/actuator/health/**", "/actuator/info").permitAll()
				.requestMatchers("/actuator/**").hasRole("ADMIN")
				.requestMatchers("/rbac/**").hasRole("ADMIN")
				.anyRequest().authenticated())
			// Stateless API authenticated by a header, not a cookie, so CSRF protection does not apply.
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic(Customizer.withDefaults())
			.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtUserAuthenticationConverter)));

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain webSecurityFilterChain(HttpSecurity http,
													  UserPrincipalService userPrincipalService,
													  SecurityProperties securityProperties,
													  @Value("${spring.h2.console.enabled:false}") boolean h2ConsoleEnabled,
													  @Value("${spring.h2.console.path:/h2-console}") String h2ConsolePath)
	{
		String h2Console = h2ConsolePath.replaceAll("/+$", "") + "/**";

		http
			.authorizeHttpRequests(authorize -> {
				authorize.requestMatchers(PUBLIC_MATCHERS).permitAll();
				authorize.requestMatchers(API_DOCS_MATCHERS).permitAll();
				if (h2ConsoleEnabled)
				{
					// The H2 console has its own login.
					authorize.requestMatchers(h2Console).permitAll();
				}
				authorize.requestMatchers("/sbat/close", "/sbat/settings").hasRole("ADMIN");
				authorize.anyRequest().authenticated();
			})
			.csrf(csrf -> csrf.ignoringRequestMatchers(h2Console))
			// The H2 console is rendered in frames.
			.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
			.formLogin(form -> form
				.loginPage("/sbat/login")
				.defaultSuccessUrl("/sbat/index")
				.failureUrl("/sbat/login?error")
				.usernameParameter("sbat-username")
				.passwordParameter("sbat-password")
				.permitAll())
			.rememberMe(rememberMe -> rememberMe
				.key(rememberMeKey(securityProperties))
				.tokenValiditySeconds((int) Duration.ofDays(21).toSeconds())
				.rememberMeParameter("remember-me")
				.userDetailsService(userPrincipalService))
			.logout(logout -> logout
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me")
				.logoutSuccessUrl("/sbat/index")
				.permitAll())
			.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/403"))
			.sessionManagement(session -> session
				.maximumSessions(1)
				.sessionRegistry(sessionRegistry()));

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
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

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}

	private static String rememberMeKey(SecurityProperties securityProperties)
	{
		if (StringUtils.hasText(securityProperties.rememberMeKey()))
		{
			return securityProperties.rememberMeKey();
		}

		log.warn("No remember-me key configured (sbat.security.remember-me-key), using a random key. Remember-me cookies will not survive a restart.");

		byte[] key = new byte[32];
		new SecureRandom().nextBytes(key);

		return Base64.getEncoder().encodeToString(key);
	}
}
