package io.github.anantharajuc.sbtest.person.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.exception.ResourceNotFoundException;
import io.github.anantharajuc.sbtest.person.model.Person;
import io.github.anantharajuc.sbtest.person.repositories.PersonRepository;
import lombok.extern.log4j.Log4j2;

/**
 * Person Query Service Implementation.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@Service
public class PersonQueryServiceImpl implements PersonQueryService
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
	public List<Person> getPersonsByGender(String gender) 
	{
		log.info("-----> getPersonsByGender service");
		
		return personRepository.getPersonByGender(gender);
	}
	
	@Override
	public Page<Person> findPaginated(Pageable pageable)
	{
		int pageSize    = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem   = currentPage * pageSize;
        
        List<Person> list;
        
        List<Person> persons = personRepository.findAll();
        
        if (persons.size() < startItem) 
        {
            list = Collections.emptyList();
        } 
        else 
        {
            int toIndex = Math.min(startItem + pageSize, persons.size());
            list = persons.subList(startItem, toIndex);
        }
        
        Page<Person> personPage = new PageImpl<Person>(list, PageRequest.of(currentPage, pageSize), persons.size());

		return personPage;
	}
}
