package io.github.anantharajuc.sbat.core_backend.api.rate_limiting;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

/**
 * Class that implements the API usage plans service methods.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 * @since 25/09/2020
 */
@Service
public class APIUsageTiersServiceImpl implements APIUsageTiersService
{
	// Bounded, so clients sending random API keys cannot exhaust the memory.
	private final Cache<String, Bucket> cache = Caffeine.newBuilder()
			.maximumSize(10_000)
			.expireAfterAccess(Duration.ofHours(1))
			.build();

	/**
	 * @see APIUsageTiersService#resolveBucket(String)
	 */
	@Override
	public Bucket resolveBucket(String apiKey) 
	{
		return cache.get(apiKey, this::newBucket);
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
		return Bucket.builder().addLimit(limit).build();
	}
}
