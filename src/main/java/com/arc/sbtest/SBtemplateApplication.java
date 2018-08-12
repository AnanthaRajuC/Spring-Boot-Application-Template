package com.arc.sbtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.beans.factory.annotation.Autowired;

import com.arc.sbtest.repository.BuiltWithRepository;
import com.arc.sbtest.model.BuiltWith;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.arc.sbtest","com.arc.sbtest.controller"})
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
		builtWithRepository.save(new BuiltWith("name", "version", "description", "link", null, null)); 
	}
}
