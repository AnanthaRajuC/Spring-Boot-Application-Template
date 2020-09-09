package io.github.anantharajuc.sbtest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.mfrey.thymeleaf.extras.with.WithDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

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
