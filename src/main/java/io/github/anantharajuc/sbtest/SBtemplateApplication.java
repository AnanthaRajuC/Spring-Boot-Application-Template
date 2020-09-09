package io.github.anantharajuc.sbtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import io.github.anantharajuc.sbtest.backend.persistence.repositories.ApplicationLogRepository;
import io.github.anantharajuc.sbtest.backend.service.impl.OtherServicesImpl;
import lombok.extern.log4j.Log4j2;

/**
 * Spring Boot Application Template.
 *
 * @author Anantha Raju C
 *
 */
@SpringBootApplication
@EnableCaching
@Log4j2
public class SBtemplateApplication implements CommandLineRunner
{
	@Autowired
	private ApplicationLogRepository applicationLogRepository;
	
	@Autowired
	private OtherServicesImpl otherServicesImpl;
	
	public static void main(String[] args) 
	{		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SBtemplateApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		log.info("Hello SB template");	
		
		log.info("-----> Initial Application Settings Key Value Load.");	
		otherServicesImpl.loadApplicationSettings();
		
		//applicationLogRepository.save(new ApplicationLog(ApplicationLogEnum.APPLICATION_START,"application started."));
		
		log.info("-----> Application Name    : "+otherServicesImpl.getApplicationName());	
		log.info("-----> Application Version : "+otherServicesImpl.getApplicationVersion());	
	}
}
 