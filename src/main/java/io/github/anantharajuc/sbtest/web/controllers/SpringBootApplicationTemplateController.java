package io.github.anantharajuc.sbtest.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Person;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.BuiltWithRepository;
import io.github.anantharajuc.sbtest.backend.service.impl.PersonServiceImpl;
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
	private BuiltWithRepository builtWithRepository;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private PersonController personController;
	
	@Autowired
    private PersonServiceImpl personServiceImpl;
	
	@GetMapping("/persons")
    public String persons(Model model) 
	{
		model.addAttribute("persons", personController.getAllPersons());
		
		return "pages/persons";
    }
	 
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
	
	@GetMapping("/listPersons")
	public String listPersons(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size)
	{
		final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        
        Page<Person> personPage = personServiceImpl.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        
        model.addAttribute("personPage", personPage);
        
        int totalPages = personPage.getTotalPages();
        
        if (totalPages > 0) 
        {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
								                .boxed()
								                .collect(Collectors.toList());
            
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
		return "pages/listPersons";
	}
}
