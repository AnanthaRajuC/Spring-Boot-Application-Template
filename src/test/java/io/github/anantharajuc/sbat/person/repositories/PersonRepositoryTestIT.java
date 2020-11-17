package io.github.anantharajuc.sbat.person.repositories;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.sbat.app.user.model.Address;
import io.github.anantharajuc.sbat.app.user.model.GenderEnum;
import io.github.anantharajuc.sbat.app.user.model.Geo;
import io.github.anantharajuc.sbat.app.user.model.Person;
import io.github.anantharajuc.sbat.app.user.repositories.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonRepositoryTestIT
{
	@Autowired
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
	public void findById_savedBooking_savedBookingFound() 
	{
		// given
	    Person savedPerson = createPerson();
	    // when
	    Optional<Person> foundBooking = personRepository.findById(savedPerson.getId());
	    // then
	    assertThat(foundBooking).hasValue(savedPerson); 
	}
}
