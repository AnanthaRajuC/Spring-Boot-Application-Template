package io.github.anantharajuc.sbat.core_backend.security.user.authentication;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.log4j.Log4j2;

/**
 * Login Attempt Service
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Service
public class LoginAttemptService 
{
	//Maximum failed login attempts count before account is blocked
    private static final int MAX_ATTEMPT = 2;
    
    //Duration for which an account is blocked after crossing the maximum failed login attempts 
    private static final int ACCOUNT_BLOCK_DURATION = 3;
    
    //The number of wrong attempts per IP address is stored in this cache
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() 
    {    	
        super();
        
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(ACCOUNT_BLOCK_DURATION, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() 
        {
            @Override
            public Integer load(final String key) 
            {
                return 0;
            }
        });
    }

    //Successful authentication resets the unsuccessful login attempts counter
    public void loginSucceeded(final String key) 
    {
    	log.info("-----> LoginAttemptService loginSucceeded");
    	
        attemptsCache.invalidate(key);
    }

    //Unsuccessful authentication attempt increases the number of attempts for that IP
    public void loginFailed(final String key) 
    {
    	log.info("-----> LoginAttemptService loginFailed");
    	
        int attempts = 0;
        
        try 
        {
            attempts = attemptsCache.get(key);
        } 
        catch(final ExecutionException e) 
        {
            attempts = 0;
        }
        
        attempts++;
        
        log.info("Unsuccessful Login Attempt : "+attempts+"/"+MAX_ATTEMPT);
        
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(final String key) 
    {
    	log.info("-----> LoginAttemptService isBlocked");
    	
        try 
        {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } 
        catch (final ExecutionException e) 
        {
            return false;
        }
    }
}