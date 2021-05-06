package io.github.anantharajuc.sbat.example.crm.user.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import io.github.anantharajuc.sbat.core_backend.api.APIutil;
import io.github.anantharajuc.sbat.core_backend.api.ResourcePaths;
import io.github.anantharajuc.sbat.example.crm.user.model.Person;
import io.github.anantharajuc.sbat.example.crm.user.model.PersonModelAssembler;
import io.github.anantharajuc.sbat.example.crm.user.model.dto.PersonDTOModelAssembler;
import io.github.anantharajuc.sbat.example.crm.user.services.PersonQueryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
 * Person Query Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping(value=ResourcePaths.Person.V1.ROOT)
@CacheConfig(cacheNames={"person"})
@Api(value="PersonQuery", tags="Person Query")
public class PersonQueryController 
{
	@Autowired
	private PersonQueryServiceImpl personQueryServiceImpl;
	
	@Autowired
    private PagedResourcesAssembler<Person> pagedResourcesAssembler;
	
	private final PersonModelAssembler personModelAssembler;
	
	private final PersonDTOModelAssembler personDTOModelAssembler;
	
	@Autowired
    public PersonQueryController(PersonModelAssembler personModelAssembler, PersonDTOModelAssembler personDTOModelAssembler) 
	{
        this.personModelAssembler = personModelAssembler;
        this.personDTOModelAssembler = personDTOModelAssembler; 
    }
	
	/*
	 * Method that returns all persons from the datastore 
	 * 
	 * 
	 * HTTP Status:
	 * 
	 * 200 - OK: Everything worked as expected.
	 */
	@Cacheable()
	@GetMapping()	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value="Find all persons", notes="Returns all Person's in the data store.")
	public ResponseEntity<CollectionModel<EntityModel<Person>>> getAllPersons(@RequestHeader(defaultValue="${api.version}") String apiVersion,
			                                                                  @RequestHeader(value=APIutil.HEADER_API_KEY, defaultValue="${api.key}") String apiKey) 
	{		
		List<EntityModel<Person>> persons = personQueryServiceImpl.getAllPersons().stream().map(personModelAssembler::toModel).collect(Collectors.toList());

		return ResponseEntity
				.ok()
				.header(APIutil.HEADER_PERSON_API_VERSION, apiVersion) 
                .header(APIutil.HEADER_API_KEY, apiKey)
                .body(CollectionModel.of(persons, linkTo(methodOn(PersonQueryController.class).getAllPersons(null, null)).withSelfRel()));
                
	}
	
	/*
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
	@GetMapping(value=ResourcePaths.ID)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ADMIN','PERSON') and hasAuthority('PERSON_READ')")
	@ApiOperation(httpMethod="GET", value = "Find person by ID", notes = "Returns a person for the given ID",response = Person.class)
	@ApiResponse(code = 400, message = "Invalid ID supplied")
	public ResponseEntity<Object> getPersonById(@RequestHeader(defaultValue="${api.version}") String apiVersion, 
			                                    @RequestHeader(value=APIutil.HEADER_API_KEY, defaultValue="${api.key}") String apiKey, 
			                                    @PathVariable(value="id") Long personId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 
		  
		headers.add(APIutil.HEADER_PERSON_API_VERSION, apiVersion);
		headers.add(APIutil.HEADER_API_KEY, apiKey);

		return new ResponseEntity<>(personDTOModelAssembler.toModel(personQueryServiceImpl.getPersonById(personId)), headers, HttpStatus.OK);
	}
	
	@GetMapping(value=ResourcePaths.USERNAME)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#username == authentication.principal.username")
	@ApiOperation(httpMethod="GET", value = "Find person by Username", notes = "Returns a person for the given username",response = Person.class)
	@ApiResponse(code = 400, message = "Invalid Username supplied")
	public ResponseEntity<Object> getPersonByName(@RequestHeader(defaultValue="${api.version}") String apiVersion, 
            									  @RequestHeader(value=APIutil.HEADER_API_KEY, defaultValue="${api.key}") String apiKey, 
                                                  @PathVariable(value="username") String username)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 
		  
		headers.add(APIutil.HEADER_PERSON_API_VERSION, apiVersion);
		headers.add(APIutil.HEADER_API_KEY, apiKey);

		return new ResponseEntity<>(personQueryServiceImpl.getPersonByUsername(username), headers, HttpStatus.OK); 
	}
}