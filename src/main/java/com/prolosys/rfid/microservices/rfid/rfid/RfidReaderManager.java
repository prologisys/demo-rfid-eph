package com.prolosys.rfid.microservices.rfid.rfid;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.OperationFailureException;
import com.mot.rfid.api3.TAG_FIELD;
import com.mot.rfid.api3.TagData;
import com.mot.rfid.api3.TagStorageSettings;
import com.prolosys.rfid.common.constants.ConfigurationEnum;
import com.prolosys.rfid.microservices.masters.converters.RfidReaderConverter;
import com.prolosys.rfid.microservices.masters.models.RfidReaderModel;
import com.prolosys.rfid.microservices.masters.repositories.entities.RfidReaderEntity;
import com.prolosys.rfid.microservices.masters.services.RfidReaderService;
import com.prolosys.rfid.microservices.rfid.constants.TagTypeEnum;

import lombok.Getter;

//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.mot.rfid.api3.InvalidUsageException;
//import com.mot.rfid.api3.OperationFailureException;
//import com.mot.rfid.api3.TAG_FIELD;
//import com.mot.rfid.api3.TagData;
//import com.mot.rfid.api3.TagStorageSettings;
//import com.pls.monitor.ConnectionSts;
//import com.prologisys.scl.common.common.constants.ConfigurationEnum;
//import com.prologisys.scl.common.common.constants.RfidReaderTypeEnum;
//import com.prologisys.scl.common.common.utils.UtilsPLS;
//import com.prologisys.scl.common.microservices.catalogs.converters.RfidReaderConverter;
//import com.prologisys.scl.common.microservices.catalogs.models.RfidReaderModel;
//import com.prologisys.scl.common.microservices.catalogs.repositories.entities.RfidReaderEntity;
//import com.prologisys.scl.common.microservices.catalogs.services.RfidReaderService;
//import com.prologisys.scl.common.microservices.configurations.services.ConfigurationService;
//import com.prologisys.scl.common.microservices.interfaces.ots.services.OtService;
//import com.prologisys.scl.common.microservices.opc.models.CorelinkSapModel;
//import com.prologisys.scl.common.microservices.rfid.constants.TagTypeEnum;
//import com.prologisys.scl.common.microservices.rfid.models.DetectedTagModel;
//import com.prologisys.scl.common.microservices.rfid.models.TagToRecord;
//import com.prologisys.scl.common.microservices.rfid.services.DashboardService;
//import com.prologisys.scl.common.microservices.rfid.services.OpcService;
//import com.prologisys.scl.common.microservices.sapcon.SapConComponent;
//
//import lombok.Getter;
//
/**
 * The Class RfidReaderManager.
 */
@Component
public class RfidReaderManager {
//	
//	@Autowired
//	RfidReaderProperties rfidReaderProperties;
//	
	@Autowired
	private RfidReaderAsync rfidReaderAsync;
////	
//    @Autowired
//    SapConComponent sapConComponen;
//
//	@Value("${com.prologisys.scl.rfid.cleanMemoryIntervalMilliseconds}")
//	private int cleanMemoryInterval;

	@Getter
	private Map<Long, RfidReaderWrapper> rfidReaders = new HashMap<>();

	@Getter
	private Map<TagTypeEnum, Map<String, Date>> tags = new HashMap<>();

	@Autowired
	private RfidReaderService rfidReaderService;

	@Autowired
	private RfidReaderConverter rfidReaderConverter;
//	
//	@Autowired
//	private OtService otService;
//
//	@Autowired
//	private OpcService opcService;
//	
//	@Autowired
//	private DashboardService dashboardService;
//
//	@Autowired
//	private ConfigurationService configurationService;
//	
//	final String configPrefix = "RFID_";
//
	private static final Logger LOGGER = LoggerFactory.getLogger(RfidReaderManager.class);
	
	
	/*
	 * ApplicationReadyEvent
	 * Se ejecuta al cargar la aplición
	 * */
	@EventListener
	public void ApplicationReadyEvent(ApplicationReadyEvent event) throws Exception {
		
		tags = new HashMap<TagTypeEnum, Map<String, Date>>();
		tags.put(TagTypeEnum.MTC, new HashMap<String, Date>());
		tags.put(TagTypeEnum.ROL, new HashMap<String, Date>());
		tags.put(TagTypeEnum.VIRGIN, new HashMap<String, Date>());
		tags.put(TagTypeEnum.OTHER, new HashMap<String, Date>());
		
		this.loadRfidReaders();
		
	}
	
