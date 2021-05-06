package io.github.anantharajuc.sbat.example.crm.user.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.github.anantharajuc.sbat.example.crm.user.model.Address;
import io.github.anantharajuc.sbat.example.crm.user.model.GenderEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
@JsonIgnoreProperties({"createdDate", "createdBy","lastModifiedDate","lastModifiedBy"}) 
public class PersonDTO extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	String name;
	String username;
	Long phone;
	String emailPrimary;
	String emailSecondary;
	GenderEnum gender;
	int age;
	LocalDate dob;
	Boolean isAdult;
	@JsonIgnoreProperties({"createdDate", "createdBy","lastModifiedDate","lastModifiedBy"}) 
	Address address;
}
