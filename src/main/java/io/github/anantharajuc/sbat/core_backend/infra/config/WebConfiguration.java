package io.github.anantharajuc.sbat.core_backend.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Views that need no controller logic.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer
{
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addRedirectViewController("/", "/sbat/index");
		// Access denied page, see ApplicationSecurityConfiguration.
		registry.addViewController("/403").setViewName("403");
	}
}
