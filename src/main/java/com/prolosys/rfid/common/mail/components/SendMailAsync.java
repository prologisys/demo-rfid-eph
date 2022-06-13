package com.prolosys.rfid.common.mail.components;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope("prototype")
public class SendMailAsync implements Runnable {
	
	@Getter
	@Setter
	private String from;
	@Getter
	@Setter
	private String to;
	@Getter
	@Setter
	private String subject;
	@Getter
	@Setter
	private String body;

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@LocalServerPort
	private int serverPort;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMailAsync.class);

	@Override
	public void run() {
		
		MimeMessage mail = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			javaMailSender.send(mail);
			
			LOGGER.info("Se ha enviado un correo electrónico a " + to + " con el asunto " + subject);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
