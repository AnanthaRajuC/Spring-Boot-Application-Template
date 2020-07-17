package io.github.anantharajuc.sbtest.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.anantharajuc.sbtest.model.Person;
import io.github.anantharajuc.sbtest.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=io.github.anantharajuc.sbtest.service.PersonServiceImpl.class) 
public class PersonServiceImplTest 
{
	@Autowired
	private PersonServiceImpl personServiceImpl;
	
	@MockBean
	private PersonRepository personRepository;
	
	@Test
	public void getAllPersonsTest()
	{
		Date date = new Date();
				
		Person person1  = new Person();
		
		person1.setAge(29);
		person1.setGender("Female");
		person1.setName("Stevie Nicks");
		person1.setCreatedOn(date);
		
		Person person2  = new Person();
		
		person2.setAge(30);
		person2.setGender("Male");
		person2.setName("Mac");
		person2.setCreatedOn(date);
		
		
		List<Person> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);
		
		Mockito.when(personRepository.findAll()).thenReturn(personList);
		
		assertThat(personServiceImpl.getAllPersons()).isEqualTo(personList);
	}
}
