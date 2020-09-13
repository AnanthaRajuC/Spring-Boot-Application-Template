package io.github.anantharajuc.sbtest.authentication;

import java.util.Optional;

public interface UserService 
{
	void createUser(User user);
	
	Optional<User> getUserByUsername(String username); 
}
