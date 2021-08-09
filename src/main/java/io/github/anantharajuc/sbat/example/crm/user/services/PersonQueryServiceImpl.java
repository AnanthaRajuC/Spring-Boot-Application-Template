package io.github.anantharajuc.sbat.example.crm.user.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.core_backend.infra.exception.ResourceNotFoundException;
import io.github.anantharajuc.sbat.example.crm.user.model.Person;
import io.github.anantharajuc.sbat.example.crm.user.model.dto.PersonDTO;
import io.github.anantharajuc.sbat.example.crm.user.repositories.PersonRepository;
import lombok.extern.log4j.Log4j2;

/*
 * Person Query Service Implementation.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@Service("PersonQueryServiceImpl")
public class PersonQueryServiceImpl implements PersonQueryService
{
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	public PersonDTO getPersonById(Long personId) 
	{
		log.info("-----> getPersonById service");	
		
		Optional<Person> personOptional = personRepository.findById(personId); 
		
		if(!personOptional.isPresent()) 
		{
			throw new ResourceNotFoundException("Person", "id", personId);
		}
		
		return modelMapper.map(personOptional.get(), PersonDTO.class); 
	}
	
	@Override
	public List<Person> getPersonByUsername(String username) 
	{
		log.info("-----> getPersonByUsername service");

		return personRepository.getPersonByCreatedBy(username);
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
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        log.info("currentPrincipalName : "+currentPrincipalName);
        
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
        
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), persons.size());
	}
}