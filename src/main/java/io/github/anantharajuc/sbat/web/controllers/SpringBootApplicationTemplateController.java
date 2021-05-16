package io.github.anantharajuc.sbat.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.anantharajuc.sbat.core_backend.api.ResourcePaths;
import io.github.anantharajuc.sbat.core_backend.persistence.repositories.BuiltWithRepository;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import io.github.anantharajuc.sbat.core_backend.user.service.UserQueryServiceImpl;
import io.github.anantharajuc.sbat.core_backend.util.SiteSettings;
import io.github.anantharajuc.sbat.example.crm.user.controllers.PersonQueryController;
import io.github.anantharajuc.sbat.example.crm.user.model.Person;
import io.github.anantharajuc.sbat.example.crm.user.services.PersonQueryServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.log4j.Log4j2;

/**
 * Application Web Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Controller
@RequestMapping(value=ResourcePaths.SBAT.V1.ROOT) 
public class SpringBootApplicationTemplateController 
{
	@Autowired
	private Environment environment;
	
	@Autowired
	private BuiltWithRepository builtWithRepository;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserQueryServiceImpl userQueryServiceImpl;
	
	@Autowired
	private PersonQueryController personController;
	
	@Autowired
    private PersonQueryServiceImpl personQueryServiceImpl;
	
	@GetMapping(value=ResourcePaths.SBAT.V1.PERSONS) 
    public String persons(Model model) 
	{
		model.addAttribute("persons", personController.getAllPersons(null, null)); 
		
		return "pages/persons";
    }
	  
	@GetMapping(value=ResourcePaths.SBAT.V1.INDEX)
    public String index(Model model) 
	{
		Dotenv dotenv = Dotenv.load();	
		
		SiteSettings siteSettings = new SiteSettings();	
		
		siteSettings.setSiteLogo(dotenv.get("SITE_LOGO", "Unable to fetch SITE_LOGO"));
		siteSettings.setSiteInitials(dotenv.get("SITE_INITIALS", "Unable to fetch SITE_INITIALS"));
		siteSettings.setSiteTitle(dotenv.get("SITE_TITLE", "Unable to fetch SITE_TITLE"));
		siteSettings.setSiteDescription(dotenv.get("SITE_DESCRIPTION", "Unable to fetch SITE_DESCRIPTION"));
		
		model.addAttribute("site_settings", siteSettings);
		
		return "pages/index";
    }
	
	@GetMapping(value=ResourcePaths.SBAT.V1.LOGIN)
    public String login() 
	{
		return "pages/login";
    }
	
	@GetMapping(value=ResourcePaths.SBAT.V1.ABOUT)
    public String about() 
	{
		return "pages/about";
    }
	
	@GetMapping(value=ResourcePaths.SBAT.V1.ARCHITECTURE)
    public String architecture() 
	{
		return "pages/architecture";
    }
	
	@GetMapping(value=ResourcePaths.SBAT.V1.DOCKER)
    public String docker() 
	{
		return "pages/docker";
    }
	
	@GetMapping(value=ResourcePaths.SBAT.V1.STATUS)
    public String status() 
	{
		return "pages/status";
    }
	
	@GetMapping(value=ResourcePaths.SBAT.V1.SECURITY)
    public String security() 
	{
		return "pages/security";
    }

	@GetMapping(value=ResourcePaths.SBAT.V1.SETTINGS)
    public String settings(Model model) 
	{
		model.addAttribute("users", userController.listLoggedInUsers());
		
		return "pages/users";
    }
	
	@GetMapping(value=ResourcePaths.SBAT.V1.TECH_STACK)
	public String builtWith(Model model, @RequestParam(defaultValue="0") int page)
	{ 
		model.addAttribute("data", builtWithRepository.findAll());
		
		return "pages/built_with";
	}
	
	@GetMapping("/profile")
    public String profile(Model model) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        log.info("currentPrincipalName : "+currentPrincipalName);
        
		model.addAttribute("data", userQueryServiceImpl.getUserByUsername(currentPrincipalName)); 
		
		return "pages/profile";
    }
	
	@GetMapping("/listPersonsByUsers")
	public String listPersons(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        log.info("currentPrincipalName : "+currentPrincipalName);
        
        model.addAttribute("personPage", personQueryServiceImpl.getPersonByUsername(currentPrincipalName)); 
        
		return "pages/listPersonsByUsers";
	}
	
	@GetMapping("/listPersons")
	public String listPersons(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size)
	{
		final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(10);

        Page<Person> personPage = personQueryServiceImpl.findPaginated(PageRequest.of(currentPage - 1, pageSize));

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
	
	@GetMapping(value=ResourcePaths.SBAT.V1.CLOSE)
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
			
			log.info("process : "+process);
		} 
		catch (IOException e) 
		{
			log.error( "App shutdown failed!", e );
		}
		
		return "pages/close";
	}
}
