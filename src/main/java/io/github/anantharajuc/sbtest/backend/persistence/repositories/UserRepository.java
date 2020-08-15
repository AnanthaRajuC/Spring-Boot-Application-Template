package io.github.anantharajuc.sbtest.backend.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByEmail(String email);
}
