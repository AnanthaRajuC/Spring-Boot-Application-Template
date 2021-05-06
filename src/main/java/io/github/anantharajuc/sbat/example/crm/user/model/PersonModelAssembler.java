package io.github.anantharajuc.sbat.example.crm.user.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import io.github.anantharajuc.sbat.example.crm.user.controllers.PersonQueryController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>>
{
	@Override
	public EntityModel<Person> toModel(Person person) 
	{
		return EntityModel.of(person, 
				linkTo(methodOn(PersonQueryController.class).getPersonById(null, null, person.getId())).withSelfRel(),
				linkTo(methodOn(PersonQueryController.class).getAllPersons(null, null)).withRel("persons"));
	}

	public Object toModel(List<Person> allPersons) 
	{	
		return CollectionModel.of(allPersons, linkTo(methodOn(PersonQueryController.class).getAllPersons(null, null)).withSelfRel()); 
	}
}
