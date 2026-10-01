package io.github.anantharajuc.sbat.core_backend.security.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbat.core_backend.security.user.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
	Optional<Role> findByName(String roleName);

}
