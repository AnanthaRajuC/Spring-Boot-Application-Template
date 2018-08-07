package com.arc.sbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SBtemplateApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(SBtemplateApplication.class, args);
	}
}
