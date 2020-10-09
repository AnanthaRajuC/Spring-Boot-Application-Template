package io.github.anantharajuc.sbat.person.services;

import org.springframework.http.ResponseEntity;

import io.github.anantharajuc.sbat.person.model.Person;

/**
 * Person Command Service.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
public interface PersonCommandService 
{
	Person createPerson(Person person);
	
	ResponseEntity<?> deletePerson(Long personId);
	
	Person updatePerson(Long personId, Person personDetails);
}
