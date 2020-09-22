package io.github.anantharajuc.sbtest.person.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbtest.person.model.Person;
import io.github.anantharajuc.sbtest.person.services.PersonCommandServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Person Command Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping("/api/v1")
@CacheConfig(cacheNames={"person"})
@Api(value="PersonCommands", tags="Person Commands")
public class PersonCommandController 
{
	@Autowired
	private PersonCommandServiceImpl personServiceImpl;
	
	@PostMapping(value="/person", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(httpMethod="POST", value = "Add Person", notes = "Add a new Person to the datastore",response=Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_CREATE')")
	public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person)
	{		
		return new ResponseEntity<>(personServiceImpl.createPerson(person), HttpStatus.CREATED);
	}

	@CacheEvict(allEntries=true)
	@PutMapping(value="/person/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod="PUT", value = "UPDATE Person", notes = "Update an existing Person in the datastore",response=Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_UPDATE')")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId,@Valid @RequestBody Person personDetails)
	{		
		return new ResponseEntity<>(personServiceImpl.updatePerson(personId, personDetails),HttpStatus.OK);
	}
	
	@CacheEvict(allEntries=true)
	@DeleteMapping(value="/person/{id}")
	@ApiOperation(httpMethod="DELETE", value = "DELETE an existing Person", notes = "Delete an existing Person from the datastore")
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_DELETE')")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personId) 
	{		
		return personServiceImpl.deletePerson(personId);
	}
}
