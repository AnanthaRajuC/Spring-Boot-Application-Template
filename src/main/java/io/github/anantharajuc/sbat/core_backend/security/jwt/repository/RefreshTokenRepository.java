package io.github.anantharajuc.sbat.core_backend.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbat.core_backend.security.jwt.model.RefreshToken;

import java.util.Optional;

/**
 * Repository class for <code>RefreshToken</code> domain object. 
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> 
{
    Optional<RefreshToken> findByToken(String token);
    
    Optional<RefreshToken> findByUsername(String username);

    void deleteByToken(String token);
}