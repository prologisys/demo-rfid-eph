package com.prolosys.rfid.common.mail.services;

public interface MailService {
	
	public void send(String from, String to, String subject, String body);

}
