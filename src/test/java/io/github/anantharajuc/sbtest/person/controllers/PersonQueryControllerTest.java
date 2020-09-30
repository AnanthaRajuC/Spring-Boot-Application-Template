package io.github.anantharajuc.sbtest.person.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(controllers=PersonQueryController.class)
public class PersonQueryControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@Before
    public void setup() 
	{
        mockMvc = MockMvcBuilders.standaloneSetup().build();
    }
	
	@Test
	public void whenPathVariableIsInvalidThenReturnsStatus404() throws Exception 
	{
		mockMvc.perform(get("http://localhost:8080/api/v1/person/0"))
	            .andExpect(status().isNotFound());
	}
}
