package io.github.anantharajuc.sbtest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping({"/", "/sbat"})
public class SpringBootApplicationTemplateController 
{
	@Autowired
	private Environment environment;
	
	@GetMapping("/index")
    public String index() 
	{
		return "pages/index";
    }
	
	@GetMapping("/about")
    public String about() 
	{
		return "pages/about";
    }
	
	@GetMapping("/close")
	public String close()
	{
		log.info("App Shutdown");
		
		String port = environment.getProperty("local.server.port");
		
		log.info("port : "+port);
		
		String command = "curl --location --request POST http://localhost:"+port+"/actuator/shutdown";
		
		try 
		{
			Process process = Runtime.getRuntime().exec(command);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return "pages/close";
	}
}
