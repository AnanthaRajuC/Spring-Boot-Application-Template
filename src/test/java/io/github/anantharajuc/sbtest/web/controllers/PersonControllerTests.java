/*package io.github.anantharajuc.sbtest.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Address;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Geo;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Person;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.PersonRepository;
import io.github.anantharajuc.sbtest.enums.GenderEnum;

*//**
 * Test class for {@link PersonController}
 *
 * @author Anantha Raju c
 *//*
@WebMvcTest(PersonController.class)
public class PersonControllerTests 
{
	private static final long TEST_PERSON_ID = 1L;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonRepository personRepository;
	
	private Person stevieNicks;
	
	@BeforeEach
	void setup() 
	{
		Date date = new Date();
		LocalDate dob = LocalDate.of(2017, 1, 13);
		
		stevieNicks.setId(TEST_PERSON_ID);
		stevieNicks.setCreatedDate(date);
		stevieNicks.setLastModifiedDate(date);
		stevieNicks.setCreatedBy("johndoe");
		stevieNicks.setLastModifiedBy("johndoe"); 
		stevieNicks.setName("Stevie Nicks");
		stevieNicks.setUsername("stevienicks");		
		stevieNicks.setEmailPrimary("stevienicks@gmail.com");
		stevieNicks.setEmailSecondary("nicksstevie@yahoo.co.in"); 
		stevieNicks.setPhone(9191919191L);
		stevieNicks.setGender(GenderEnum.FEMALE);
		stevieNicks.setAge(29);
		stevieNicks.setPassword("abcd12345");
		stevieNicks.setDob(dob);
		stevieNicks.setIsAdult(true); 
		
		Address stevieNicksAddress = new Address(); 

		stevieNicksAddress.setStreet("Jane Plains");
		stevieNicksAddress.setSuite("Suite 779");
		stevieNicksAddress.setCity("Wisokyburghh");
		stevieNicksAddress.setZipcode("90565-7771");
		
		Geo stevieNicksAddressGeo = new Geo();
		
		stevieNicksAddressGeo.setLat("-43.9589");
		stevieNicksAddressGeo.setLng("-34.4628");
		
		stevieNicksAddress.setGeo(stevieNicksAddressGeo);
		
		stevieNicks.setAddress(stevieNicksAddress); 
	}
}
*/