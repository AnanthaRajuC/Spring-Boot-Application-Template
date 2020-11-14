package io.github.anantharajuc.sbat.email;

public interface EmailService 
{
	String mailContentBuilder(String mailContent);
	
	void sendMail(Email notificationEmail);
}