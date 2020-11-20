/*package io.github.anantharajuc.sbat.app.user.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.anantharajuc.sbat.app.user.model.Address;
import io.github.anantharajuc.sbat.app.user.model.GenderEnum;
import io.github.anantharajuc.sbat.app.user.model.Geo;
import io.github.anantharajuc.sbat.app.user.model.Person;
import io.github.anantharajuc.sbat.app.user.repositories.PersonRepository;
import io.github.anantharajuc.sbat.backend.api.ResourcePaths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PersonCommandControllerTest 
{
	private static final String ROOT = ResourcePaths.Person.V1.ROOT;
	
	private static final Long ID 				= 1L;
	private static final String NAME 			= "Stevie Nicks";
	private static final String USERNAME 		= "stevienicks";
	private static final String EMAIL_PRIMARY 	= "ghijkl@gmail.com";
	private static final String EMAIL_SECONDARY = "abcdef@gmail.com";
	private static final Long PHONE 			= 9191919191L;
	private static final GenderEnum GENDER 		= GenderEnum.FEMALE;
	private static final int AGE 				= 70;
	private static final String PASSWORD 		= "12@!ABCDE";
	private static final LocalDate DOB 			= LocalDate.of(2017, 1, 13);
	private static final Date CREATED_DATE      = new Date();
	private static final Boolean IS_ADULT 		= true;
	
	private static final String STREET 			= "Jane Plains";
	private static final String CITY 			= "Wisokyburghh";
	private static final String SUITE 			= "Suite 779";
	private static final String ZIP_CODE 		= "90565-7771";
	
	private static final String LAT 			= "-43.9589";
	private static final String LNG 			= "-34.4628";
	private static final Geo GEO 				= new Geo(LAT, LNG, null); 
	
	private static final Address ADDRESS        = new Address(STREET, SUITE, CITY, ZIP_CODE,GEO);
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	PersonRepository personRepository;
	
	@Before
	public void cleanup()
	{
		personRepository.deleteAll();
	}
	
	@Test
	public void postPerson_whenUserIsValid_receiveOk()
	{
		Person person = createValidPerson(); 
		
		ResponseEntity<Object> responseEntity = testRestTemplate.postForEntity("/api/v1/person",person,Object.class);
	 
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private Person createValidPerson() 
	{
		Person person = new Person();
		
		person.setId(ID);
		person.setCreatedDate(CREATED_DATE);		
		person.setName(NAME);
		person.setUsername(USERNAME);		
		person.setEmailPrimary(EMAIL_PRIMARY);
		person.setEmailSecondary(EMAIL_SECONDARY); 
		person.setPhone(PHONE);
		person.setGender(GENDER);
		person.setAge(AGE);
		person.setPassword(PASSWORD);
		person.setDob(DOB);
		person.setIsAdult(IS_ADULT); 
		person.setAddress(ADDRESS);
		
		return person;
	}
	
	@Test
	public void postPerson_whenUserIsValid_userSavedToDatabase()
	{
		Person person = createValidPerson(); 
		
		testRestTemplate.postForEntity(ROOT,person,Object.class);
		
		assertThat(personRepository.count()).isEqualTo(1);
		 
	}
}
*/