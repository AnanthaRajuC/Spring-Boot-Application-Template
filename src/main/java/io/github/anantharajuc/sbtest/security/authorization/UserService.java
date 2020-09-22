package io.github.anantharajuc.sbtest.security.authorization;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

/**
 * Spring Security - User Service
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
public interface UserService 
{
	void createUser(User user);
	
	Optional<User> getUserByUsername(String username);

	ResponseEntity<?> deleteUser(String username);  
	
	List<User> getAllUsers();
}
