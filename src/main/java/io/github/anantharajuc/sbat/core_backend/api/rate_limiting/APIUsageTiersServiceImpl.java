package io.github.anantharajuc.sbat.core_backend.api.rate_limiting;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;

/**
 * Class that implements the API usage plans service methods.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 * @since 25/09/2020
 */
@Service
public class APIUsageTiersServiceImpl implements APIUsageTiersService
{
	private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

	/**
	 * @see APIUsageTiersService#resolveBucket(String)
	 */
	@Override
	public Bucket resolveBucket(String apiKey) 
	{
		return cache.computeIfAbsent(apiKey, this::newBucket);
	}

	/**
	 * @see APIUsageTiersService#newBucket(String)
	 */
	@Override
	public Bucket newBucket(String apiKey) 
	{
		APIUsageTiersEnum pricingPlan = APIUsageTiersEnum.resolvePlanFromApiKey(apiKey);
		
		return bucket(pricingPlan.getLimit());
	}

	/**
	 * @see APIUsageTiersService#bucket(Bandwidth)
	 */
	@Override
	public Bucket bucket(Bandwidth limit) 
	{		
		return Bucket4j.builder().addLimit(limit).build();
	}
}
