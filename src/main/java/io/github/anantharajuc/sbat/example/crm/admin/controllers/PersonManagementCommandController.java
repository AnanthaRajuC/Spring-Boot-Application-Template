package io.github.anantharajuc.sbat.example.crm.admin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbat.core_backend.api.ResourcePaths;
import io.github.anantharajuc.sbat.example.crm.user.model.Person;
import io.github.anantharajuc.sbat.example.crm.user.services.PersonCommandService;
import io.swagger.annotations.ApiOperation;

/**
 * Person Management Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping(value=ResourcePaths.PersonManagement.V1.ROOT)
@CacheConfig(cacheNames={"personManagement"})
public class PersonManagementCommandController 
{
	@Autowired
	private PersonCommandService personCommandServiceImpl;

	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod="POST", value="Add Person", notes = "Add a new Person to the datastore", response=Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE') and hasAuthority('PERSON_CREATE')")
	public Person createPerson(@Valid @RequestBody Person person)
	{		
		return personCommandServiceImpl.createPerson(person);
	}
	
	@CacheEvict(allEntries=true)
	@PutMapping(value=ResourcePaths.ID, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod="PUT", value="UPDATE Person", notes = "Update an existing Person in the datastore", response=Person.class)
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE') and hasAuthority('PERSON_UPDATE')")
	public Person updatePerson(@PathVariable(value="id") Long personId,@Valid @RequestBody Person personDetails)
	{		
		return personCommandServiceImpl.updatePerson(personId, personDetails);
	}
	
	@CacheEvict(allEntries=true)
	@DeleteMapping(value=ResourcePaths.ID)
	@ApiOperation(httpMethod="DELETE", value = "DELETE an existing Person", notes = "Delete an existing Person from the datastore")
	@PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE') and hasAuthority('PERSON_DELETE')")
	public ResponseEntity<?> deletePerson(@PathVariable(value="id") Long personId) 
	{		
		return personCommandServiceImpl.deletePerson(personId);
	}
}