package io.github.anantharajuc.sbat.core_backend.user.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.github.anantharajuc.sbat.core_backend.security.user.model.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
@JsonIgnoreProperties({"createdDate","lastModifiedDate","lastModifiedBy","password","isAccountNonExpired","isAccountNonLocked","isCredentialsNonExpired","isEnabled"}) 
public class UserDTO extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	String username;
	String email;
	@JsonIgnoreProperties({"createdDate", "createdBy","lastModifiedDate","lastModifiedBy"}) 
	private List<Role> roles;

}
