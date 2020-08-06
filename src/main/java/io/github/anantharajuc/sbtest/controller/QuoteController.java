package io.github.anantharajuc.sbtest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbtest.model.Quote;
import io.github.anantharajuc.sbtest.repository.PersonRepository;
import io.github.anantharajuc.sbtest.repository.QuoteRepository;

@RestController
@RequestMapping("/api/")
public class QuoteController 
{
	@Autowired
	private QuoteRepository quoteRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping(value="/quote/{name}")	 
	public List<Quote> getQuoteByName(@PathVariable(value="personName") String personName)
	{
		return quoteRepository.findByPersonName(personName);		
	}
	
	@PostMapping("/person/{name}/quotes")
	public Quote createQuote(@PathVariable(value="name") String name, @Valid @RequestBody Quote quote)
	{
		return quote;
		
	}
}
