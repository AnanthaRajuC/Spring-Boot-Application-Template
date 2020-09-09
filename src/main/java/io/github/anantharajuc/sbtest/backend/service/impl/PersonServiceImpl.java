package io.github.anantharajuc.sbtest.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Person;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.PersonRepository;
import io.github.anantharajuc.sbtest.backend.service.PersonService;
import io.github.anantharajuc.sbtest.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PersonServiceImpl implements PersonService
{
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public List<Person> getAllPersons() 
	{
		log.info("-----> getAllPersons service");	
		
		return personRepository
				.findAll();
	}
	
	@Override
	public Page<Person> getAllPersonsPageable(Pageable pageable) 
	{
		log.info("-----> getAllPersons service");	
		
		return personRepository
				.findAll(pageable);
	}

	@Override
	public Person getPersonById(Long personId) 
	{
		log.info("-----> getPersonById service");	
		
		
		
		return personRepository
				.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
	}

	@Override
	public Person createPerson(Person person) 
	{
		log.info("-----> createPerson service");	
		
		return personRepository
				.save(person);
	}

	@Override
	public ResponseEntity<?> deletePerson(Long personId) 
	{
		log.info("-----> deletePerson service");
		
		Person person = personRepository
				.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
		
		personRepository.delete(person);
		
		return ResponseEntity
				.ok()
				.build();
	}

	@Override
	public Person updatePerson(Long personId, Person personDetails) 
	{
		log.info("-----> updatePerson service");
		
		Person person = personRepository
						.findById(personId)
						.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
		
		person.setName(personDetails.getName());
		person.setUsername(personDetails.getUsername());
		person.setEmailPrimary(personDetails.getEmailPrimary());
		person.setEmailSecondary(personDetails.getEmailSecondary());
		person.setPhone(personDetails.getPhone());
		person.setGender(personDetails.getGender());
		person.setAge(personDetails.getAge());
		person.setPassword(personDetails.getPassword());
		person.setDob(personDetails.getDob());
		person.setIsAdult(personDetails.getIsAdult());		

		return personRepository.save(person);
	}

	@Override
	public List<Person> getPersonsByGender(String gender) 
	{
		log.info("-----> getPersonsByGender service");
		
		return personRepository.getPersonByGender(gender);
	}
}
