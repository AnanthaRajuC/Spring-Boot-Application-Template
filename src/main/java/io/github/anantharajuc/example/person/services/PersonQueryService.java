package io.github.anantharajuc.example.person.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.anantharajuc.example.person.model.Person;

/**
 * Person Query Service.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
public interface PersonQueryService 
{	
	List<Person> getPersonsByGender(String gender);

	Person getPersonById(Long personId);

	Page<Person> getAllPersonsPageable(Pageable pageable); 
	
	Page<Person> findPaginated(Pageable pageable);
}
