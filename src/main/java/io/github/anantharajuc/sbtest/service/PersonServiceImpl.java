package io.github.anantharajuc.sbtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.model.Person;
import io.github.anantharajuc.sbtest.repository.PersonRepository;
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
}
