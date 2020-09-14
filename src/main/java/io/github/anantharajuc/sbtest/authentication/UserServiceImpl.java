package io.github.anantharajuc.sbtest.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements UserService
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
}
