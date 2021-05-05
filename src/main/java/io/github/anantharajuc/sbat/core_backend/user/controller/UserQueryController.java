package io.github.anantharajuc.sbat.core_backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbat.core_backend.api.APIutil;
import io.github.anantharajuc.sbat.core_backend.api.ResourcePaths;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.user.service.UserQueryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/*
 * User Query Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping(value=ResourcePaths.User.V1.ROOT)
@Api(value="UserQuery", tags="User Query")
public class UserQueryController 
{
	@Autowired
	private UserQueryServiceImpl userQueryServiceImpl;
	
	@GetMapping(value=ResourcePaths.USERNAME)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#username == authentication.principal.username")
	@ApiOperation(httpMethod="GET", value = "Find user by Username", notes = "Returns a user for the given username",response = User.class)
	@ApiResponse(code = 400, message = "Invalid Username supplied")
	public ResponseEntity<Object> getUserByUsername(@RequestHeader(defaultValue="${api.version}") String apiVersion, 
			  										@RequestHeader(value=APIutil.HEADER_API_KEY, defaultValue="${api.key}") String apiKey, 
			  										@PathVariable(value="username") String username)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 
		  
		headers.add(APIutil.HEADER_PERSON_API_VERSION, apiVersion);
		headers.add(APIutil.HEADER_API_KEY, apiKey);
		
		return new ResponseEntity<>(userQueryServiceImpl.getUserByUsername(username), headers, HttpStatus.OK);  
	}
}
