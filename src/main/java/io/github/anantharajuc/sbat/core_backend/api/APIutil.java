package io.github.anantharajuc.sbat.core_backend.api;

/**
 * Class that implements the API utility methods.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 * @since 25/09/2020
 */
public class APIutil 
{
	private APIutil() 
	{
		
	}
	
   /**
	* Field to represent API version on the requests/responses header
	*/
	public static final String HEADER_PERSON_API_VERSION = "sbat-api-version";
	
   /**
	* Field to represent API key on the requests/responses header
	*/
	public static final String HEADER_API_KEY = "X-api-key";
	
   /**
	* Field to represent API Rate Limit Remaining on the requests/responses header
	*/
	public static final String HEADER_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
	
   /**
	* Field to represent API Rate Limit Retry After Seconds on the requests/responses header
	*/
	public static final String HEADER_RETRY_AFTER = "X-Rate-Limit-Retry-After-Seconds";
}
