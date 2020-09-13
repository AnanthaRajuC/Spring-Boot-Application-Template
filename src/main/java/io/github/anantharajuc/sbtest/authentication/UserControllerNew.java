package io.github.anantharajuc.sbtest.authentication;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllerNew 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/person")
	public void createUser(@Valid @RequestBody User user)
	{		
		userServiceImpl.createUser(user);
	}
	
	@GetMapping(value="/person/{username}")
	public Optional<User> getPersonByUsername(@PathVariable(value = "username") String username)
	{		
		return userServiceImpl.getUserByUsername(username);
	}
}
