package io.github.anantharajuc.sbat.core_backend.security.jwt.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;

@Data
@Entity
@Table(name="sbat_auth_refresh_token") 
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class RefreshToken extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="token")
	String token;
	
	@Column(name="username")
	String username;
}