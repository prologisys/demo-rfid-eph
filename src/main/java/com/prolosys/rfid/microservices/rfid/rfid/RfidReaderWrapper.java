package com.prolosys.rfid.microservices.rfid.rfid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.mot.rfid.api3.ACCESS_OPERATION_CODE;
import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.LoginInfo;
import com.mot.rfid.api3.MEMORY_BANK;
import com.mot.rfid.api3.OperationFailureException;
import com.mot.rfid.api3.READER_TYPE;
import com.mot.rfid.api3.RFIDReader;
import com.mot.rfid.api3.ReaderManagement;
import com.mot.rfid.api3.RfidEventsListener;
import com.mot.rfid.api3.SECURE_MODE;
import com.mot.rfid.api3.TagAccess;
import com.prolosys.rfid.common.bootstrap.ApplicationContextProvider;
import com.prolosys.rfid.common.utils.UtilsPLS;
import com.prolosys.rfid.microservices.masters.models.RfidReaderModel;

import lombok.Getter;
import lombok.Setter;

//
//import java.util.HashMap;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//
//import com.mot.rfid.api3.ACCESS_OPERATION_CODE;
//import com.mot.rfid.api3.Antennas.SingulationControl;
//import com.mot.rfid.api3.FILTER_ACTION;
//import com.mot.rfid.api3.InvalidUsageException;
//import com.mot.rfid.api3.LoginInfo;
//import com.mot.rfid.api3.MEMORY_BANK;
//import com.mot.rfid.api3.OperationFailureException;
//import com.mot.rfid.api3.PreFilters;
//import com.mot.rfid.api3.READER_TYPE;
//import com.mot.rfid.api3.RFIDReader;
//import com.mot.rfid.api3.ReaderManagement;
//import com.mot.rfid.api3.RfidEventsListener;
//import com.mot.rfid.api3.SECURE_MODE;
//import com.mot.rfid.api3.SESSION;
//import com.mot.rfid.api3.STATE_UNAWARE_ACTION;
//import com.mot.rfid.api3.TagAccess;
//import com.mot.rfid.api3.TagData;
//import com.prologisys.scl.common.common.beans.ApplicationContextProvider;
//import com.prologisys.scl.common.common.utils.UtilsPLS;
//import com.prologisys.scl.common.microservices.catalogs.models.RfidReaderModel;
//
//import lombok.Getter;
//import lombok.Setter;

public class RfidReaderWrapper {

	private ApplicationContext context = ApplicationContextProvider.getApplicationContext();
	private RfidReaderManager fxManager = (RfidReaderManager) context.getBean(RfidReaderManager.class);

	private static final Logger LOGGER = LoggerFactory.getLogger(RfidReaderWrapper.class);

	@Getter
	@Setter
	private ReaderManagement readerManagement = new ReaderManagement();

	@Getter
	@Setter
	private RFIDReader rfidReader = new RFIDReader();
	
	@Getter
	@Setter
	private String werks;

	@Getter
	@Setter
	private RfidReaderModel rfidReaderModel = new RfidReaderModel();

//	public static final String API_SUCCESS = "Function Succeeded";
//	public static final String PARAM_ERROR = "Parameter Error";
//	
	public void disconnect() {
		try {
			if(this.getRfidReaderModel().getScanning()!=null && this.getRfidReaderModel().getScanning()) {
				this.stopScanning();
			}
			
			this.getRfidReader().disconnect();
			LOGGER.info(String.format("Se desconectó al lector %s %s", this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp()));
		} catch (InvalidUsageException e) {
//			LOGGER.info(String.format("No se desconectó al lector %s %s InvalidUsageException: %s", this.getRfidReaderModel().getName(),this.getRfidReaderModel().getIp(), e.getInfo()));
		} catch (OperationFailureException e) {
//			LOGGER.info(String.format("No se desconectó al lector %s %s OperationFailureException: %s", this.getRfidReaderModel().getName(),this.getRfidReaderModel().getIp(), e.getStatusDescription()));
		}
	}


