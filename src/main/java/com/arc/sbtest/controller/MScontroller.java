package com.arc.sbtest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MScontroller 
{
	private static final Logger logger = LogManager.getLogger(MScontroller.class);
	
	@GetMapping("/generic-hello")
	public ResponseEntity<String> noReqestParameter() 
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("CustomResponseHeader1", "CustomResponseHeaderValue1");
		responseHeaders.set("Custom-ResponseHeader-2", "Custom-Response-Header-Value-2");
		return new ResponseEntity<String>("Welcome to generic greetings", responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/personalized-hello")
	public String singleReqestParameter(@RequestParam(name="name", required=false, defaultValue="World") String name) 
	{
	    return "hello "+name;
	}
	
	@GetMapping("/loggers")
	public ResponseEntity<String> logMethod()
	{
		logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
        logger.fatal("Damn! Fatal error. Please fix me.");
        
		return new ResponseEntity<String>("logging", HttpStatus.OK);
	}
}
