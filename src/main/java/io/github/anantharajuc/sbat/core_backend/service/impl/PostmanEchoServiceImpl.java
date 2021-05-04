package io.github.anantharajuc.sbat.core_backend.service.impl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.core_backend.service.PostmanEchoService;
import io.github.anantharajuc.sbat.core_backend.util.OkHttpSingleton;
import lombok.extern.log4j.Log4j2;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Log4j2
@Service
public class PostmanEchoServiceImpl implements PostmanEchoService
{
	static OkHttpClient client = OkHttpSingleton.getInstance().getClient();
	
	@Autowired
	private OtherServicesImpl otherServicesImpl;
	
	@Override
	public void getRequest() 
	{
		log.info("-----> /PostmanEcho getRequest");	
		
		Request request = new Request.Builder()
									 .url(otherServicesImpl.getPostmanEchoGETurl()+"?ApplicationnAME="+otherServicesImpl.getApplicationName())
								     .method("GET", null)
								     .addHeader("Cookie", "sails.sid=s%3APLDFuP7QxkbuyeAZ831jljwlw01nGeEo.LVk8c4dIg1sUPSIZn7XOyDHZiCFt7koynBy21xpX0uo")
								     .build();
	    
		Response response;
		
		try 
		{
			response = client.newCall(request).execute();
			
			String getRequestResponse = response.body().string();
			log.info(getRequestResponse);
			
			JSONObject jo = new JSONObject(getRequestResponse); 
			
			log.info(jo.get("args"));

		} 
		catch (IOException | JSONException e) 
		{ 
			log.error( "PostmanEcho getRequest failed!", e );
		}			    
	}

	@Override
	public void postFormData() 
	{
		log.info("-----> /PostmanEcho postFormData");	
		
		RequestBody formBody = new FormBody.Builder()
									       .add("username", "test")
									       .add("passwordf", "test")
									       .build();
		
		Request request = new Request.Builder()
									 .url(otherServicesImpl.getPostmanEchoBaseUrl()+otherServicesImpl.getPostmanEchoPOSTpath())
									 .post(formBody)
								     .build();
		
		Response response;
		
		try 
		{
			response = client.newCall(request).execute();
			
			String getPOSTformDataResponse = response.body().string();
			
			log.info(getPOSTformDataResponse);
		} 
		catch (IOException e) 
		{
			log.error( "postFormData failed!", e );
		}
	}
}
