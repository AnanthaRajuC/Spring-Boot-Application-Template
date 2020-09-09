package io.github.anantharajuc.sbtest.authentication;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByUsername(@NotBlank String username);
}
