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

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Person;
import io.github.anantharajuc.sbtest.backend.service.impl.PersonServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Person Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping("/api/v1")
@CacheConfig(cacheNames={"person"})
@Api(value = "/api/v1", tags = "Person")
public class PersonController 
{
	@Autowired
	private PersonServiceImpl personServiceImpl;
	
	@Cacheable()
	@GetMapping(value="/person")	
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value="Find all persons", notes="Returns all Person's in the data store.")
	public List<Person> getAllPersons() 
	{		
		return personServiceImpl.getAllPersons();
	}
	
	@GetMapping(value="/person/pageable")	
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value="Find all persons via Paging", notes="Returns all Person's in the data store via Paging.")
	public Page<Person> getAllPersons(Pageable pageable) 
	{		
		return personServiceImpl.getAllPersonsPageable(pageable);
	}

	@GetMapping(value="/person/gender/{gender}")
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value="Find all persons based on Gender", notes="Returns all Person's in the data store.")
	public List<Person> getPersonByGender(@PathVariable(value = "gender") String gender)
	{
		return personServiceImpl.getPersonsByGender(gender);  
	}
	
	@GetMapping(value="/person/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value = "Find person by ID", notes = "Returns a person for the given ID",response = Person.class)
	@ApiResponse(code = 400, message = "Invalid ID supplied")
	public Person getPersonById(@PathVariable(value = "id") Long personId)
	{		
		return personServiceImpl.getPersonById(personId);
	}
	
	@PostMapping("/person")
	@ApiOperation(httpMethod="POST", value = "Add Person", notes = "Add a new Person to the datastore",response = Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_CREATE')")
	public Person createPerson(@Valid @RequestBody Person person)
	{		
		return personServiceImpl.createPerson(person);
	}

	@CachePut(value="person", key="id")
	@PutMapping("/person/{id}")
	@ApiOperation(httpMethod="PUT", value = "UPDATE Person", notes = "Update an existing Person in the datastore",response = Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_UPDATE')")
	public Person updatePerson(@PathVariable(value = "id") Long personId,@Valid @RequestBody Person personDetails)
	{		
		return personServiceImpl.updatePerson(personId, personDetails);
	}
	
	@CacheEvict(allEntries=true)
	@DeleteMapping("/person/{id}")
	@ApiOperation(httpMethod="DELETE", value = "DELETE an existing Person", notes = "Delete an existing Person from the datastore")
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_DELETE')")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personId) 
	{		
		return personServiceImpl.deletePerson(personId);
	}
}
