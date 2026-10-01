package io.github.anantharajuc.sbat.web.controllers;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbat.core_backend.service.impl.PostmanEchoServiceImpl;

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
	@Operation(summary="Simple Postman GET request.", description="Simple Postman GET request.")
	public void getRequest()
	{
		postmanEchoServiceImpl.getRequest();
	}
	
	@GetMapping(value="/POSTrequest")
	@Operation(summary="Simple Postman POST request.", description="Simple Postman POST request.")
	public void postFormData()
	{
		postmanEchoServiceImpl.postFormData();
	}
}
