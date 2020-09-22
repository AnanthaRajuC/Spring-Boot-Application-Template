/*package io.github.anantharajuc.sbtest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes=io.github.anantharajuc.sbtest.security.ApplicationSecurityConfiguration.class) 
@AutoConfigureMockMvc
public class TestSBtemplateApplication 
{
	@Autowired
	private MockMvc mockMvc;
	
	@Before
    public void setup() 
	{
        mockMvc = MockMvcBuilders.standaloneSetup().build();
    }
	
	@Test
	public void loginWithValidUserThenAuthenticated() throws Exception 
	{
		FormLoginRequestBuilder login = formLogin()
											.user("johndoe")
											.password("password");

		mockMvc.perform(login)
			   .andExpect(authenticated().withUsername("johndoe"));
	}
	
	@Test
	public void accessUnsecuredResourceThenOk() throws Exception 
	{
		mockMvc.perform(get("/"))
			   .andExpect(status().isOk());
	}
}
*/