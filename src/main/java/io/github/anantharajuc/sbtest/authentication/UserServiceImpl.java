package io.github.anantharajuc.sbtest.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
