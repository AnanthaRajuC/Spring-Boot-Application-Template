package io.github.anantharajuc.sbat.example.crm.user.model.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import io.github.anantharajuc.sbat.example.crm.user.controllers.PersonQueryController;

public class PersonDTOModelAssembler implements RepresentationModelAssembler<PersonDTO, EntityModel<PersonDTO>>
{
	@Override
	public EntityModel<PersonDTO> toModel(PersonDTO personDTO) 
	{
		return EntityModel.of(personDTO, 
				linkTo(methodOn(PersonQueryController.class).getPersonById(null, null, personDTO.getId())).withSelfRel(),
				linkTo(methodOn(PersonQueryController.class).getAllPersons(null, null)).withRel("persons"));
	}
}
