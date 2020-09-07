package io.github.anantharajuc.sbtest.auth;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByUsername(@NotBlank String username);
}
