package io.github.anantharajuc.sbat.backend.security.authorization;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import io.github.anantharajuc.sbat.backend.security.user.model.User;

/**
 * Spring Security - User Service
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
public interface RBACuserService 
{
	void createUser(User user);
	
	Optional<User> getUserByUsername(String username);

	ResponseEntity<?> deleteUser(String username);  
	
	List<User> getAllUsers();
}
