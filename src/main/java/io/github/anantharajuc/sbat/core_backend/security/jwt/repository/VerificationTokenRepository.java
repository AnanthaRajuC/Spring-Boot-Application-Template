package io.github.anantharajuc.sbat.core_backend.security.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbat.core_backend.security.jwt.model.VerificationToken;

/**
 * Repository class for <code>VerificationToken</code> domain object. 
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> 
{
	Optional<VerificationToken> findByToken(String token);
}
