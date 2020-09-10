package io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidatorTests 
{
	@Before
	private Validator createValidator() 
	{
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		
		return localValidatorFactoryBean;
	}
	
	@Test
	void shouldNotValidateWhenUsernameEmpty() 
	{
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		
		Person person = new Person();
		
		person.setUsername("madonna");

		Validator validator = createValidator();
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations).hasSize(1);
		
		ConstraintViolation<Person> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
		assertThat(violation.getMessage()).isEqualTo("username must not be empty.");
	}
}