	public void connect() {

		if (UtilsPLS.ping(this.getRfidReaderModel().getIp())) {

			try {

				this.getRfidReader().setHostName(this.getRfidReaderModel().getIp());
				this.getRfidReader().setPort(this.getRfidReaderModel().getPort());

				this.getRfidReader().connect();

				this.getRfidReader().Events.setAccessStartEvent(true);
				this.getRfidReader().Events.setAccessStopEvent(true);
				this.getRfidReader().Events.setAntennaEvent(true);
				this.getRfidReader().Events.setAttachTagDataWithReadEvent(true);
				this.getRfidReader().Events.setBufferFullEvent(true);
				this.getRfidReader().Events.setBufferFullWarningEvent(true);
				this.getRfidReader().Events.setEASAlarmEvent(true);
				this.getRfidReader().Events.setGPIEvent(true);
				this.getRfidReader().Events.setHandheldEvent(true);
				this.getRfidReader().Events.setInventoryStartEvent(true);
				this.getRfidReader().Events.setInventoryStopEvent(true);
				this.getRfidReader().Events.setReaderDisconnectEvent(true);
				this.getRfidReader().Events.setReaderExceptionEvent(true);
				this.getRfidReader().Events.setTemperatureAlarmEvent(true);
				this.getRfidReader().Events.setTagReadEvent(true);
				this.getRfidReader().Events.setTagReadEvent(true);
				
				RfidEventsListener rfidEventsListener = null;
				
				switch (this.getRfidReaderModel().getType()) {
				case RECORD:
					rfidEventsListener = new RecordHandler(this);
					break;
				case SCRAPPING:
				case SHIPMENT:
				case ENTRY:
					rfidEventsListener = new ReadHandler(this.getRfidReaderModel());
					break;
				case INVENTORY:
					rfidEventsListener = new InventoryHandler(this);
					break;
				default:
					break;
				}
				
				System.out.println("Lector " + this.getRfidReaderModel().getName());
				System.out.println("Tipo de lector " + this.getRfidReaderModel().getType());
				
				this.getRfidReader().Events.addEventsListener(rfidEventsListener);
				
				System.out.println(this.getRfidReader().Events.getReaderHandle());
				
				
				LOGGER.info(String.format("Se inicia conexion al lector %s %s", this.getRfidReaderModel().getName(),this.getRfidReaderModel().getIp()));

			} catch (InvalidUsageException e) {
				LOGGER.info(String.format("Falla en la conexion del lector %s %s InvalidUsageException: %s",
						this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getInfo()));

				fxManager.removeRfidReaderRFIDById(this.getRfidReaderModel().getId());

			} catch (OperationFailureException e) {

				LOGGER.info(String.format("Falla en la conexion del lector %s %s OperationFailureException: %s",
						this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getStatusDescription()));

				if (e.getStatusDescription().contains("Connection Already exists")) {

					try {
						this.login();
						this.readerManagement.LLRPConnection.disconnectFromReader();

						// this.readerManagement.restart();

					} catch (InvalidUsageException e2) {
						LOGGER.info(String.format("Falla el reinicio del lector %s %s InvalidUsageException: %s",
								this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e2.getInfo()));
					} catch (OperationFailureException e2) {
						LOGGER.info(String.format("Falla el reinicio del lector %s %s OperationFailureException: %s",
								this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(),
								e2.getStatusDescription()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			} catch (NullPointerException e) {

				LOGGER.info(String.format("Falla en la conexion del lector %s %s NullPointerException: %s",
						this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getMessage()));
			}

		}else {
			this.getRfidReaderModel().setMessage("No se puede iniciar conexión, verificar lector");
			LOGGER.info(String.format("%s %s Ping rechazado, no se puede iniciar conexión, verificar lector", this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp()));
		}

	}
//
//	public void accessToTag() {
//
//		String tagId = "1234ABCD00000000000025B1";
//
//		TagAccess tagAccess = new TagAccess();
//
//		TagAccess.ReadAccessParams readAccessParams = tagAccess.new ReadAccessParams();
//
//		TagData readAccessTag;
//
//		readAccessParams.setAccessPassword(0);
//
//		readAccessParams.setByteCount(8);
//
//		readAccessParams.setMemoryBank(MEMORY_BANK.MEMORY_BANK_USER);
//
//		readAccessParams.setByteOffset(0);
//
//		try {
//			readAccessTag = this.getRfidReader().Actions.TagAccess.readWait(tagId, readAccessParams, null);
//
////			System.out.println(readAccessTag.getMemoryBank().toString() + " : " + readAccessTag.getMemoryBankData());
//
//		} catch (InvalidUsageException | OperationFailureException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
	public void startScanning(int selectedMemoryBank) {
		try {

			// Es una operación simple
			if (selectedMemoryBank < 0) {

				this.getRfidReader().Actions.Inventory.perform();

				// Es una operación de lectura en secuencia
			} else {

				TagAccess tagaccess = new TagAccess();

				MEMORY_BANK memoryBank = MEMORY_BANK.MEMORY_BANK_EPC;

				TagAccess.Sequence opSequence = tagaccess.new Sequence(tagaccess);

				TagAccess.Sequence.Operation op1 = opSequence.new Operation();

				op1.setAccessOperationCode(ACCESS_OPERATION_CODE.ACCESS_OPERATION_READ);

				switch (selectedMemoryBank) {
				case 0:
					memoryBank = MEMORY_BANK.MEMORY_BANK_RESERVED;
					break;
				case 1:
					memoryBank = MEMORY_BANK.MEMORY_BANK_EPC;
					break;
				case 2:
					memoryBank = MEMORY_BANK.MEMORY_BANK_TID;
					break;
				case 3:
					memoryBank = MEMORY_BANK.MEMORY_BANK_USER;
					break;
				}

				op1.ReadAccessParams.setMemoryBank(memoryBank);
				op1.ReadAccessParams.setByteCount(0);
				op1.ReadAccessParams.setByteOffset(0);
				op1.ReadAccessParams.setAccessPassword(0);

				this.getRfidReader().Actions.TagAccess.OperationSequence.add(op1);
				this.getRfidReader().Actions.TagAccess.OperationSequence.performSequence();
				this.getRfidReaderModel().setAccessSequenceRunning(true);

			}
			this.getRfidReaderModel().setScanning(true);
			LOGGER.info(String.format("Se solicita escaneo en el lector %s %s", this.getRfidReaderModel().getName(),
					this.getRfidReaderModel().getIp()));
		} catch (InvalidUsageException e) {
			LOGGER.info(String.format("Falla al escanear con el lector %s %s InvalidUsageException: %s",
					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getInfo()));
		} catch (OperationFailureException e) {
			LOGGER.info(String.format("Falla al escanear con el lector %s %s OperationFailureException: %s",
					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getStatusDescription()));
		}

	}

	public void stopScanning() {

		try {

			if (this.getRfidReaderModel().getAccessSequenceRunning()) {
				this.getRfidReader().Actions.TagAccess.OperationSequence.stopSequence();
				this.getRfidReader().Actions.TagAccess.OperationSequence.deleteAll();
				this.getRfidReaderModel().setAccessSequenceRunning(false);
			} else {

				this.getRfidReader().Actions.Inventory.stop();
				this.getRfidReaderModel().setScanning(false);
			}

//				LOGGER.info(String.format("Se detiene escaneo al lector %s %s", this.getRfidReaderModel().getName(), this.getRfidReader().getHostName()));
		} catch (InvalidUsageException e) {
//			LOGGER.info(String.format("No se detuvo el escaneo en el lector %s %s InvalidUsageException: %s",
//					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getInfo()));
		} catch (OperationFailureException e) {
//			LOGGER.info(String.format("No se detuvo el escaneo en el lector %s %s OperationFailureException: %s",
//					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getStatusDescription()));
		} catch (NullPointerException e) {
//			LOGGER.info(String.format("No se detuvo el escaneo en el lector %s %s OperationFailureException: %s",
//					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getMessage()));
		}
//			catch (NullPointerException e) {
//				
//				System.out.println("-------------------"+this.getRfidReader());
//				System.out.println("+++++++++++++++++++"+this.getRfidReaderModel().getName());
//				
//				e.printStackTrace();
//				
//				LOGGER.info(String.format("No se detuvo el escaneo en el lector %s %s NullPointerException: %s", this.getRfidReaderModel().getName(),this.getRfidReaderModel().getIp(), e.getMessage()));
//			}

	}
//
//	public void addFilter(int antennaId, String pattern) {
//
//		PreFilters filters = new PreFilters();
//
//		PreFilters.PreFilter filter = filters.new PreFilter();
//
////		byte[] tagMask = new byte[] { (byte) 0xAA, (byte) 0x03, (byte) 0x16};
//		byte[] tagMask = UtilsPLS.hexStringToByteArray(pattern);
//
//		filter.setAntennaID((short) antennaId);// Set this filter for Antenna ID 3
//
//		filter.setTagPattern(tagMask);// Tags which starts with 0x1211
//
//		filter.setTagPatternBitCount(tagMask.length * 8);
//
//		filter.setBitOffset(32); // skip PC bits (always it should be in bit length)
//
//		filter.setMemoryBank(MEMORY_BANK.MEMORY_BANK_EPC);
//
//		filter.setFilterAction(FILTER_ACTION.FILTER_ACTION_STATE_UNAWARE); // use state unaware singulation
//
//		filter.StateUnawareAction.setStateUnawareAction(STATE_UNAWARE_ACTION.STATE_UNAWARE_ACTION_SELECT);
//
//		try {
//			this.getRfidReader().Actions.PreFilters.add(filter);
////			LOGGER.info(String.format("Se agrega filtro %s en antena %s del lector %s", pattern, antennaId, this.getRfidReaderModel().getName()));
//
//		} catch (InvalidUsageException e) {
//			LOGGER.info(String.format("Falla al agregar filtro %s en antena %s del lector %s InvalidUsageException: %s",
//					pattern, antennaId, this.getRfidReaderModel().getName(), e.getInfo()));
//		} catch (OperationFailureException e) {
//			LOGGER.info(
//					String.format("Falla al agregar filtro $s en antena %s del lector %s OperationFailureException: %s",
//							pattern, antennaId, this.getRfidReaderModel().getName(), e.getStatusDescription()));
//		}
//
//	}
//
//	public void setSingulationControl(int antennaId) {
//
//		SingulationControl s1_singulationControl;
//		try {
//			s1_singulationControl = this.getRfidReader().Config.Antennas.getSingulationControl(antennaId);
//
//			s1_singulationControl.setSession(SESSION.SESSION_S1); // Set session to operate on S1. If not specified,
//																	// reader uses its own way of implementing the
//																	// state-unware singulation
//
//			this.getRfidReader().Config.Antennas.setSingulationControl(antennaId, s1_singulationControl);
//
////			LOGGER.info(String.format("Se agrega singulacion en antena %s del lector %s",antennaId, this.getRfidReaderModel().getName()));
//
//		} catch (InvalidUsageException e) {
//			LOGGER.info(
//					String.format("Falla al agregar singulacion en antena %s del lector %s InvalidUsageException: %s",
//							antennaId, this.getRfidReaderModel().getName(), e.getInfo()));
//		} catch (OperationFailureException e) {
//			LOGGER.info(String.format(
//					"Falla al agregar singulacion en antena %s del lector %s OperationFailureException: %s", antennaId,
//					this.getRfidReaderModel().getName(), e.getStatusDescription()));
//		}
//	}
//
	public void deleteFilters() {
		try {
			this.getRfidReader().Actions.PreFilters.deleteAll();
//			LOGGER.info(String.format("Se eliminan los filtros del lector %s", this.getRfidReaderModel().getName()));
		} catch (InvalidUsageException e) {
			LOGGER.info(String.format("Falla al eliminar los filtros del lector %s %s InvalidUsageException: %s",
					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getInfo()));
		} catch (OperationFailureException e) {
			LOGGER.info(String.format("Falla al eliminar los filtros del lector %s %s OperationFailureException: %s",
					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getStatusDescription()));
		}
	}
//
//	public TagData findTagByEPCID(String tagId) throws InvalidUsageException, OperationFailureException {
//
//		TagAccess tagAccess = new TagAccess();
//
//		TagAccess.ReadAccessParams readAccessParams = tagAccess.new ReadAccessParams();
//
//		TagData readAccessTag;
//
//		readAccessParams.setAccessPassword(0);
//
//		readAccessParams.setByteCount(8);
//
//		readAccessParams.setMemoryBank(MEMORY_BANK.MEMORY_BANK_EPC);
//
//		readAccessParams.setByteOffset(0);
//
//		readAccessTag = this.getRfidReader().Actions.TagAccess.readWait(tagId, readAccessParams, null);
//
//		return readAccessTag;
//
//	}
//
//	public void changeEPCIDByTagId(String tagId, String newTagId) throws InvalidUsageException, OperationFailureException {
//
////		try {
//		byte[] writeData = UtilsPLS.hexStringToByteArray(newTagId);
//
////			AntennaInfo antennaInfo = new AntennaInfo();
////			antennaInfo.setAntennaID(new short[] {1,2,3,4});
//		// byte[] tagMask = new byte[] { (byte) 0xAA, (byte) 0x03, (byte) 0x16};
//		TagAccess tagAccess = new TagAccess();
//
//		TagAccess.WriteSpecificFieldAccessParams writeSpecificFieldAccessParams = tagAccess.new WriteSpecificFieldAccessParams();
//
//		writeSpecificFieldAccessParams.setAccessPassword(0);
//		writeSpecificFieldAccessParams.setWriteData(writeData);
//		writeSpecificFieldAccessParams.setWriteDataLength(writeData.length);
//
//		this.getRfidReader().Actions.TagAccess.writeTagIDWait(tagId, writeSpecificFieldAccessParams, null);
//
////		} catch (InvalidUsageException e1 ) {
////			System.out.println(e1.getMessage());
////			e1.printStackTrace();
////		}
////		catch (OperationFailureException e2) {
////			System.out.println("Falló el cambio de tagID");
////			System.out.println(e2.getStatusDescription());
////			System.out.println(e2.getVendorMessage());
////			System.out.println(e2.getMessage());
////			e2.printStackTrace();
////		}
////		
////		The application can call method reader.Actions.TagAccess.writeWait or reader.Actions.TagAccess.blockWriteWait to write data to a specific memory bank.
////
////		// Write user memory bank data
////
////		String tagId = "1234ABCD00000000000025B1";
////
////		TagAccess tagAccess = new TagAccess();
////
////		TagAccess.WriteAccessParams writeAccessParams = tagAccess.new WriteAccessParams();
////
////		byte[] writeData = new byte[] {0x11, 0x22, 0x33, 0x44};
////
////		writeAccessParams.setAccessPassword(0);
////
////		writeAccessParams.setWriteDataLength(writeData.Length);
////
////		writeAccessParams.setMemoryBank(MEMORY_BANK.MEMORY_BANK_USER);
////
////		writeAccessParams.setByteOffset(0);
////
////		writeAccessParams.setWriteData(writeData);
////
////		// antenna Info is null – performs on all antenna
////
////		reader.Actions.TagAccess.writeWait(tagId, writeAccessParams, null);
//
//	}
//
//	public HashMap<String, String> getCapabilities() {
//
//		HashMap<String, String> capabilities = new HashMap<String, String>();
//		try {
//			capabilities.put("Reader ID", this.getRfidReader().ReaderCapabilities.ReaderID.getID());
//			capabilities.put("Model Name", this.getRfidReader().ReaderCapabilities.getModelName());
//			capabilities.put("Communication Standard",
//					this.getRfidReader().ReaderCapabilities.getCommunicationStandard().toString());
//			capabilities.put("Country Code",
//					Integer.toString(this.getRfidReader().ReaderCapabilities.getCountryCode()));
//			capabilities.put("Firware Version", this.getRfidReader().ReaderCapabilities.getFirwareVersion());
//			capabilities.put("RSSI filter",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isRSSIFilterSupported()));
//			capabilities.put("Tag Event Reporting",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isTagEventReportingSupported()));
//			capabilities.put("Tag Locating Reporting",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isTagLocationingSupported()));
//			capabilities.put("NXP Command Support",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isNXPCommandSupported()));
//			capabilities.put("Block Erase Support",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isBlockEraseSupported()));
//			capabilities.put("Block Write Support",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isBlockWriteSupported()));
//			capabilities.put("Block Permalock Support",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isBlockPermalockSupported()));
//			capabilities.put("Recommision Support",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isRecommisionSupported()));
//			capabilities.put("Write WMI Support",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isWriteUMISupported()));
//			capabilities.put("Radio Power Control Support",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isRadioPowerControlSupported()));
//			capabilities.put("Hopping Enabled",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isHoppingEnabled()));
//			capabilities.put("State Aware Singulation Capable", Boolean
//					.toString(this.getRfidReader().ReaderCapabilities.isTagInventoryStateAwareSingulationSupported()));
//			capabilities.put("UTC Clock Capable",
//					Boolean.toString(this.getRfidReader().ReaderCapabilities.isUTCClockSupported()));
//			capabilities.put("Num Operations In Access Sequence",
//					Integer.toString(this.getRfidReader().ReaderCapabilities.getMaxNumOperationsInAccessSequence()));
//			capabilities.put("Num PreFilters",
//					Integer.toString(this.getRfidReader().ReaderCapabilities.getMaxNumPreFilters()));
//			capabilities.put("Num Antenna Supported",
//					Integer.toString(this.getRfidReader().ReaderCapabilities.getNumAntennaSupported()));
//			capabilities.put("Num GPI Ports",
//					Integer.toString(this.getRfidReader().ReaderCapabilities.getNumGPIPorts()));
//
//		} catch (Exception e) {
//			LOGGER.info(
//					String.format("No se puede leer las capacidades del lector %s", this.getRfidReaderModel().getName()));
//		}
//		return capabilities;
//	}
//
	public void login() {
		try {
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setUserName(this.getRfidReaderModel().getUsername());
			loginInfo.setPassword(this.getRfidReaderModel().getPassword());
			loginInfo.setSecureMode(SECURE_MODE.HTTP);
			loginInfo.setHostName(this.getRfidReaderModel().getIp());
//            loginInfo.setForceLogin(true);

			this.getReaderManagement().login(loginInfo, READER_TYPE.FX);

			LOGGER.info(String.format("Se inicia sesion en el lector %s %s", this.getRfidReaderModel().getName(),
					this.getRfidReaderModel().getIp()));
		} catch (InvalidUsageException e) {
			LOGGER.info(String.format("No se inicia sesion  en el lector %s %s InvalidUsageException: %s",
					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getInfo()));
		} catch (OperationFailureException e) {
			LOGGER.info(String.format("No se inicia sesion en el lector %s %s OperationFailureException: %s",
					this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp(), e.getStatusDescription()));
		}
	}
//
	public void logout() {
		try {
			this.getReaderManagement().logout();

//			LOGGER.info(String.format("Se cierra sesion en el lector %s %s", this.getRfidReaderModel().getName(), this.getRfidReaderModel().getIp()));
		} catch (InvalidUsageException e) {
			LOGGER.info(String.format("Se inicia sesion  en el lector %s InvalidUsageException: %s",
					this.getRfidReaderModel().getName(), e.getInfo()));
		} catch (OperationFailureException e) {
			LOGGER.info(String.format("Se inicia sesion en el lector %s OperationFailureException: %s",
					this.getRfidReaderModel().getName(), e.getStatusDescription()));
		}
	}

}
