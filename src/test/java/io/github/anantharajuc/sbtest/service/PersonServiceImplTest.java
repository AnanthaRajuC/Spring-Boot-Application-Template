package io.github.anantharajuc.sbtest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.Address;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.Geo;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.Person;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.PersonRepository;
import io.github.anantharajuc.sbtest.backend.service.PersonServiceImpl;
import io.github.anantharajuc.sbtest.enums.GenderEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=io.github.anantharajuc.sbtest.backend.service.PersonServiceImpl.class) 
public class PersonServiceImplTest 
{
	@Autowired
	private PersonServiceImpl personServiceImpl;
	
	@MockBean
	private PersonRepository personRepository;
	
	@Test
	public void createPersonTest()
	{
		Date date = new Date();
		LocalDate dob = LocalDate.of(2017, 1, 13);  
		
		Person person  = new Person();
		
		person.setId((long) 1);
		person.setCreatedOn(date);		
		person.setName("Stevie Nicks");
		person.setUsername("stevie-nicks");		
		person.setEmailPrimary("jane1.howell@gmail.com");
		person.setEmailSecondary("jane1.howell@yahoo.co.in"); 
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
		 
		Mockito.when(personRepository.save(person)).thenReturn(person);
		
		assertThat(personServiceImpl.createPerson(person)).isEqualTo(person);
	}
	
	@Test
	public void deletePersonTest()
	{
		Date date = new Date();
		
		Person person  = new Person();
		
		person.setId((long) 1);
		person.setAge(29);
		person.setGender(GenderEnum.FEMALE);
		person.setName("Stevie Nicks");
		person.setCreatedOn(date);
		
		Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		Mockito.when(personRepository.existsById(person.getId())).thenReturn(false);
		assertFalse(personRepository.existsById(person.getId())); 
	}
	
	@Test
	public void updatePersonTest()
	{
		Date date = new Date();
		
		Person person  = new Person();
		
		person.setId((long) 1);
		person.setAge(29);
		person.setGender(GenderEnum.FEMALE);
		person.setName("Stevie Nicks");
		person.setCreatedOn(date);
		
		Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		
		person.setName("Stevie Nicks Updated Name");
		Mockito.when(personRepository.save(person)).thenReturn(person);
		
		assertThat(personServiceImpl.updatePerson((long) 1, person)).isEqualTo(person);
	}
	
	@Test
	public void getPersonByIdTest()
	{
		Date date = new Date();
		
		Person person  = new Person();
		
		person.setId((long) 1);
		person.setAge(55);
		person.setGender(GenderEnum.MALE);
		person.setName("Michael Jackson");
		person.setCreatedOn(date);
		
		Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person)); 
		assertThat(personServiceImpl.getPersonById(1L)).isEqualTo(person);
	}
	
	@Test
	public void getAllPersonsTest()
	{
		Date date = new Date();
				
		Person person1  = new Person();
		
		person1.setId((long) 1);
		person1.setAge(29);
		person1.setGender(GenderEnum.FEMALE);
		person1.setName("Stevie Nicks");
		person1.setCreatedOn(date);
		
		Person person2  = new Person();
		
		person2.setId((long) 2);
		person2.setAge(30);
		person2.setGender(GenderEnum.MALE);
		person2.setName("Mac");
		person2.setCreatedOn(date);

		List<Person> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);
		
		Mockito.when(personRepository.findAll()).thenReturn(personList);
		
		assertThat(personServiceImpl.getAllPersons()).isEqualTo(personList);
	}
}
