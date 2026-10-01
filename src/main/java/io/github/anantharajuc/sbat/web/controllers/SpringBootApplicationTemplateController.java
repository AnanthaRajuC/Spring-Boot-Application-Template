package io.github.anantharajuc.sbat.web.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.anantharajuc.sbat.core_backend.api.ResourcePaths;
import io.github.anantharajuc.sbat.core_backend.persistence.repositories.BuiltWithRepository;
import io.github.anantharajuc.sbat.core_backend.user.service.UserQueryServiceImpl;
import io.github.anantharajuc.sbat.core_backend.util.SiteProperties;
import io.github.anantharajuc.sbat.example.crm.user.model.Person;
import io.github.anantharajuc.sbat.example.crm.user.services.PersonQueryServiceImpl;
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
	private ConfigurableApplicationContext applicationContext;
	
	@Autowired
	private SiteProperties siteProperties;
	
	@Autowired
	private BuiltWithRepository builtWithRepository;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private UserQueryServiceImpl userQueryServiceImpl;
	
	@Autowired
    private PersonQueryServiceImpl personQueryServiceImpl;
	
	@GetMapping(value=ResourcePaths.SBAT.V1.PERSONS) 
    public String persons(Model model) 
	{
		return "redirect:/sbat/listPersons";
    }
	  
	@GetMapping(value=ResourcePaths.SBAT.V1.INDEX)
    public String index(Model model) 
	{
		model.addAttribute("site_settings", siteProperties.toSiteSettings());
		
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
		final int currentPage = Math.max(1, page.orElse(1));
        final int pageSize = Math.clamp(size.orElse(10), 1, 100);

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
	
	@PostMapping(value=ResourcePaths.SBAT.V1.CLOSE)
	public String close()
	{
		log.info("App Shutdown requested");
		
		// Shut down after the response has been rendered.
		Thread shutdown = new Thread(() -> {
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			
			System.exit(SpringApplication.exit(applicationContext, () -> 0));
		}, "sbat-shutdown");
		shutdown.setDaemon(false);
		shutdown.start();
		
		return "pages/close";
	}
}
