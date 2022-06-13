//package com.prologisys.scl.common.microservices.rfid.events;
//
//import java.text.ParseException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import com.prologisys.scl.common.microservices.catalogs.services.RfidReaderService;
//import com.prologisys.scl.common.microservices.rfid.rfid.RfidReaderManager;
//
//@Component
//public class EventStartSpring {
//	
//	@Autowired
//	RfidReaderManager rfidFxManager;
//
//	@Autowired
//	private RfidReaderService readerService;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(EventStartSpring.class);
//
//	@EventListener
//	public void ApplicationReadyEvent(ApplicationReadyEvent event) throws ParseException {
//
////		List<ReaderModel> readersModel = readerServiceImpl.findAll();
////
////		for (ReaderModel readerModel : readersModel) {
////			rfidManager.putReader(readerModel);
////			if(readerModel.isEnabled()) {
////				rfidManager.updateMessageByName("Iniciará la conexión pronto.", readerModel.getName());
////				rfidManager.connectInReader();
////			}else {
////				rfidManager.updateMessageByName("Se conectará cuando se habilite.", readerModel.getName());
////			}
////			
////			rfidManager.updateIsScanningByName(false, readerModel.getName());
////		}
////
////		LOGGER.info("Se inicializó los lectores en memoria");
//	}
//}
