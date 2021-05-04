package io.github.anantharajuc.sbat;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import io.github.anantharajuc.sbat.core_backend.service.impl.OtherServicesImpl;
import lombok.extern.log4j.Log4j2;

/**
 * Spring Boot Application Template.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@SpringBootApplication
@EnableCaching
@Log4j2
@EnableAsync(proxyTargetClass=true)
public class SBtemplateApplication implements CommandLineRunner
{	
	@Autowired
	private OtherServicesImpl otherServicesImpl;
	
	public static void main(String[] args) 
	{		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SBtemplateApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
		
		log.info("-----> Application Context : "+context.getId());
	}

	@Override
	public void run(String... args) throws Exception 
	{
		log.info("Spring Boot Application Template started at {}", LocalDateTime.now());	
		
		log.info("-----> Initial Application Settings Key Value Load.");
		
		otherServicesImpl.loadApplicationSettings();
		
		log.info("-----> Application Name    : "+otherServicesImpl.getApplicationName());	
		log.info("-----> Application Version : "+otherServicesImpl.getApplicationVersion());
	}
}
 