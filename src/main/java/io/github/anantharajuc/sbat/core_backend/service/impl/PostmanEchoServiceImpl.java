package io.github.anantharajuc.sbat.core_backend.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbat.core_backend.service.PostmanEchoService;
import lombok.extern.log4j.Log4j2;

/**
 * Sample outbound HTTP calls to https://postman-echo.com using Spring's {@link RestClient}.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Log4j2
@Service
public class PostmanEchoServiceImpl implements PostmanEchoService
{
	private final RestClient restClient;
	private final OtherServicesImpl otherServicesImpl;

	public PostmanEchoServiceImpl(RestClient.Builder restClientBuilder, OtherServicesImpl otherServicesImpl)
	{
		this.restClient = restClientBuilder.build();
		this.otherServicesImpl = otherServicesImpl;
	}

	@Override
	public void getRequest()
	{
		log.info("-----> /PostmanEcho getRequest");

		try
		{
			EchoResponse response = restClient.get()
					.uri(otherServicesImpl.getPostmanEchoGETurl() + "?applicationName={name}", otherServicesImpl.getApplicationName())
					.retrieve()
					.body(EchoResponse.class);

			log.info("PostmanEcho GET args : {}", response == null ? null : response.args());
		}
		catch (RestClientException e)
		{
			log.error("PostmanEcho getRequest failed!", e);
		}
	}

	@Override
	public void postFormData()
	{
		log.info("-----> /PostmanEcho postFormData");

		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("username", "test");
		form.add("password", "test");

		try
		{
			EchoResponse response = restClient.post()
					.uri(otherServicesImpl.getPostmanEchoBaseUrl() + otherServicesImpl.getPostmanEchoPOSTpath())
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.body(form)
					.retrieve()
					.body(EchoResponse.class);

			log.info("PostmanEcho POST form : {}", response == null ? null : response.form());
		}
		catch (RestClientException e)
		{
			log.error("PostmanEcho postFormData failed!", e);
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	record EchoResponse(Object args, Object form)
	{
	}
}
