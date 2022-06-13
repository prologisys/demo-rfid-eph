package com.prolosys.rfid.common.mail.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.prolosys.rfid.common.mail.components.SendMailAsync;
import com.prolosys.rfid.common.mail.services.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private ApplicationContext applicationContext;
	 
    @Autowired
    private TaskExecutor taskExecutor;

	@Override
	public void send(String from, String to, String subject, String body) {
		
		SendMailAsync sendMailAsync = applicationContext.getBean(SendMailAsync.class);
		
		sendMailAsync.setFrom(from);
		sendMailAsync.setTo(to);
		sendMailAsync.setSubject(subject);
		sendMailAsync.setBody(body);

        taskExecutor.execute(sendMailAsync);
        
	}

}
