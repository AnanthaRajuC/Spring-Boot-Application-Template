package io.github.anantharajuc.sbtest.person.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import io.github.anantharajuc.sbtest.person.controllers.PersonQueryController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>>
{
	@Override
	public EntityModel<Person> toModel(Person person) 
	{
		return EntityModel.of(person, linkTo(methodOn(PersonQueryController.class).getPersonById(null, null, person.getId())).withSelfRel()); 
	}
}
