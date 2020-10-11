package io.github.anantharajuc.sbat.person.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbat.auditing.AuditEntity;
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
	Long phone;
	String username;
}
