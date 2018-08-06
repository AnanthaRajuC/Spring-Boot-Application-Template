package com.arc.sbtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MScontroller 
{
	@GetMapping("/generic-hello")
	public String noReqestParameter() 
	{
	    return "hello from micro service controller";
	}
	
	@GetMapping("/personalized-hello")
	public String singleReqestParameter(@RequestParam(name="name", required=false, defaultValue="World") String name) 
	{
	    return "hello "+name;
	}
}
