package io.github.anantharajuc.sbat.example.crm.admin.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbat.core_backend.api.ResourcePaths;
import io.github.anantharajuc.sbat.example.crm.user.model.Person;
import io.github.anantharajuc.sbat.example.crm.user.model.dto.PersonDTO;
import io.github.anantharajuc.sbat.example.crm.user.services.PersonQueryService;

/**
 * Person Management Query Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping(value=ResourcePaths.PersonManagement.V1.ROOT)
@CacheConfig(cacheNames={"personManagement"})
public class PersonManagementQueryController 
{
	@Autowired
	private PersonQueryService personQueryServiceImpl; 
	
	@Cacheable()
	@GetMapping()
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	@Operation(summary="Find all persons", description="Returns all Person's in the data store.")
	public List<Person> getAllPersons() 
	{		
		return personQueryServiceImpl.getAllPersons(); 
	}
	
	@GetMapping(value=ResourcePaths.ID)
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	@Operation(summary="Find person by ID", description="Returns a person for the given ID")
	@ApiResponse(responseCode="400", description="Invalid ID supplied")
	public PersonDTO getPersonById(@PathVariable(value="id") Long personId)
	{		
		return personQueryServiceImpl.getPersonById(personId);
	}
	
	@GetMapping(value=ResourcePaths.PAGEABLE)	
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	@Operation(summary="Find all persons via Paging", description="Returns all Person's in the data store via Paging.")
	public Page<Person> getAllPersons(Pageable pageable) 
	{		
		return personQueryServiceImpl.getAllPersonsPageable(pageable);
	}
}
