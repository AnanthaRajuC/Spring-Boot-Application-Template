package io.github.anantharajuc.sbtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.github.anantharajuc.sbtest.enums.ApplicationLogEnum;
import io.github.anantharajuc.sbtest.model.ApplicationLog;
import io.github.anantharajuc.sbtest.repository.ApplicationLogRepository;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableJpaAuditing
//@ComponentScan({"com.arc.sbtest","com.arc.sbtest.controller"})
@EnableCaching
@Log4j2
public class SBtemplateApplication implements CommandLineRunner
{
	@Autowired
	private ApplicationLogRepository applicationLogRepository;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(SBtemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		System.out.println("Hello SB template");
		log.info("Hello SB template");	
		
		applicationLogRepository.save(new ApplicationLog(ApplicationLogEnum.APPLICATION_START,"application started."));
	}
}
 