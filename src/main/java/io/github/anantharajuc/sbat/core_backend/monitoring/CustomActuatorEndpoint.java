package io.github.anantharajuc.sbat.core_backend.monitoring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id="customActuatorEndpoint")
@Component
public class CustomActuatorEndpoint 
{
	@ReadOperation
	public Map<String, String> customEndpoint()
	{
		Map<String, String> map = new HashMap<>();
		
		map.put("Add you key here","Add the value here");
		
		return map;
	}
}
