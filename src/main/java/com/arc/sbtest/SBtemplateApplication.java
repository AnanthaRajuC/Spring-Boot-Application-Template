package com.arc.sbtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.beans.factory.annotation.Autowired;

import com.arc.sbtest.repository.BuiltWithRepository;
import com.arc.sbtest.model.BuiltWith;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.arc.sbtest","com.arc.sbtest.controller"})
@EnableCaching
public class SBtemplateApplication implements CommandLineRunner
{
	@Autowired
	private BuiltWithRepository builtWithRepository;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(SBtemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		builtWithRepository.save(new BuiltWith("Maven", "3.5.2", "Dependency Management", "https://maven.apache.org/", null, null)); 
		builtWithRepository.save(new BuiltWith("JDK", "9.0.1", "Java™ Platform, Standard Edition Development Kit", "http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html", null, null));
		builtWithRepository.save(new BuiltWith("Spring Boot	", "2.0.4", "Framework to ease the bootstrapping and development of new Spring Applications", "https://spring.io/projects/spring-boot", null, null));
		builtWithRepository.save(new BuiltWith("MySQL", "5.7.21", "Open-Source Relational Database Management System", "https://www.mysql.com/", null, null));
		builtWithRepository.save(new BuiltWith("git", "2.9.0.windows.1", "Free and Open-Source distributed version control system", "https://git-scm.com/", null, null));
		builtWithRepository.save(new BuiltWith("Postman", "v6.2.3", "API Development Environment (ADE)", "https://www.getpostman.com/", null, null));
		builtWithRepository.save(new BuiltWith("Swagger", "2.7.0", "API Design, Development, Documentation, Testing & Governance", "https://swagger.io/", null, null));
		builtWithRepository.save(new BuiltWith("Prometheus", "2.3.2", "Monitoring system and time series database", "https://prometheus.io/", null, null));
	}
}
