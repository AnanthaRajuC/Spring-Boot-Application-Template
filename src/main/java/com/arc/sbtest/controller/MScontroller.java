package com.arc.sbtest.controller;

import java.util.List;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arc.sbtest.model.Person;
import com.arc.sbtest.repository.PersonRepository;
import com.arc.sbtest.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class MScontroller 
{
	@Autowired
	PersonRepository personRepository;
	
	private static final Logger logger = LogManager.getLogger(MScontroller.class);
	
	@GetMapping("/persons")
	public List<Person> getAllPersons() 
	{
		return  personRepository.findAll();
	}
	
	@PostMapping("/persons")
	public Person createPerson(@Valid @RequestBody Person person)
	{
		return personRepository.save(person);		
	}
	 
	@GetMapping("/persons/{id}")
	public Person getPersonById(@PathVariable(value = "id") Long personId)
	{
		return personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
	}
	
	@PutMapping("/persons/{id}")
	public Person updatePerson(@PathVariable(value = "id") Long personId,@Valid @RequestBody Person personeDetails)
	{
		Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
		
		person.setName(personeDetails.getName());

		return personRepository.save(person);		
	}
	
	@GetMapping("/generic-hello")
	public ResponseEntity<String> noReqestParameter() 
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("CustomResponseHeader1", "CustomResponseHeaderValue1");
		responseHeaders.set("Custom-ResponseHeader-2", "Custom-Response-Header-Value-2");
		return new ResponseEntity<>("Welcome to generic greetings", responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/personalized-hello")
	public String singleReqestParameter(@RequestParam(name="name", required=false, defaultValue="World") String name) 
	{
	    return "hello "+name;
	}
	
	@GetMapping("/loggers")
	public ResponseEntity<String> logMethod()
	{
		logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
        logger.fatal("Damn! Fatal error. Please fix me.");
        
		return new ResponseEntity<>("logging", HttpStatus.OK);
	}
}