	/*
	 * load Rfid Readers
	 * Carga a la memoria todos los lectores rfid habilitados en la base de datos
	 * */
	public void loadRfidReaders() {
		
		boolean enabled = true;
		
		List<RfidReaderEntity> rfidReaderEntityList = rfidReaderService.findAllByEnabled(enabled);
		
		List<RfidReaderModel> rfidReaderModelList = rfidReaderConverter.rfidReaderEntityListToRfidReaderModelList(rfidReaderEntityList, Arrays.asList("center"));
		
		for (RfidReaderModel rfidReaderModel : rfidReaderModelList) {
			this.putRfidReader(rfidReaderModel);
		}
		
		LOGGER.info("Se carga en memoria {} lectores rfid habilitados en la base de datos", rfidReaderModelList.size());
	}
	
	
	/*
	 * monitorRfidReaders
	 * Conecta, desconecta, escanea y detiene escaneo de los lectores según operación
	 */
	@Scheduled(fixedDelay = 1000 * 20, initialDelay = 1000 * 1)
	public void monitorRfidReaders() {
		
		for (Entry<Long, RfidReaderWrapper> entry : this.rfidReaders.entrySet()) {
			rfidReaderAsync.monitorRfidReader(entry.getValue());
		}
		LOGGER.info("Se monitorea los lectores Rfid en memoria");
	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//	// Contructor para incializar los tags en memoria
////	public RfidReaderManager() {
////	tags = new HashMap<TagTypeEnum, Map<String, Date>>();
////	tags.put(TagTypeEnum.MTC, new HashMap<String, Date>());
////	tags.put(TagTypeEnum.ROL, new HashMap<String, Date>());
////	tags.put(TagTypeEnum.VIRGIN, new HashMap<String, Date>());
////	tags.put(TagTypeEnum.OTHER, new HashMap<String, Date>());
////	}
//
//	@Scheduled(fixedDelay = (1000 * 10), initialDelay = 1000 * 1)
//	public void cleanTagStorage() {
//
//		for (Entry<Long, RfidReaderWrapper> entry : rfidReaders.entrySet()) {
//			RfidReaderWrapper rfidReaderWrapper = entry.getValue();
//			
//			try {
//				rfidReaderWrapper.getRfidReader().Actions.purgeTags();
//			} catch ( NullPointerException e) {
//				// TODO: handle exception
//			}
//
////			try {
//////				TagStorageSettings tagStorageSettings = rfidReaderWrapper.getRfidRfidReader().Config.getTagStorageSettings();
////				
////				rfidReaderWrapper.getRfidRfidReader().Actions.purgeTags();
////
////			} catch (InvalidUsageException | OperationFailureException | NullPointerException e) {
////
////			}
//		}
//	}
//
//	
//	
//	@SuppressWarnings("unchecked")
//	@Scheduled(fixedDelay = 1000 * 20, initialDelay = 1000 * 10)
//	public void cleanTags() {
//		Date now = new Date();
//
//		// Imprimir otros tags en memoria
//		StringBuilder tagsSB = new StringBuilder();
//		StringBuilder removedTagsSB = new StringBuilder();
//		StringBuilder removeSB = new StringBuilder();
//
//		Iterator<?> itTypes = tags.entrySet().iterator();
//		while (itTypes.hasNext()) {
//			Map.Entry<?, ?> entryType = (Map.Entry<?, ?>) itTypes.next();
//			Map<String, Date> tagMap = (Map<String, Date>) entryType.getValue();
//			if (!tagMap.isEmpty()) {
//				tagsSB.append("\n\t" + entryType.getKey() + ": ");
//			}
//
//			Iterator<?> itTags = tagMap.entrySet().iterator();
//			while (itTags.hasNext()) {
//				Map.Entry<?, ?> entryTag = (Map.Entry<?, ?>) itTags.next();
//				tagsSB.append(entryTag.getKey() + " ");
//
//				Date d = (Date) entryTag.getValue();
//
//				if ((now.getTime() - d.getTime()) > cleanMemoryInterval) {
//					itTags.remove();
//					removeSB.append(entryTag.getKey() + " ");
//				}
//
//			}
//
//			// Si se elimina un tag
//			if (removeSB.length() > 0) {
//				removedTagsSB.append("\n\t" + entryType.getKey() + ": " + removeSB);
//				removeSB.setLength(0);
//			}
//		}
//		if (tagsSB.length() > 0) {
//			System.out.println("=============== Tags en memoria ===============" + tagsSB);
//		}
//		if (removedTagsSB.length() > 0) {
//			System.out.println("========== Tags eliminados de memoria =========" + removedTagsSB);
//		}
//
//	}
//
	TagTypeEnum getTagType(TagData tag) {

//		TagTypeEnum tagType = TagTypeEnum.OTHER;
//		String[] prefixies;
//
//		prefixies = configurationService.findById(ConfigurationEnum.RFID_PREFIXES_UA).getValue().split(",");
//
//		for (String string : prefixies) {
//			if (tag.getTagID().startsWith(string)) {
//				tagType = TagTypeEnum.ROL;
//			}
//		}
//
//		prefixies = configurationService.findById(ConfigurationEnum.RFID_PREFIXES_MTC).getValue().split(",");
//		for (String string : prefixies) {
//			if (tag.getTagID().startsWith(string)) {
//				tagType = TagTypeEnum.MTC;
//			}
//		}
//
//		prefixies = configurationService.findById(ConfigurationEnum.RFID_PREFIXES_VIRGIN).getValue().split(",");
//		for (String string : prefixies) {
//			if (tag.getTagID().startsWith(string)) {
//				tagType = TagTypeEnum.VIRGIN;
//			}
//		}
		return TagTypeEnum.VIRGIN;
	}
//
//	
//	
//	
	/*
	 * Agregar o actualizar un lector en el storage de los lectores
	 */
	public void putRfidReader(RfidReaderModel rfidReaderModel) {

		RfidReaderWrapper rfidReaderWrapper;

		// Si el lector ya existe en memoria
		if (rfidReaders.containsKey(rfidReaderModel.getId())) {
			rfidReaderWrapper = rfidReaders.get(rfidReaderModel.getId());
			try {
				// Detiene escaneo, desconecta y deloguea el que existe
				System.out.println("Se desconecta desde putRfidReader");
				rfidReaderAsync.disconnect(rfidReaderWrapper);
				rfidReaderWrapper.logout();
			} catch (Exception e) {
			}
		} else {
			rfidReaderWrapper = new RfidReaderWrapper();
		}

		System.out.println("Agregando lector a memoria " + rfidReaderModel.getId() + " " + rfidReaderModel.getName());
		// Agrega el lector actualizado inyectado por parametro
		rfidReaderWrapper.setRfidReaderModel(rfidReaderModel);
		rfidReaders.put(rfidReaderModel.getId(), rfidReaderWrapper);
	}
//
//	
//	
//	
//	/*
//	 * Obtener los modelos de los lectores del storage
//	 */
//	public RfidReaderModel getRfidReaderModelById(Long id) {
//		RfidReaderModel rfidReaderModel = null;
//
//		if (rfidReaders.containsKey(id)) {
//
//			RfidReaderWrapper rfidReaderWrapper = rfidReaders.get(id);
//			rfidReaderModel = rfidReaders.get(id).getRfidReaderModel();
//
//			try {
//				rfidReaderModel.setScanning(rfidReaderWrapper.getRfidReaderModel().getScanning());
//			} catch (Exception e) {
//				rfidReaderModel.setScanning(false);
//			}
//			try {
//				rfidReaderModel.setConnected(rfidReaderWrapper.getRfidReader().isConnected());
//			} catch (Exception e) {
//				rfidReaderModel.setConnected(false);
//			}
//			try {
//				rfidReaderModel.setLoggedIn(rfidReaderWrapper.getReaderManagement().isLoggedIn());
//			} catch (Exception e) {
//				rfidReaderModel.setLoggedIn(false);
//			}
//		}
//
//		return rfidReaderModel;
//	}
//
//	
//	
//	
//	
//	
//	public Map<String, String> getCapabilitiesById(Long id) {
//		Map<String, String> capabilities = new HashMap<String, String>();
//
//		if (rfidReaders.containsKey(id)) {
//			RfidReaderWrapper rfidReaderWrapper = rfidReaders.get(id);
//			capabilities = rfidReaderWrapper.getCapabilities();
//		}
//		return capabilities;
//	}
//
//	
	
	
	
	public void removeRfidReaderRFIDById(Long id) {
		if (rfidReaders.containsKey(id)) {
			RfidReaderWrapper rfidReaderWrapper = rfidReaders.get(id);
			try {

				rfidReaderAsync.disconnect(rfidReaderWrapper);
				rfidReaderWrapper.logout();
				rfidReaders.remove(id);

			} catch (Exception e) {

			}
		}
	}

	
	
	
	
	/*
	 * Iniciar el escaneo en los lectores manualmente
	 */
	public void rfidReaderScan(RfidReaderWrapper rfidReaderWrapper, int bank) {
		if (rfidReaderWrapper.getRfidReader().isConnected() && !rfidReaderWrapper.getRfidReaderModel().getScanning()) {

			TagStorageSettings tagStorageSettings;
			try {
				tagStorageSettings = rfidReaderWrapper.getRfidReader().Config.getTagStorageSettings();
				tagStorageSettings.enableAccessReports(true);

				TAG_FIELD[] tagField = new TAG_FIELD[3];

				tagField[0] = TAG_FIELD.ANTENNA_ID;

				tagField[1] = TAG_FIELD.PEAK_RSSI;
				
				tagField[2] = TAG_FIELD.LAST_SEEN_TIME_STAMP;

				tagStorageSettings.setTagFields(tagField);

				tagStorageSettings.discardTagsOnInventoryStop(true);
				tagStorageSettings.setMaxTagCount(4096);

				rfidReaderWrapper.getRfidReader().Config.setTagStorageSettings(tagStorageSettings);

			} catch (InvalidUsageException | OperationFailureException e) {
				e.printStackTrace();
			}

			rfidReaderWrapper.deleteFilters();

			String[] prefixies;

//			switch (rfidReaderWrapper.getRfidReaderModel().getType()) {
//			case RECORD:
//
//				prefixies = configurationService.findById(ConfigurationEnum.RFID_PREFIXES_VIRGIN).getValue().split(",");
//
//				for (String string : prefixies) {
//
//					rfidReaderWrapper.addFilter(1, string);
//					rfidReaderWrapper.addFilter(2, string);
//					rfidReaderWrapper.addFilter(3, string);
//					rfidReaderWrapper.addFilter(4, string);
//				}
//
//				break;
//			case ENTRY:
//			case SHIPMENT:
//			case SCRAPPING:
//			case INVENTORY:
//
//				prefixies = configurationService.findById(ConfigurationEnum.RFID_PREFIXES_UA).getValue().split(",");
//
//				for (String string : prefixies) {
//					rfidReaderWrapper.addFilter(1, string);
//					rfidReaderWrapper.addFilter(2, string);
//					rfidReaderWrapper.addFilter(3, string);
//					rfidReaderWrapper.addFilter(4, string);
//				}
//
//				prefixies = configurationService.findById(ConfigurationEnum.RFID_PREFIXES_MTC).getValue().split(",");
//
//				for (String string : prefixies) {
//					rfidReaderWrapper.addFilter(1, string);
//					rfidReaderWrapper.addFilter(2, string);
//					rfidReaderWrapper.addFilter(3, string);
//					rfidReaderWrapper.addFilter(4, string);
//				}
//
//				break;
//			}
//
//			rfidReaderWrapper.setSingulationControl(1);
//			rfidReaderWrapper.setSingulationControl(2);
//			rfidReaderWrapper.setSingulationControl(3);
//			rfidReaderWrapper.setSingulationControl(4);

			rfidReaderAsync.startScanning(rfidReaderWrapper, bank);
		}
	}
//
//
//	
//	
//	
//	
//
//	
//	
//	
//
//
//	
//	
//	
//	
//	
//	/*
//	 * Función para ejecutar grabación de tag (Cambio de tagID)
//	 */
//	public boolean changeEPCIDByTagId(RfidReaderWrapper rfidReaderWrapper, TagToRecord tagToRecord, int numeroAleatorio) {
//		
//		boolean result = false;
//		
//		int recordAttemps = Integer.parseInt(configurationService.findById(ConfigurationEnum.RFID_RECORD_ATTEMPS).getValue());
//		LOGGER.info(String.format("Inciando grabacion de tag virgen %s con %s intentos" , tagToRecord.getEpcId() ,recordAttemps));
//		String uaPrefix = configurationService.findById(ConfigurationEnum.RFID_PREFIXES_UA).getValue();
//		
//		int count = 1;
//		
//		do {
//			
//			try {
//			
//				rfidReaderAsync.changeEPCIDByTagId(rfidReaderWrapper, tagToRecord.getEpcId(), UtilsPLS.buildTagEpc(uaPrefix, Long.toString(tagToRecord.getLenum()), 24));
//
//				LOGGER.info(String.format("%s Se cambia el epcId de %s a %s por el lector %s",
//						numeroAleatorio, tagToRecord.getEpcId(),
//						UtilsPLS.buildTagEpc(uaPrefix, Long.toString(tagToRecord.getLenum()), 24),
//						rfidReaderWrapper.getRfidReaderModel().getName()));
//			
//				count = (recordAttemps+1);
//				result = true;
//				
//			} catch (InvalidUsageException e) {
//
//				LOGGER.info(String.format(
//						"%s Falla al cambiar el epcId de %s a %s por el lector %s [InvalidUsageException: %s]",
//						numeroAleatorio,
//						tagToRecord.getEpcId(),
//						UtilsPLS.buildTagEpc(uaPrefix, Long.toString(tagToRecord.getLenum()), 24),
//						rfidReaderWrapper.getRfidReaderModel().getName()), e.getInfo());
//				count++;
//
//			} catch (OperationFailureException e) {
//
//				LOGGER.info(String.format(
//						"%s Falla al cambiar el epcId de %s a %s por el lector %s [OperationFailureException: %s]",
//						numeroAleatorio,
//						tagToRecord.getEpcId(),
//						UtilsPLS.buildTagEpc(uaPrefix, Long.toString(tagToRecord.getLenum()), 24),
//						rfidReaderWrapper.getRfidReaderModel().getName(), e.getStatusDescription()));
//				count++;
//
//			}
//			
//		} while (count <= recordAttemps);
//		
//		
//		CorelinkSapModel opcCorelinkSapModel = new CorelinkSapModel();
//		opcCorelinkSapModel.setLenum(tagToRecord.getLenum());
//		opcCorelinkSapModel.setSend((result)?3:5);
//		opcCorelinkSapModel.setRecordingDate(new Date());
//		
//		
//		int affectedRow = otService.updateSendAndRecordingDateByLenum(opcCorelinkSapModel.getSend(), opcCorelinkSapModel.getRecordingDate(), Long.toString(tagToRecord.getLenum()));
//		System.out.println(String.format("OTs actualizadas %s: con lenum %s con date %s con label_send %s ",affectedRow, Long.toString(tagToRecord.getLenum()), opcCorelinkSapModel.getRecordingDate(), opcCorelinkSapModel.getSend()));
//		opcCorelinkSapModel = opcService.reportToOpcRecordedUa(opcCorelinkSapModel);
//		return result;
//	}
//
//	
//	
//	
////	@Async
//	public void reportToDashboardScannedTag(DetectedTagModel detectedTagModel) {
//		
//		dashboardService.reportToDashboardScannedTag(detectedTagModel);
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@Scheduled(fixedDelay = 1000 * 30, initialDelay = 1000 * 1)
//	protected void findAvailableRfidReaders() {
//		
//		
//		List<RfidReaderEntity> rfidReaderEntityList = new ArrayList<>();
//		
//		for (Map<String, String> readers : rfidReaderProperties.getReaders()) {
//
//			List<RfidReaderEntity> rfidReaderModelListTemp = rfidReaderService.findAllByCenterWerksAndRfidReaderType(
//					readers.get("werks"),
//					RfidReaderTypeEnum.valueOf(readers.get("readerType"))
//					);
//			rfidReaderEntityList.addAll(rfidReaderModelListTemp);
//		}
//
//		List<RfidReaderModel> rfidReaderModelList = rfidReaderConverter.rfidReaderEntityListToRfidReaderModelList(rfidReaderEntityList, Arrays.asList("center"));
//
//		for (RfidReaderModel rfidReaderModel : rfidReaderModelList) {
//			
//			try {
//				RfidReaderWrapper rfidReaderWrapper = rfidReaders.get(rfidReaderModel.getId());
//
//				if (rfidReaders.containsKey(rfidReaderModel.getId())) {
//
//					// Si el que existe en memoria es diferente al que se extrajo de la DB, se elimina el interior
//					if (!rfidReaderWrapper.getRfidReaderModel().getIp().equals(rfidReaderModel.getIp())
//							|| !rfidReaderWrapper.getRfidReaderModel().getName().equals(rfidReaderModel.getName())
//							|| !rfidReaderWrapper.getRfidReaderModel().getUsername().equals(rfidReaderModel.getUsername())
//							|| !rfidReaderWrapper.getRfidReaderModel().getPassword().equals(rfidReaderModel.getPassword())
//							|| !rfidReaderWrapper.getRfidReaderModel().getType().equals(rfidReaderModel.getType())
//							|| rfidReaderWrapper.getRfidReaderModel().getRssi() != rfidReaderModel.getRssi()
//							|| rfidReaderWrapper.getRfidReaderModel().getPort() != rfidReaderModel.getPort() ||
//
//							rfidReaderWrapper.getRfidReaderModel().getEnabled() != rfidReaderModel.getEnabled()) {
//						removeRfidReaderRFIDById(rfidReaderModel.getId());
//						rfidReaderModel.setMessage("Lector inicializado");
//						putRfidReader(rfidReaderModel);
//					}
//
//				} else {
//					putRfidReader(rfidReaderModel);
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//		}
//
//	}
//
//	
//	
//	
//	
//	
//	public ConnectionSts getConectionFromMTC(String mtc) throws Exception {
//
//		ConnectionSts connectionMtc = null;
//
//		List<ConnectionSts> connsSts = sapConComponen.getJcoClient(null).getConnsSts();
//
//		for (ConnectionSts connectionSts : connsSts) {
//			if (connectionSts.getType().equals("ws_client")) {
//
//				System.out.println("Sesiones iniciadas: " + connectionSts.getUser() + " " + connectionSts.getName());
//				if (connectionSts.getName().toUpperCase().equals(mtc.toUpperCase())) {
//					connectionMtc = connectionSts;
//					break;
//				}
//			}
//		}
//		return connectionMtc;
//
//	}
//	
//	
//	
//	@Scheduled(initialDelay = 1000*48, fixedRate = 1000*60)
//	public void sendBarcodeReadersToMonitor() {
//
//		if (!rfidReaders.isEmpty()) {
//			for (Entry<Long, RfidReaderWrapper> entry : rfidReaders.entrySet()) {
//				RfidReaderWrapper brw = entry.getValue();
//				rfidReaderAsync.sendRfidReaderToMonitor(brw);
//			}
//		}
//	}
//
}
