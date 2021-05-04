package io.github.anantharajuc.sbat.core_backend.api.rate_limiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

/**
 * Interface that provides methods for manipulating API Usage Tiers.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 * @since 25/09/2020
 */
public interface APIUsageTiersService 
{
	/**
	 * Method that links a bucket in an apiKey.
	 * 
	 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
	 * @since 25/09/2020
	 * 
	 * @param apiKey API Key
	 * @return <code>Bucket</code> object
	 */
	public Bucket resolveBucket(String apiKey);
	
	/**
	 * Method that creates a bucket for a specific apiKey.
	 * 
	 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
	 * 
	 * @param apiKey API Key
	 * @return <code>Bucket</code> object
	 */
	public Bucket newBucket(String apiKey);
	
	/**
	 * Method that creates the new builder of in-memory buckets, 
	 * adding a limited bandwidth.
	 * 
	 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
	 * 
	 * @param limit API Rate Limit
	 * @return <code>Bucket</code> object
	 */
	public Bucket bucket(Bandwidth limit);
}
