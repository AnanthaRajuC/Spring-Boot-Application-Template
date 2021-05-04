package io.github.anantharajuc.sbat.core_backend.api.rate_limiting;

import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Enum that classifies the usage tiers in the API.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@AllArgsConstructor
public enum APIUsageTiersEnum 
{
	FREE(25),
    BASIC(50),
    PROFESSIONAL(75);
	
	private int bucketCapacity;
	
	/**
     * Method to get the Bandwidth, that is composed by the bucket capacity and the refill interval.
     * 
     * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
     * 
     * @return <code>Bandwidth</code> object
     */
    public Bandwidth getLimit() 
    {
    	log.info("-----> API Rate Limiting : Bandwidth : "+Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofMinutes(20))));
    	
    	return Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofMinutes(20)));
    	
    	//return Bandwidth.simple(1, Duration.ofSeconds(30));
    }
    
    /**
     * Method to get the bucket capacity.
     * 
     * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
     * 
     * @return int
     */
    public int getBucketCapacity() 
    {
    	log.info("-----> API Rate Limiting : bucketCapacity : "+bucketCapacity);
        
        return bucketCapacity;
    }
    
    /**
     * Method to get the right plan by looking the apiKey prefix.
     * 
     * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
     * 
     * @param apiKey API Key
     * @return <code>APIUsagePlansEnum</code>
     */
    public static APIUsageTiersEnum resolvePlanFromApiKey(String apiKey) 
    {        
    	if (apiKey == null || apiKey.isEmpty()) 
    	{
    		log.info("-----> API Rate Limiting : API Usage Tier : FREE 25");
            return FREE;
        
        } 
    	else if (apiKey.startsWith("PX001-")) 
    	{
    		log.info("-----> API Rate Limiting : API Usage Tier : PROFESSIONAL 75");
            return PROFESSIONAL;
            
        } 
    	else if (apiKey.startsWith("BX001-")) 
    	{
    		log.info("-----> API Rate Limiting : API Usage Tier : BASIC 50");
            return BASIC;
        }
    	
        return FREE;
    }
}

