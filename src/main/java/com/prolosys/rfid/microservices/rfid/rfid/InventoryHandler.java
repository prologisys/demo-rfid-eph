package com.prolosys.rfid.microservices.rfid.rfid;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.mot.rfid.api3.RfidEventsListener;
import com.mot.rfid.api3.RfidReadEvents;
import com.mot.rfid.api3.RfidStatusEvents;
import com.mot.rfid.api3.STATUS_EVENT_TYPE;
import com.mot.rfid.api3.TagData;
//import com.prologisys.scl.common.microservices.rfid.models.DetectedTagModel;
import com.prolosys.rfid.common.bootstrap.ApplicationContextProvider;
import com.prolosys.rfid.common.utils.UtilsPLS;
import com.prolosys.rfid.microservices.masters.models.RfidReaderModel;
import com.prolosys.rfid.microservices.rfid.constants.TagTypeEnum;

/**
 * The Class InventoryHandler.
 */
public class InventoryHandler implements RfidEventsListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryHandler.class);

	/** The context. */
	private ApplicationContext context = ApplicationContextProvider.getApplicationContext();

	/** The fx manager. */
	private RfidReaderManager fxManager = (RfidReaderManager) context.getBean(RfidReaderManager.class);

	/** The reader model. */
	private RfidReaderModel rfidReaderModel;

	/** The reader FX. */
	private RfidReaderWrapper readerFX;

	/**
	 * Instantiates a new inventory handler.
	 *
	 * @param readerFX the reader FX
	 */
	public InventoryHandler(RfidReaderWrapper readerFX) {
		this.readerFX = readerFX;
		this.rfidReaderModel = readerFX.getRfidReaderModel();
	}

	/**
	 * Event read notify.
	 *
	 * @param event the event
	 */
	@Override
	public void eventReadNotify(RfidReadEvents event) {

//		try {
//
//			TagData tagData = event.getReadEventData().tagData;
//
//			if (tagData != null && tagData.getTagID() != null) {
//
//				TagTypeEnum tagType = fxManager.getTagType(tagData);
//
//				if (!fxManager.getTags().get(tagType).containsKey(tagData.getTagID())) {
//					
//					System.out.println("tagData.getTagID()");
//					
////					fxManager.getTags().get(tagType).put(tagData.getTagID(), new Date(System.currentTimeMillis()+((1000*60)*2)));
//					fxManager.getTags().get(tagType).put(tagData.getTagID(), new Date(System.currentTimeMillis()));
//
//
//					if (tagType.equals(TagTypeEnum.ROL)) {
//
//						DetectedTagModel detectedTagModel = new DetectedTagModel();
//						detectedTagModel.setREADER(this.readerFX.getRfidReaderModel().getName());
//
//						String lenum = UtilsPLS.getLENUMFromTagId(tagData.getTagID());
//						detectedTagModel.setLENUM(lenum);
//
//						fxManager.reportToDashboardScannedTag(detectedTagModel);
//
//					}
//
//				}
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
	}

	/**
	 * Event status notify.
	 *
	 * @param e the e
	 */
	@Override
	public void eventStatusNotify(RfidStatusEvents e) {
		final STATUS_EVENT_TYPE statusType = e.StatusEventData.getStatusEventType();

		switch (statusType.toString()) {
		case "INVENTORY_START_EVENT":
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(true);
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.rfidReaderModel.getName()));
			break;
		case "INVENTORY_STOP_EVENT":
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(false);
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.rfidReaderModel.getName()));
			break;
		case "TEMPERATURE_ALARM_EVENT":
			LOGGER.info(String.format(
					"Evento %s en lector %s. Temp. actual: %s - Nivel de alarma: %s - Recurso de temp. %s", statusType,
					this.rfidReaderModel.getName(), e.StatusEventData.TemperatureAlarmData.getCurrentTemperature(),
					e.StatusEventData.TemperatureAlarmData.getAlarmLevel(),
					e.StatusEventData.TemperatureAlarmData.getTemperatureSource()));
			break;
		case "ACCESS_START_EVENT":
//			fxManager.getReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(true);
//			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.rfidReaderModel.getName()));
			break;

		case "ACCESS_STOP_EVENT":
//			fxManager.getReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(false);
//			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.rfidReaderModel.getName()));
			break;

		case "DISCONNECTION_EVENT":
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(false);

			LOGGER.info(String.format("Evento %s en lector %s. Motivo: %s", statusType, this.rfidReaderModel.getName(),
					e.StatusEventData.DisconnectionEventData.getDisconnectionEvent()));
			break;

		case "BUFFER_FULL_EVENT":
		case "BUFFER_FULL_WARNING_EVENT":
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).getRfidReader().Actions.purgeTags();
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.rfidReaderModel.getName()));
			break;
		case "ANTENNA_EVENT":
			LOGGER.info(String.format("Evento %s en lector %s %s- Antena: %s - Cambia estado a: %s", statusType,
					this.rfidReaderModel.getName(), this.rfidReaderModel.getIp(),
					e.StatusEventData.AntennaEventData.getAntennaID(),
					e.StatusEventData.AntennaEventData.getAntennaEvent()));
			break;
		case "READER_EXCEPTION_EVENT":
			LOGGER.info(String.format("Evento %s en lector %s - Evento: %s - Informacion: %s", statusType,
					this.rfidReaderModel.getName(),
					e.StatusEventData.ReaderExceptionEventData.getReaderExceptionEventType(),
					e.StatusEventData.ReaderExceptionEventData.getReaderExceptionEventInfo()));
			break;
		default:
			LOGGER.info("Evento en lector " + this.rfidReaderModel.getName() + ": " + statusType);
			break;
		}
	}
}
