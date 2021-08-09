package io.github.anantharajuc.sbat.core_backend.infra.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.anantharajuc.sbat.example.crm.user.model.dto.PersonDTOModelAssembler;

@Configuration
public class DTOModelMapperConfig 
{
	@Bean
	public ModelMapper modelMapper() 
	{
		return new ModelMapper();
	}
	
	@Bean
	public PersonDTOModelAssembler personDTOModelAssembler() 
	{
		return new PersonDTOModelAssembler();
	}
}
