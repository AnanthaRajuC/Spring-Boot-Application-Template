package io.github.anantharajuc.sbat.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbat.core_backend.service.impl.PostmanEchoServiceImpl;
import io.swagger.annotations.ApiOperation;

/**
 * Postman Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping("/api/postman-echo")
public class PostmanEchoController 
{
	@Autowired
	private PostmanEchoServiceImpl postmanEchoServiceImpl;
	
	@GetMapping(value="/GETrequest")
	@ApiOperation(httpMethod="GET", value="Simple Postman GET request.", notes="Simple Postman GET request.")
	public void getRequest()
	{
		postmanEchoServiceImpl.getRequest();
	}
	
	@GetMapping(value="/POSTrequest")
	@ApiOperation(httpMethod="POST", value="Simple Postman POST request.", notes = "Simple Postman POST request.")
	public void postFormData()
	{
		postmanEchoServiceImpl.postFormData();
	}
}
