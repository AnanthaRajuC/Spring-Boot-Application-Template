package io.github.anantharajuc.sbtest.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;

import io.github.anantharajuc.sbtest.security.UserPrincipal;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController 
{
	@Autowired
    private SessionRegistry sessionRegistry;
	 
	public List<String> listLoggedInUsers() 
	{
		List<String> listLoggedInUsers = new ArrayList<String>();  
		
		log.info("------------------ Currently loggedin users");
		
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for (final Object principal : allPrincipals) 
        {
            if (principal instanceof UserPrincipal) 
            {
                final UserPrincipal user = (UserPrincipal) principal;

                List<SessionInformation> activeUserSessions = sessionRegistry.getAllSessions(principal,
                                /* includeExpiredSessions */ false); // Should not return null;

                if (!activeUserSessions.isEmpty()) 
                {
                	listLoggedInUsers.add(user.getUsername());
                	
                	log.info(user.getUsername());
                }
            }
        }
        
		return listLoggedInUsers; 
    }
}
