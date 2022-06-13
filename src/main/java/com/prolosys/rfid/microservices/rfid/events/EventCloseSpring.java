//package com.prologisys.scl.common.microservices.rfid.events;
//
//import java.util.Map.Entry;
//
//import javax.annotation.PreDestroy;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import com.prologisys.scl.common.microservices.rfid.rfid.RfidReaderAsync;
//import com.prologisys.scl.common.microservices.rfid.rfid.RfidReaderManager;
//import com.prologisys.scl.common.microservices.rfid.rfid.RfidReaderWrapper;
//
//@Configuration
//public class EventCloseSpring {
//	
//	@Autowired
//	RfidReaderManager rfidManager;
//	
//	@Autowired
//	private RfidReaderAsync asyncFX;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(EventCloseSpring.class);
//	private int SECONDS_WAIT = 5;
//
//	@PreDestroy
//	public void onShutDown() {
//
//		LOGGER.info("Se esperará "+SECONDS_WAIT+" seg. mientras se detiene el escaneo y se desconecta todos los lectores");
//		
//		for (Entry<Long, RfidReaderWrapper> entry : rfidManager.getRfidReaders().entrySet()) {
//			
//			RfidReaderWrapper readerFX = entry.getValue();
//			try {
//				
//				if (readerFX.getRfidReader().isConnected()) {
//					
//					asyncFX.stopScanning(readerFX);
//					asyncFX.logout(readerFX);
//					asyncFX.disconnect(readerFX);
//				}
//
//			} catch (Exception e) {}
//		}
//		
//		try {
//			
//			Thread.sleep(1000*SECONDS_WAIT);
//		} catch (InterruptedException e) {
//
//		}finally {
//			LOGGER.info("Los lectores se han detenido");
//		}
//		
//	}
//}
