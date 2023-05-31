package com.ait.IES.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailsUtil {
	
	private Logger logger = LoggerFactory.getLogger(MailsUtil.class);
	
			
	@Autowired
	private JavaMailSender mailSender; 
	
	
	public boolean sendMail(String subject, String body, String to) {
		logger.info("Mime message created");
		MimeMessage sme = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(sme);
		try {
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
		logger.info("successfully send to email !");
		return true;
	}
	
}
