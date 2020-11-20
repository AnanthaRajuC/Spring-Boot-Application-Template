package io.github.anantharajuc.sbat.backend.email;

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

import io.github.anantharajuc.sbat.backend.service.impl.OtherServicesImpl;
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

		javaMailSenderImpl.setUsername(otherServicesImpl.getSpringMailUsername()); 
		javaMailSenderImpl.setPassword(otherServicesImpl.getSpringMailPassword());
		javaMailSenderImpl.setPort(otherServicesImpl.getSpringMailPort());
		javaMailSenderImpl.setProtocol(otherServicesImpl.getSpringMailProtocol());
		javaMailSenderImpl.setHost(otherServicesImpl.getSpringMailHost());
		
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