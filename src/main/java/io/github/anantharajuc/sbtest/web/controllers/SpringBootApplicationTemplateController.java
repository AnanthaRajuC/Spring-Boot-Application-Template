package io.github.anantharajuc.sbtest.web.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.anantharajuc.sbtest.backend.persistence.repositories.BuiltWithRepository;

import lombok.extern.log4j.Log4j2;

/**
 * @author Anantha Raju C
 */
@Log4j2
@Controller
@RequestMapping({"/sbat"})
public class SpringBootApplicationTemplateController 
{
	@Autowired
	private Environment environment;
	
	@Autowired
	BuiltWithRepository builtWithRepository;
	
	@Autowired
	UserController userController;
	
	@GetMapping("/index")
    public String index() 
	{
		return "pages/index";
    }
	
	@GetMapping("/login")
    public String login() 
	{
		return "pages/login";
    }
	
	@GetMapping("/about")
    public String about() 
	{
		return "pages/about";
    }
	
	@GetMapping("/settings")
    public String settings(Model model) 
	{
		model.addAttribute("users", userController.listLoggedInUsers());
		
		return "pages/users";
    }
	
	@GetMapping("/tech-stack")
	public String builtWith(Model model, @RequestParam(defaultValue="0") int page)
	{ 
		model.addAttribute("data", builtWithRepository.findAll());
		
		log.info(model.toString()); 
		
		return "pages/built_with";
	}
	
	@GetMapping("/close")
	public String close()
	{
		log.info("App Shutdown");
		
		String port = environment.getProperty("local.server.port");
		
		log.info("port : "+port);
		
		String command = "curl --location --request POST http://localhost:"+port+"/actuator/shutdown";
		
		log.info("shutdown command : "+command);
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
