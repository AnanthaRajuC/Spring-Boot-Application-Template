package io.github.anantharajuc.sbtest.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Person;

/**
 * Person Service.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
public interface PersonService 
{
	List<Person> getAllPersons();
	
	List<Person> getPersonsByGender(String gender);

	Person getPersonById(Long personId);
	
	Person createPerson(Person person);
	
	ResponseEntity<?> deletePerson(Long personId);
	
	Person updatePerson(Long personId, Person personDetails);

	Page<Person> getAllPersonsPageable(Pageable pageable); 
	
	Page<Person> findPaginated(Pageable pageable);
}
