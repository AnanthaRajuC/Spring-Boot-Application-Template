package io.github.anantharajuc.sbat.core_backend.security.user.authorization;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.core_backend.infra.exception.ResourceNotFoundException;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

/**
 * Spring Security - User Service Implementation
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Service
public class RBACuserServiceImpl implements RBACuserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createUser(User user) 
	{
		log.info("-----> createUser service");	
		
		userRepository.save(user);
	}

	@Override
	public Optional<User> getUserByUsername(String username) 
	{
		log.info("-----> getUserByUsername service");	

		return userRepository.findByUsername(username);
	}
	
	@Override
	public ResponseEntity<?> deleteUser(String username) 
	{
		log.info("-----> deletePerson service");
		
		User user = userRepository
				.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		userRepository.delete(user);
		
		return ResponseEntity
				.ok()
				.build();
	}

	@Override
	public List<User> getAllUsers() 
	{
		return userRepository.findAll();
	}
}
