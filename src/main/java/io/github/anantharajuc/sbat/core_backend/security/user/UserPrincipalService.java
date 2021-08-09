package io.github.anantharajuc.sbat.core_backend.security.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.core_backend.infra.exception.FailedLoginAttemptException;
import io.github.anantharajuc.sbat.core_backend.security.user.authentication.LoginAttemptService;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

/**
 * User Principal Service
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Service
public class UserPrincipalService implements UserDetailsService
{
	@Autowired
	UserRepository appUserRepository;
 
    @Autowired
    private LoginAttemptService loginAttemptService;
 
    @Autowired
    private HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String username)
	{
		log.info("-----> loadUserByUsername  : "+username);
		
		String ip = getClientIP();
		
        if (loginAttemptService.isBlocked(ip)) 
        {
        	log.info("-----> Your IP has been blocked for 2 minute for 2 consecutive failed login attempts.");
        	
        	throw new FailedLoginAttemptException();
        }
		
		User user = appUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
		
		return new UserPrincipal(user);
	}
	
	private String getClientIP() 
	{
        final String xfHeader = request.getHeader("X-Forwarded-For");
        
        if (xfHeader != null) 
        {
            return xfHeader.split(",")[0];
        }
        
        log.info("-----> getClientIP  : "+request.getRemoteAddr());
        
        return request.getRemoteAddr();
    }
}
