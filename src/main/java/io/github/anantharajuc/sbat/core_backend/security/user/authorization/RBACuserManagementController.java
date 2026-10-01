package io.github.anantharajuc.sbat.core_backend.security.user.authorization;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.user.model.dto.UserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Role based access control user management. Restricted to ROLE_ADMIN, see ApplicationSecurityConfiguration.
 */
@Log4j2
@RestController
@RequestMapping("/rbac")
@AllArgsConstructor
@Tag(name="RBAC User Management")
public class RBACuserManagementController
{
	private final RBACuserServiceImpl userServiceImpl;

	/**
	 * Creates a user. Roles are referenced by name; a role that does not exist yet is created, and when it has no
	 * permissions listed it gets the CREATE, READ, UPDATE and DELETE permissions of the resource it is named after.
	 */
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createUser(@Valid @RequestBody User user)
	{
		log.info("-----> Create RBAC User : {}", user.getUsername());

		return userServiceImpl.createUser(user);
	}

	@GetMapping(value="/user/{username}")
	public Optional<UserDTO> getPersonByUsername(@PathVariable(value = "username") String username)
	{
		return userServiceImpl.getUserByUsername(username);
	}

	@GetMapping(value="/user")
	public List<UserDTO> getusers()
	{
		return userServiceImpl.getAllUsers();
	}

	@DeleteMapping("/user/{username}")
	public ResponseEntity<?> deletePerson(@PathVariable(value="username") String username)
	{
		log.info("-----> Delete RBAC User : {}", username);

		return userServiceImpl.deleteUser(username);
	}
}
