package com.arc.sbtest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arc.sbtest.exception.ResourceNotFoundException;
import com.arc.sbtest.model.Person;
import com.arc.sbtest.repository.PersonRepository;

@RestController
@RequestMapping("/api/")
public class PersonController 
{
	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/person")
	public List<Person> getAllPersons() 
	{
		return  personRepository.findAll();
	}
	
	@GetMapping("/person/{id}")
	public Person getPersonById(@PathVariable(value = "id") Long personId)
	{
		return personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
	}
	
	@PostMapping("/person")
	public Person createPerson(@Valid @RequestBody Person person)
	{
		return personRepository.save(person);		
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personId) 
	{
		Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
		personRepository.delete(person);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable(value = "id") Long personId,@Valid @RequestBody Person personeDetails)
	{
		Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
		
		person.setName(personeDetails.getName());
		person.setGender(personeDetails.getGender());
		person.setAge(personeDetails.getAge());

		return personRepository.save(person);		
	}
}
