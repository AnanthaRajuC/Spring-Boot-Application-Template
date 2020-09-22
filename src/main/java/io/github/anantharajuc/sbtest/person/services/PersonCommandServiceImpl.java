package io.github.anantharajuc.sbtest.person.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.exception.ResourceNotFoundException;
import io.github.anantharajuc.sbtest.person.model.Person;
import io.github.anantharajuc.sbtest.person.repositories.PersonRepository;
import lombok.extern.log4j.Log4j2;

/**
 * Person Command Service Implementation.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@Service
public class PersonCommandServiceImpl implements PersonCommandService
{
	@Autowired
	private PersonRepository personRepository;

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
}
