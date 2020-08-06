package io.github.anantharajuc.sbtest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbtest.model.Person;
import io.github.anantharajuc.sbtest.service.PersonServiceImpl;

@RestController
@RequestMapping("/api/")
public class PersonController 
{
	@Autowired
	private PersonServiceImpl personServiceImpl;
	
	@GetMapping(value="/person")	
	public List<Person> getAllPersons() 
	{		
		return personServiceImpl.getAllPersons();
	}
	
	@GetMapping(value="/person/pageable")	
	public Page<Person> getAllPersons(Pageable pageable) 
	{		
		return personServiceImpl.getAllPersonsPageable(pageable);
	}
	
	@GetMapping(value="/person/gender/{gender}")
	public List<Person> getPersonByGender(@PathVariable(value = "gender") String gender)
	{
		return personServiceImpl.getPersonsByGender(gender);  
	}
	
	@GetMapping(value="/person/{id}")
	public Person getPersonById(@PathVariable(value = "id") Long personId)
	{		
		return personServiceImpl.getPersonById(personId);
	}
	
	@PostMapping("/person")
	public Person createPerson(@Valid @RequestBody Person person)
	{		
		return personServiceImpl.createPerson(person);
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personId) 
	{		
		return personServiceImpl.deletePerson(personId);
	}
	
	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable(value = "id") Long personId,@Valid @RequestBody Person personDetails)
	{		
		return personServiceImpl.updatePerson(personId, personDetails);
	}
}
