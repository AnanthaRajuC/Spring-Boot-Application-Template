package io.github.anantharajuc.sbtest.person.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbtest.person.model.Person;
import io.github.anantharajuc.sbtest.person.services.PersonQueryServiceImpl;
import io.github.anantharajuc.sbtest.util.APIutil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Person Query Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping("/api/v1")
@CacheConfig(cacheNames={"person"})
@Api(value="PersonQuery", tags="Person Query")
public class PersonQueryController 
{
	@Autowired
	private PersonQueryServiceImpl personQueryImpl;
	
	@Cacheable()
	@GetMapping(value="/person")	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value="Find all persons", notes="Returns all Person's in the data store.")
	public ResponseEntity<List<Person>> getAllPersons() 
	{		
		return new ResponseEntity<>(personQueryImpl.getAllPersons(), HttpStatus.OK);
	}
	
	@GetMapping(value="/person/pageable")	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value="Find all persons via Paging", notes="Returns all Person's in the data store via Paging.")
	public ResponseEntity<Page<Person>> getAllPersons(Pageable pageable) 
	{		
		return new ResponseEntity<>(personQueryImpl.getAllPersonsPageable(pageable), HttpStatus.OK);
	}

	@GetMapping(value="/person/gender/{gender}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value="Find all persons based on Gender", notes="Returns all Person's in the data store.")
	public ResponseEntity<List<Person>> getPersonByGender(@PathVariable(value = "gender") String gender)
	{
		return new ResponseEntity<>(personQueryImpl.getPersonsByGender(gender), HttpStatus.OK);
	}
	
	/**
	 * Method that search a person by the id.
	 * 
	 * @param apiVersion - API version at the moment
	 * @param personId - the id of the person
	 * 
	 * @return ResponseEntity with a <code>Person</code> object and the HTTP status
	 * 
	 * HTTP Status:
	 * 
	 * 200 - OK: Everything worked as expected.
	 */
	@GetMapping(value="/person/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value = "Find person by ID", notes = "Returns a person for the given ID",response = Person.class)
	@ApiResponse(code = 400, message = "Invalid ID supplied")
	public ResponseEntity<Person> getPersonById(@RequestHeader(defaultValue="${api.version}") String apiVersion, @PathVariable(value = "id") Long personId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(APIutil.HEADER_PERSON_API_VERSION, apiVersion);
		
		return new ResponseEntity<>(personQueryImpl.getPersonById(personId), headers, HttpStatus.OK);
	}
}
