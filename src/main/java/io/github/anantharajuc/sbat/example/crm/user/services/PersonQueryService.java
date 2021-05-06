package io.github.anantharajuc.sbat.example.crm.user.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.anantharajuc.sbat.example.crm.user.model.Person;
import io.github.anantharajuc.sbat.example.crm.user.model.dto.PersonDTO;

/**
 * Person Query Service.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
public interface PersonQueryService 
{
	List<Person> getAllPersons();
	
	List<Person> getPersonsByGender(String gender);

	PersonDTO getPersonById(Long personId); 

	Page<Person> getAllPersonsPageable(Pageable pageable); 
	
	Page<Person> findPaginated(Pageable pageable);
	
	List<Person> getPersonByUsername(String username);
}
