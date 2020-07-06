package io.github.anantharajuc.sbtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.github.anantharajuc.sbtest.enums.ApplicationLogEnum;
import io.github.anantharajuc.sbtest.model.ApplicationLog;
import io.github.anantharajuc.sbtest.repository.ApplicationLogRepository;
import io.github.anantharajuc.sbtest.service.OtherServicesImpl;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableJpaAuditing
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
		SpringApplication.run(SBtemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		log.info("Hello SB template");	
		
		log.info("-----> Initial Application Settings Key Value Load.");	
		otherServicesImpl.loadApplicationSettings();
		
		applicationLogRepository.save(new ApplicationLog(ApplicationLogEnum.APPLICATION_START,"application started."));
		
		log.info("-----> Application Name    : "+otherServicesImpl.getApplicationName());	
		log.info("-----> Application Version : "+otherServicesImpl.getApplicationVersion());	
	}
}
 