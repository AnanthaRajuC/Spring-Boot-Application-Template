package io.github.anantharajuc.sbtest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.anantharajuc.sbtest.backend.service.I18NService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=io.github.anantharajuc.sbtest.backend.service.I18NService.class)
public class I18NServiceTest 
{
	@Autowired
	private I18NService i18NService;
	
	@Test
	public void testMessageByLocalService() throws Exception
	{
		String expectedResult = "Spring Boot Application Template";
		String messageId = "index.main.callout";
		System.out.println("messageId : "+messageId);
		String actual = i18NService.getMessage(messageId);
		Assert.assertEquals(expectedResult, expectedResult); 
	}
}
