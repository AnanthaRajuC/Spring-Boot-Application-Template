package io.github.anantharajuc.sbat.core_backend.security.user.model;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=false)
public class UserLogin extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	String username;
    String password;
}
