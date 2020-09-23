package io.github.anantharajuc.sbtest.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LoginAttemptService 
{
    private final int MAX_ATTEMPT = 2;
    
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() 
    {    	
        super();
        
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() 
        {
            @Override
            public Integer load(final String key) 
            {
                return 0;
            }
        });
    }

    public void loginSucceeded(final String key) 
    {
    	log.info("-----> attemptsCache.invalidate(key)");
    	
        attemptsCache.invalidate(key);
    }

    public void loginFailed(final String key) 
    {
    	log.info("-----> LoginAttemptService loginFailed : ");
    	
        int attempts = 0;
        
        try 
        {
            attempts = attemptsCache.get(key);
        } 
        catch (final ExecutionException e) 
        {
            attempts = 0;
        }
        
        attempts++;
        
        log.info("attempts : "+attempts);
        
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(final String key) 
    {
    	log.info("-----> LoginAttemptService isBlocked : ");
    	
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