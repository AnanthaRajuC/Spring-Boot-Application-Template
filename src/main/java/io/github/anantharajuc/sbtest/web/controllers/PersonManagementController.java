package io.github.anantharajuc.sbtest.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbtest.person.model.Person;
import io.github.anantharajuc.sbtest.person.services.PersonCommandServiceImpl;
import io.github.anantharajuc.sbtest.person.services.PersonQueryServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Person Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping("/management/api/v1")
@CacheConfig(cacheNames={"personManagement"})
public class PersonManagementController 
{
	@Autowired
	private PersonQueryServiceImpl personQueryServiceImpl; 
	
	@Autowired
	private PersonCommandServiceImpl personCommandServiceImpl;
	
	@Cacheable()
	@GetMapping(value="/person")	
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	@ApiOperation(httpMethod="GET", value="Find all persons", notes="Returns all Person's in the data store.")
	public List<Person> getAllPersons() 
	{		
		return personQueryServiceImpl.getAllPersons(); 
	}
	
	@GetMapping(value="/person/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	@ApiOperation(httpMethod="GET", value = "Find person by ID", notes = "Returns a person for the given ID", response=Person.class)
	@ApiResponse(code = 400, message = "Invalid ID supplied")
	public Person getPersonById(@PathVariable(value="id") Long personId)
	{		
		return personQueryServiceImpl.getPersonById(personId);
	}
	
	@GetMapping(value="/person/pageable")
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
	@ApiOperation(httpMethod="GET", value="Find all persons via Paging", notes="Returns all Person's in the data store via Paging.")
	public Page<Person> getAllPersons(Pageable pageable) 
	{		
		return personQueryServiceImpl.getAllPersonsPageable(pageable);
	}
	
	@PostMapping("/person")
	@ApiOperation(httpMethod="POST", value="Add Person", notes = "Add a new Person to the datastore", response=Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE') and hasAuthority('PERSON_CREATE')")
	public Person createPerson(@Valid @RequestBody Person person)
	{		
		return personCommandServiceImpl.createPerson(person);
	}
	
	@CachePut(value="person", key="id")
	@PutMapping("/person/{id}")
	@ApiOperation(httpMethod="PUT", value="UPDATE Person", notes = "Update an existing Person in the datastore", response=Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE') and hasAuthority('PERSON_UPDATE')")
	public Person updatePerson(@PathVariable(value="id") Long personId,@Valid @RequestBody Person personDetails)
	{		
		return personCommandServiceImpl.updatePerson(personId, personDetails);
	}
	
	@CacheEvict(allEntries=true)
	@DeleteMapping("/person/{id}")
	@ApiOperation(httpMethod="DELETE", value = "DELETE an existing Person", notes = "Delete an existing Person from the datastore")
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE') and hasAuthority('PERSON_DELETE')")
	public ResponseEntity<?> deletePerson(@PathVariable(value="id") Long personId) 
	{		
		return personCommandServiceImpl.deletePerson(personId);
	}
}
