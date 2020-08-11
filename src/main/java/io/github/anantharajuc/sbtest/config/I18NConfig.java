package io.github.anantharajuc.sbtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class I18NConfig 
{
	@Bean
	public ReloadableResourceBundleMessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename("classpath:i18n/messages");
		
		//Checks for new messages every 30 minutes
		reloadableResourceBundleMessageSource.setCacheSeconds(1800);
		return reloadableResourceBundleMessageSource;		
	}
}
