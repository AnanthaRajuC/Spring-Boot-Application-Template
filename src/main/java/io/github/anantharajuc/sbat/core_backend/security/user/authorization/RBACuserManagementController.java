package io.github.anantharajuc.sbat.core_backend.security.user.authorization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbat.core_backend.security.user.model.Permission;
import io.github.anantharajuc.sbat.core_backend.security.user.model.Role;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rbac")
public class RBACuserManagementController 
{
	@Autowired
	private RBACuserServiceImpl userServiceImpl;
	
	@PostMapping("/user")
	public void createUser(@Valid @RequestBody User user)
	{ 		
		List<Role> roles = user.getRoles();
		
		log.info("-----> User Name : "+user.getUsername());
		log.info("-----> Roles Count : "+roles.size());
		
		for(int i = 0; i < roles.size(); i++)
		{
			log.info("-----> Role Name : "+roles.get(i).getName()); 
			
			if(roles.get(i).getPermissions().isEmpty())
			{
				log.info("-----> Role doesn't have permissions explicitly defined : ");

				String resource = roles.get(i).getName().replace("ROLE_", "");
				
				log.info("-----> Resource : "+resource); 
				
				ArrayList<Permission> permission = new ArrayList<>();
				
				permission.add(new Permission(resource+"_CREATE"));
				permission.add(new Permission(resource+"_READ"));
				permission.add(new Permission(resource+"_UPDATE"));
				permission.add(new Permission(resource+"_DELETE"));
				
				roles.get(i).setPermissions(permission);
			}
			else
			{
				log.info("-----> Role has permissions explicitly defined : "); 
			}
		}

		log.info("-----> user.getUsername() : "+user.getUsername());
	
		userServiceImpl.createUser(user);
	}
	
	@GetMapping(value="/user/{username}")
	public Optional<User> getPersonByUsername(@PathVariable(value = "username") String username)
	{
		log.info("-----> Getting RBAC User : "+username);
		
		return userServiceImpl.getUserByUsername(username);
	}
	
	@GetMapping(value="/user")
	public List<User> getusers() 
	{
		log.info("-----> Getting All RBAC Users.");
		
		return userServiceImpl.getAllUsers();
	}
	
	@DeleteMapping("/user/{username}")
	public ResponseEntity<?> deletePerson(@PathVariable(value="username") String username) 
	{
		log.info("-----> Delete RBAC User : "+username);
		
		return userServiceImpl.deleteUser(username);
	}
}
