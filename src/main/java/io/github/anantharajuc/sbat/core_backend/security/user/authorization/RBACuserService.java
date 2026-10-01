package io.github.anantharajuc.sbat.core_backend.security.user.authorization;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.user.model.dto.UserDTO;

/**
 * Spring Security - User Service
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
public interface RBACuserService
{
	UserDTO createUser(User user);

	Optional<UserDTO> getUserByUsername(String username);

	ResponseEntity<?> deleteUser(String username);

	List<UserDTO> getAllUsers();
}
