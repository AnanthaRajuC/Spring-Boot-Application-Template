package io.github.anantharajuc.sbat.person.services;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.anantharajuc.sbat.app.user.model.Address;
import io.github.anantharajuc.sbat.app.user.model.GenderEnum;
import io.github.anantharajuc.sbat.app.user.model.Geo;
import io.github.anantharajuc.sbat.app.user.model.Person;
import io.github.anantharajuc.sbat.app.user.model.dto.PersonDTO;
import io.github.anantharajuc.sbat.app.user.repositories.PersonRepository;
import io.github.anantharajuc.sbat.app.user.services.PersonQueryServiceImpl;

/*
 * Test class for {@link PersonQueryController}
 *
 * @author Anantha Raju c
 */
@RunWith(SpringRunner.class)
@SpringBootTest() 
public class PersonQueryServiceImplTest 
{
	@Autowired
	private PersonQueryServiceImpl personQueryServiceImpl;
	
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	/*@Test
	public void getPersonByIdTest()
	{		
		Person person = createPerson();
		PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
		
		Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person)); 
		assertThat(personQueryServiceImpl.getPersonById(1L)).isEqualTo(personDTO);
	}*/
	
	@Test
	public void getAllPersonsTest()
	{	
		Person person1 = createPerson();

		
		Date date = new Date();
		LocalDate dob = LocalDate.of(2017, 1, 13);
		
		Person person2 = new Person();
		
		person2.setId((long) 1);
		person2.setCreatedDate(date);		
		person2.setName("Stevie Nicks");
		person2.setUsername("stevienicks");		
		person2.setEmailPrimary("stevienicks@gmail.com");
		person2.setEmailSecondary("nicksstevie@yahoo.co.in"); 
		person2.setPhone(9191919191L);
		person2.setGender(GenderEnum.FEMALE);
		person2.setAge(29);
		person2.setPassword("abcd12345");
		person2.setDob(dob);
		person2.setIsAdult(true); 
		
		Address address2 = new Address();

		address2.setStreet("Jane Plains");
		address2.setSuite("Suite 779");
		address2.setCity("Wisokyburghh");
		address2.setZipcode("90565-7771");
		
		Geo geo2 = new Geo();	
		
		geo2.setLat("-43.9589");
		geo2.setLng("-34.4628");
		
		address2.setGeo(geo2);
		person2.setAddress(address2);
		
		List<Person> personList = new ArrayList<>();
		
		personList.add(person1);
		personList.add(person2);
		
		Mockito.when(personRepository.findAll()).thenReturn(personList);
		
		assertThat(personQueryServiceImpl.getAllPersons()).isEqualTo(personList);
	}
}
