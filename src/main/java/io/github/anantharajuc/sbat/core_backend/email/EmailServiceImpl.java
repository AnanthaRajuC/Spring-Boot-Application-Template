package io.github.anantharajuc.sbat.core_backend.email;

import java.util.Date;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import io.github.anantharajuc.sbat.core_backend.service.impl.OtherServicesImpl;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class EmailServiceImpl implements EmailService
{
	private final TemplateEngine templateEngine;
	private final JavaMailSender javaMailSender;
	private final JavaMailSenderImpl javaMailSenderImpl;
	private final OtherServicesImpl otherServicesImpl;
	
	@Override
	public String mailContentBuilder(String mailContent) 
	{
		Context context = new Context();
		
		context.setVariable("message", mailContent);

		return templateEngine.process("mailTemplate", context);
	}

	@Override
	@Async
	public void sendMail(Email notificationEmail) 
	{
		otherServicesImpl.loadApplicationSettings();
		
		Dotenv dotenv = Dotenv.load();

		javaMailSenderImpl.setUsername(dotenv.get("MAIL_USERNAME", "Unable to fetch MAIL_USERNAME")); 
		javaMailSenderImpl.setPassword(dotenv.get("MAIL_PASSWORD", "Unable to fetch MAIL_PASSWORD"));
		javaMailSenderImpl.setPort(Integer.parseInt(dotenv.get("MAIL_PORT", "Unable to fetch MAIL_PORT")));
		javaMailSenderImpl.setProtocol(dotenv.get("MAIL_PROTOCOL", "Unable to fetch MAIL_PROTOCOL"));
		javaMailSenderImpl.setHost(dotenv.get("MAIL_HOST", "Unable to fetch MAIL_HOST"));
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {
														          	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

														            messageHelper.setFrom(otherServicesImpl.getMailFrom());
														            messageHelper.setTo(notificationEmail.getRecipient());
														            messageHelper.setSubject(notificationEmail.getSubject());
														            messageHelper.setText(notificationEmail.getBody());
														            messageHelper.setReplyTo(otherServicesImpl.getMailReplyTo());
														            messageHelper.setSentDate(new Date());
														         };
        
        try 
        {
            javaMailSender.send(messagePreparator);
            
            log.info("Activation email sent!!");
        } 
        catch (MailException e) 
        {
            log.error("Exception occurred when sending mail", e);
        }
	}
}