package io.github.anantharajuc.sbtest.authentication;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface UserService 
{
	void createUser(User user);
	
	Optional<User> getUserByUsername(String username);

	ResponseEntity<?> deleteUser(String username);  
}
