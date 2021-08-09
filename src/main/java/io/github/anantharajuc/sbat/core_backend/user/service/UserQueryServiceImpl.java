package io.github.anantharajuc.sbat.core_backend.user.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.core_backend.infra.exception.ResourceNotFoundException;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import io.github.anantharajuc.sbat.core_backend.user.model.dto.UserDTO;
import lombok.extern.log4j.Log4j2;

/*
 * User Query Service Implementation.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@Service("UserQueryServiceImpl")
public class UserQueryServiceImpl implements UserQueryService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO getUserByUsername(String username) 
	{
		log.info("-----> getUserByUsername service");
		
		Optional<User> userOptional = userRepository.findByUsername(username);
		
		if(!userOptional.isPresent()) 
		{
			throw new ResourceNotFoundException("User", "username", username);
		}
		
		return modelMapper.map(userOptional.get(), UserDTO.class); 
	}
}
