package io.github.anantharajuc.sbat;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import io.github.anantharajuc.sbat.core_backend.service.impl.OtherServicesImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Spring Boot Application Template.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableCaching
@EnableAsync
@Log4j2
@AllArgsConstructor
public class SBtemplateApplication implements CommandLineRunner
{
	private final OtherServicesImpl otherServicesImpl;

	public static void main(String[] args)
	{
		SpringApplication.run(SBtemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		log.info("Spring Boot Application Template started at {}", LocalDateTime.now());

		log.info("-----> Initial Application Settings Key Value Load.");

		otherServicesImpl.loadApplicationSettings();

		log.info("-----> Application Name    : {}", otherServicesImpl.getApplicationName());
		log.info("-----> Application Version : {}", otherServicesImpl.getApplicationVersion());
	}
}
