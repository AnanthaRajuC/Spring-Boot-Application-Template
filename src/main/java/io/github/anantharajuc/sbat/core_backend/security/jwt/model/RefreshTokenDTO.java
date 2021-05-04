package io.github.anantharajuc.sbat.core_backend.security.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
@JsonIgnoreProperties({"createdDate", "createdBy","lastModifiedDate","lastModifiedBy"}) 
public class RefreshTokenDTO extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	String token;
	String username;
}
