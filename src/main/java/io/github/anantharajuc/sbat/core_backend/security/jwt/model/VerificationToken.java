package io.github.anantharajuc.sbat.core_backend.security.jwt.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sbat_auth_verification_token")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class VerificationToken extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="token")
	String token;
	
    @Column(name="expiry_date")
    Instant expiryDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    VerificationTokenEnum status;
	
    @OneToOne(fetch=LAZY)
    User user;
}
