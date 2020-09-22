package io.github.anantharajuc.sbtest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password Configuration
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Configuration
public class PasswordConfiguration 
{
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}
}
