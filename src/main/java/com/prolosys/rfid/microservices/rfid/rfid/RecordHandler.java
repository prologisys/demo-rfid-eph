package com.prolosys.rfid.microservices.rfid.rfid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.mot.rfid.api3.RfidEventsListener;
import com.mot.rfid.api3.RfidReadEvents;
import com.mot.rfid.api3.RfidStatusEvents;
import com.mot.rfid.api3.STATUS_EVENT_TYPE;
//import com.prologisys.scl.common.microservices.opc.models.CorelinkSapModel;
//import com.prologisys.scl.common.microservices.rfid.models.TagToRecord;
//import com.prologisys.scl.common.microservices.rfid.services.OpcService;
import com.prolosys.rfid.common.bootstrap.ApplicationContextProvider;
import com.prolosys.rfid.microservices.masters.models.RfidReaderModel;

public class RecordHandler implements RfidEventsListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecordHandler.class);
	private ApplicationContext context = ApplicationContextProvider.getApplicationContext();
//	private OpcService opcService = (OpcService) context.getBean(OpcService.class);
	private RfidReaderManager fxManager = (RfidReaderManager) context.getBean(RfidReaderManager.class);
	
	
//	private Map<TagTypeEnum, Map<String, Date>> tags = new HashMap<TagTypeEnum, Map<String, Date>>();
//	private Map<String, TagToRecord> tagsToRecord = new HashMap<String, TagToRecord>();
	
	private RfidReaderModel readerModel;
	private RfidReaderWrapper readerFX;

	public RecordHandler(RfidReaderWrapper readerFX) {
		this.readerModel = readerFX.getRfidReaderModel();
		this.readerFX = readerFX;
	}
	
	@Override
	public void eventReadNotify(RfidReadEvents event) {
		
		
//		int numeroAleatorio = (int) (Math.random()*10+1);
//		
//		try {
//			
//			TagData tagData = event.getReadEventData().tagData;
//			
//			
//			if (tagData != null && tagData.getTagID() != null) {					
//				
//				TagTypeEnum tagType = fxManager.getTagType(tagData);
//				
////				System.out.println(tagType +  " " + readerModel.getRssi() +  tagData.getTagID() + tagData.getPeakRSSI());
//
//				if(tagData.getPeakRSSI() >= readerModel.getRssi() && tagType.equals(TagTypeEnum.VIRGIN) && readerModel.getType().equals(RfidReaderTypeEnum.RECORD) &&  !readerModel.getRecording()) {
//				
//					
//					if (!fxManager.getTags().get(tagType).containsKey(tagData.getTagID())) {
//						
//						LOGGER.info(String.format("%s [Reader: %s][tag %s][%s chars] [RSSI: %s] [antena %s] [time %s] [Type: %s]",numeroAleatorio, this.readerModel.getName(), tagData.getTagID(), tagData.getTagID().length(),   tagData.getPeakRSSI(),   tagData.getAntennaID(), tagData.SeenTime.getUTCTime().getLastSeenTimeStamp().GetCurrentTime(), tagType.toString()));
//						
//						fxManager.getTags().get(TagTypeEnum.VIRGIN).put(tagData.getTagID(), new Date());
//						
//						this.readerModel.setRecording(true);
//						
//						CorelinkSapModel opcCorelinkSapModel = opcService.findUaforRecord();
//						
//						System.out.println("tag A grabar: " + opcCorelinkSapModel);
//						
//						if(opcCorelinkSapModel!=null) {
//							
//							//Detiene el escaneo
//							readerFX.stopScanning();
//							
//							//Ejecuta el cambio de EPC del tag
//							boolean recorded = fxManager.changeEPCIDByTagId(readerFX, new TagToRecord(opcCorelinkSapModel.getLenum(), tagData.getTagID()), numeroAleatorio);
//							
////							if(recorded) {
////								fxManager.getTags().get(tagType).put(tagData.getTagID(), new Date(System.currentTimeMillis()+(1000*60)));
////							}
//							
//							fxManager.getTags().get(tagType).put(tagData.getTagID(), new Date(System.currentTimeMillis()+((1000*10)*1)));
//							
//							this.readerModel.setRecording(false);
//							
//							fxManager.rfidReaderScan(readerFX,-1);
//							
//						}else {
//							this.readerModel.setRecording(false);
//							LOGGER.info(String.format("No hay tags para grabar, se continua leyendo"));
//						}
//					}
//				}
//			}
//			
//		} catch (Exception e) {}
		
		

	}

	@Override
	public void eventStatusNotify(RfidStatusEvents e) {
		final STATUS_EVENT_TYPE statusType = e.StatusEventData.getStatusEventType();

		switch (statusType.toString()) {
		case "INVENTORY_START_EVENT":
			fxManager.getRfidReaders().get(readerModel.getId()).getRfidReaderModel().setScanning(true);
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.readerModel.getName()));
			break;
		case "INVENTORY_STOP_EVENT":
			fxManager.getRfidReaders().get(readerModel.getId()).getRfidReaderModel().setScanning(false);
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.readerModel.getName()));
			break;
		case "TEMPERATURE_ALARM_EVENT":
			LOGGER.info(String.format(
					"Evento %s en lector %s. Temp. actual: %s - Nivel de alarma: %s - Recurso de temp. %s", statusType,
					this.readerModel.getName(), e.StatusEventData.TemperatureAlarmData.getCurrentTemperature(),
					e.StatusEventData.TemperatureAlarmData.getAlarmLevel(),
					e.StatusEventData.TemperatureAlarmData.getTemperatureSource()));
			break;
		case "ACCESS_START_EVENT":
//			fxManager.getReaders().get(readerModel.getId()).getRfidReaderModel().setScanning(true);
//			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.readerModel.getName()));
			break;

		case "ACCESS_STOP_EVENT":
//			fxManager.getReaders().get(readerModel.getId()).getRfidReaderModel().setScanning(false);
//			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.readerModel.getName()));
			break;

		case "DISCONNECTION_EVENT":
			fxManager.getRfidReaders().get(readerModel.getId()).getRfidReaderModel().setScanning(false);
			
			LOGGER.info(String.format("Evento %s en lector %s. Motivo: %s", statusType, this.readerModel.getName(),
					e.StatusEventData.DisconnectionEventData.getDisconnectionEvent()));
			break;

		case "BUFFER_FULL_EVENT":
		case "BUFFER_FULL_WARNING_EVENT":
			fxManager.getRfidReaders().get(readerModel.getId()).getRfidReader().Actions.purgeTags();
			fxManager.getRfidReaders().get(readerModel.getId()).disconnect();
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.readerModel.getName()));
			break;
		case "ANTENNA_EVENT":
			LOGGER.info(String.format("Evento %s en lector %s %s- Antena: %s - Cambia estado a: %s", statusType,
					this.readerModel.getName(), this.readerModel.getIp(),
					e.StatusEventData.AntennaEventData.getAntennaID(),
					e.StatusEventData.AntennaEventData.getAntennaEvent()));
			break;
		case "READER_EXCEPTION_EVENT":
			LOGGER.info(String.format("Evento %s en lector %s - Evento: %s - Informacion: %s", statusType,
					this.readerModel.getName(),
					e.StatusEventData.ReaderExceptionEventData.getReaderExceptionEventType(),
					e.StatusEventData.ReaderExceptionEventData.getReaderExceptionEventInfo()));
			break;
		default:
			LOGGER.info("Evento en lector " + this.readerModel.getName() + ": " + statusType);
			break;
		}
	}
}
