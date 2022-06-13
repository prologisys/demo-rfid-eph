package com.prolosys.rfid.microservices.rfid.rfid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.mot.rfid.api3.RfidEventsListener;
import com.mot.rfid.api3.RfidReadEvents;
import com.mot.rfid.api3.RfidStatusEvents;
import com.mot.rfid.api3.STATUS_EVENT_TYPE;
import com.mot.rfid.api3.TagData;
import com.prolosys.rfid.common.bootstrap.ApplicationContextProvider;
import com.prolosys.rfid.common.utils.UtilsPLS;
import com.prolosys.rfid.microservices.masters.models.RfidReaderModel;
import com.prolosys.rfid.microservices.rfid.constants.TagTypeEnum;

public class ReadHandler implements RfidEventsListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReadHandler.class);

	private ApplicationContext context = ApplicationContextProvider.getApplicationContext();

	private RfidReaderManager fxManager = (RfidReaderManager) context.getBean(RfidReaderManager.class);

//	private TagScanner tagScanner = (TagScanner) context.getBean(TagScanner.class);

	RfidReaderModel rfidReaderModel = null;

	public ReadHandler(RfidReaderModel rfidReaderModel) {
		this.rfidReaderModel = rfidReaderModel;
	}

	@Override
	public void eventReadNotify(RfidReadEvents event) {

		TagData tagData = event.getReadEventData().tagData;

//		try {
//
//			if (tagData != null && tagData.getTagID() != null) {
//
//				TagTypeEnum tagType = fxManager.getTagType(tagData);
//
//				if (tagType.equals(TagTypeEnum.MTC)) {
//					String mtc = UtilsPLS.getMTCFromTagId(tagData.getTagID());
//					if (mtc != null) {
//						tagScanner.addMtc((rfidReaderModel.getName() + "%" + rfidReaderModel.getType()), mtc ,rfidReaderModel.getCenter().getWerks());
//					}
//
//				} else if (tagType.equals(TagTypeEnum.ROL)) {
//
//					String lenum = UtilsPLS.getLENUMFromTagId(tagData.getTagID());
//					if (lenum != null) {
//						if (!tagScanner.getRolls().containsKey(lenum)) {
//							tagScanner.addRoll((rfidReaderModel.getName() + "%" + rfidReaderModel.getType()), lenum, rfidReaderModel.getCenter().getWerks());
//						}
//					}
//
//				}
//
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}

	}

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
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(true);
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.rfidReaderModel.getName()));
			break;

		case "ACCESS_STOP_EVENT":
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(false);
			LOGGER.info(String.format("Evento %s en lector %s", statusType, this.rfidReaderModel.getName()));
			break;

		case "DISCONNECTION_EVENT":
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).getRfidReaderModel().setScanning(false);
		
			LOGGER.info(String.format("Evento %s en lector %s. Motivo: %s", statusType, this.rfidReaderModel.getName(),
					e.StatusEventData.DisconnectionEventData.getDisconnectionEvent()));
			break;

		case "BUFFER_FULL_EVENT":
		case "BUFFER_FULL_WARNING_EVENT":
//			fxManager.getReaders().get(rfidReaderModel.getId()).getRfidReader().Actions.purgeTags();
			fxManager.getRfidReaders().get(rfidReaderModel.getId()).disconnect();
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
