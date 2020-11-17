package io.github.anantharajuc.sbat.backend.email;

public interface EmailService 
{
	String mailContentBuilder(String mailContent);
	
	void sendMail(Email notificationEmail);
}