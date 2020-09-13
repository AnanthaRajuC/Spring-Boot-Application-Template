package io.github.anantharajuc.sbtest.authentication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
	List<Permission> findByName(String roleName);

}
