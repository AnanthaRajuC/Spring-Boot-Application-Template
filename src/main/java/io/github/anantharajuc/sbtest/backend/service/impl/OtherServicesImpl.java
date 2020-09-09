package io.github.anantharajuc.sbtest.backend.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.ApplicationSetings;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.ApplicationSettingsRepository;
import io.github.anantharajuc.sbtest.backend.service.OtherServices;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Service
public class OtherServicesImpl implements OtherServices
{
	//Application Settings
	private String applicationName;
	private String applicationVersion;
	
	//Postman URL
	private String postmanEchoBaseUrl;
	private String postmanEchoGETurl;
	private String postmanEchoPOSTpath;
	
	@Autowired
	private ApplicationSettingsRepository applicationSettingsRepository;
		
	@Override
	public void loadApplicationSettings() 
	{
		log.info("-----> Loading Application Settings Value");
		
		List<ApplicationSetings> applicationSettingsList = applicationSettingsRepository.findAll();
		
		HashMap<String, String> applicationSettingsHashMap = new HashMap<>(); 
		
		for(int i = 0; i< applicationSettingsList.size(); i++)
		{
			applicationSettingsHashMap.put(applicationSettingsList.get(i).getAppKey(), applicationSettingsList.get(i).getAppValue());
		}
		
		setApplicationName(applicationSettingsHashMap.get("applicationName"));
		setApplicationVersion(applicationSettingsHashMap.get("applicationVersion"));
		
		setPostmanEchoBaseUrl(applicationSettingsHashMap.get("postmanEchoBaseUrl"));
		setPostmanEchoGETurl(applicationSettingsHashMap.get("postmanEchoGETurl")); 
		setPostmanEchoPOSTpath(applicationSettingsHashMap.get("postmanEchoPOSTpath"));
	}
}
