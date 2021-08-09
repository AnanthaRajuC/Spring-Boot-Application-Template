package io.github.anantharajuc.sbat.core_backend.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.mfrey.thymeleaf.extras.with.WithDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * Thymeleaf dialect configuration intended to build layouts and reusable templates in order to improve code reuse.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 * 
 */
@Configuration
public class ViewConfiguration 
{
	@Bean
	public LayoutDialect layoutDialect() 
	{
		return new LayoutDialect();
	}

	@Bean
	public WithDialect withDialect() 
	{
		return new WithDialect();
	}
}
