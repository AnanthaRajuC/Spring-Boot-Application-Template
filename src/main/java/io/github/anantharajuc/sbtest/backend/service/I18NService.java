package io.github.anantharajuc.sbtest.backend.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class I18NService 
{
	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String messageId)
	{
		System.out.println("messageId "+messageId );
		Locale locale = LocaleContextHolder.getLocale();
		System.out.println("locale "+locale );
		return getMessage(messageId, locale); 
	}
	
	public String getMessage(String messageId, Locale locale)
	{
		return messageSource.getMessage(messageId, null, locale);
	}
}
