package com.prolosys.rfid.microservices.users.bootstrap;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
//@Profile("development")
@Component
public class Boostrap {

	private static final Logger LOGGER = LoggerFactory.getLogger(Boostrap.class);

	@EventListener
	public void ApplicationReadyEvent(ApplicationReadyEvent event) throws ParseException, InterruptedException, ExecutionException {
		
		DataMaster dataMaster = new DataMaster();
		
		dataMaster.generateRootUser();
		dataMaster.generateAdminUser();

		LOGGER.info("Se inicializan datos maestros");

	}

}