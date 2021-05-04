package io.github.anantharajuc.sbat.core_backend.persistence.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Audit Configuration.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
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
