package io.github.anantharajuc.sbtest.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import io.github.anantharajuc.sbtest.model.Person;

public interface PersonService 
{
	List<Person> getAllPersons();
	
	Person getPersonById(Long personId);
	
	Person createPerson(Person person);
	
	ResponseEntity<?> deletePerson(Long personId);
	
	Person updatePerson(Long personId, Person personDetails);
}
