package io.github.anantharajuc.sbat.core_backend.security.jwt.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.FetchType.LAZY;

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
