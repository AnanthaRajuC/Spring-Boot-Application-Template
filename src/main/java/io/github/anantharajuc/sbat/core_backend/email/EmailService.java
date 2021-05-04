package io.github.anantharajuc.sbat.core_backend.email;

public interface EmailService 
{
	String mailContentBuilder(String mailContent);
	
	void sendMail(Email notificationEmail);
}