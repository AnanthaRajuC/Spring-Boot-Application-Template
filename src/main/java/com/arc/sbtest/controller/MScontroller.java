package com.arc.sbtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MScontroller 
{
	@GetMapping("/microservice")
	public String getAllNotes() 
	{
	    return "micro service controller";
	}
}
