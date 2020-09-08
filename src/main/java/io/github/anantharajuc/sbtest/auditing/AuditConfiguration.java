package io.github.anantharajuc.sbtest.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class AuditConfiguration 
{
	 @Bean
	 public AuditorAware<String> auditorProvider() 
	 {
		 return new AuditorAwareImpl();
	 }
}
