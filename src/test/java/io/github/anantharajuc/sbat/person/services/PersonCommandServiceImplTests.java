package io.github.anantharajuc.sbat.person.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.anantharajuc.sbat.app.user.model.Address;
import io.github.anantharajuc.sbat.app.user.model.GenderEnum;
import io.github.anantharajuc.sbat.app.user.model.Geo;
import io.github.anantharajuc.sbat.app.user.model.Person;
import io.github.anantharajuc.sbat.app.user.repositories.PersonRepository;
import io.github.anantharajuc.sbat.app.user.services.PersonCommandServiceImpl;

/*
 * Test class for {@link PersonCommandServiceImpl}
 *
 * @author Anantha Raju c
 */
@RunWith(SpringRunner.class)
@SpringBootTest() 
public class PersonCommandServiceImplTests 
{
	@Autowired
	private PersonCommandServiceImpl personCommandServiceImpl; 
	
	@MockBean
	private PersonRepository personRepository;
	
	private Person createPerson()
	{
		Date date = new Date();
		LocalDate dob = LocalDate.of(2017, 1, 13);
		
		Person person = new Person();
		
		person.setId((long) 1);
		person.setCreatedDate(date);		
		person.setName("Stevie Nicks");
		person.setUsername("stevienicks");		
		person.setEmailPrimary("stevienicks@gmail.com");
		person.setEmailSecondary("nicksstevie@yahoo.co.in"); 
		person.setPhone(9191919191L);
		person.setGender(GenderEnum.FEMALE);
		person.setAge(29);
		person.setPassword("abcd12345");
		person.setDob(dob);
		person.setIsAdult(true); 
		
		Address address = new Address();

		address.setStreet("Jane Plains");
		address.setSuite("Suite 779");
		address.setCity("Wisokyburghh");
		address.setZipcode("90565-7771");
		
		Geo geo = new Geo();
		
		geo.setLat("-43.9589");
		geo.setLng("-34.4628");
		
		address.setGeo(geo);
		person.setAddress(address); 
		
		return person;
	}
	
	@Test
	public void createPersonTest()
	{
		Person person = createPerson();
		 
		Mockito.when(personRepository.save(person)).thenReturn(person);
		
		assertThat(personCommandServiceImpl.createPerson(person)).isEqualTo(person);
	}
	
	@Test
	public void deletePersonTest()
	{
		
		Person person = createPerson();
		
		Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		Mockito.when(personRepository.existsById(person.getId())).thenReturn(false);
		
		assertFalse(personRepository.existsById(person.getId())); 
	}
	
	@Test
	public void updatePersonTest()
	{
		Person person = createPerson();
		
		Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		
		person.setName("Stevie Nicks Updated Name");
		
		Mockito.when(personRepository.save(person)).thenReturn(person);
		
		assertThat(personCommandServiceImpl.updatePerson((long) 1, person)).isEqualTo(person);
	}

}
