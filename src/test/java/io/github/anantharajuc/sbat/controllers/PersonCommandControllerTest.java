/*package io.github.anantharajuc.sbat.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.github.anantharajuc.sbat.app.user.model.Address;
import io.github.anantharajuc.sbat.app.user.model.GenderEnum;
import io.github.anantharajuc.sbat.app.user.model.Geo;
import io.github.anantharajuc.sbat.app.user.model.Person;
import io.github.anantharajuc.sbat.app.user.repositories.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class PersonCommandControllerTest 
{
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
	

	private static final String URL 			= "/api/v1/person";
	
	private HttpHeaders headers;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PersonRepository personRepository;
	
	
	
	@BeforeAll
	private void setUp() 
	{
		headers = new HttpHeaders();
        headers.set("X-api-key", "FX001-ZBSY6YSLP");
	}
	
	@Test
	@Order(1)
	public void testSave() throws Exception 
	{
		BDDMockito.given(personRepository.save(Mockito.any(Person.class))).willReturn(getMockPerson());
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(NAME, USERNAME, PHONE, EMAIL_PRIMARY, EMAIL_SECONDARY, GENDER, AGE, PASSWORD, DOB, IS_ADULT, ADDRESS))
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
			.headers(headers))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.name").value(NAME));
	}
	
	private String getJsonPayload(String name, String username, Long phone, String emailPrimary,String emailSecondary, GenderEnum gender, int age, String password, LocalDate dob, Boolean isAdult, Address address) throws JsonProcessingException 
	{
		
		Person person = new Person(name, username, phone, emailPrimary, emailSecondary, gender, age, password, dob, isAdult, address);
	        
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		return mapper.writeValueAsString(person);
	}
	
	private Person getMockPerson()
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
}
*/