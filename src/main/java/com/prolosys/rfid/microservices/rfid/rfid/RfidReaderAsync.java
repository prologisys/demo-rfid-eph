package com.prolosys.rfid.microservices.rfid.rfid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.prolosys.rfid.common.utils.UtilsPLS;

//
//import java.util.Date;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//import com.mot.rfid.api3.InvalidUsageException;
//import com.mot.rfid.api3.OperationFailureException;
//import com.prologisys.scl.common.common.constants.TechnologyEnum;
//import com.prologisys.scl.common.common.utils.UtilsPLS;
//import com.prologisys.scl.common.microservices.dashboards.constants.InventoryStatusEnum;
//import com.prologisys.scl.common.microservices.interfaces.deliveries.repositories.entities.OpenDeliveryEntity;
//import com.prologisys.scl.common.microservices.interfaces.deliveries.services.OpenDeliveryService;
//import com.prologisys.scl.common.microservices.interfaces.ots.services.OtService;
//import com.prologisys.scl.common.microservices.interfaces.stock.services.StockService;
//import com.prologisys.scl.common.microservices.interfaces.tasks.constants.ActTypeEnum;
//import com.prologisys.scl.common.microservices.interfaces.tasks.services.TaskService;
//import com.prologisys.scl.common.microservices.inventories.services.InventoryService;
//import com.prologisys.scl.common.microservices.monitor.services.MonitorService;
//import com.prologisys.scl.common.microservices.rfid.models.DetectedTagModel;
//import com.prologisys.scl.common.microservices.rfid.models.MtcModel;
//import com.prologisys.scl.common.microservices.rfid.services.InterfacesService;
//import com.prolosys.rfid.common.models.EndpointJson;
//
@Component
public class RfidReaderAsync {
//	
//	@Autowired
//	private MonitorService monitorService;
//	
	@Lazy
	@Autowired 
	private RfidReaderManager fxManager;
//	
//	@Autowired
//	private InterfacesService interfacesService;
//	
//	@Autowired
//	private TaskService taskService;
//	
//	@Autowired
//	private OtService otService;
//	
//	@Autowired
//	private OpenDeliveryService openDeliveryService;
//	
//	@Autowired
//	private StockService stockService;
//	
//	@Autowired
//	private InventoryService inventoryService;
//	
	private static final Logger LOGGER = LoggerFactory.getLogger(RfidReaderAsync.class);

	@Async
    public void connect(RfidReaderWrapper readerFX) {
		readerFX.connect();
    }
	
	@Async
    public void startScanning(RfidReaderWrapper readerFX, int bank) {
		readerFX.startScanning(bank);
    }
//	
//	
//	@Async
//    public void stopScanning(RfidReaderWrapper readerFX) {
//		readerFX.stopScanning();
//    }
//	
//	
	@Async
    public void disconnect(RfidReaderWrapper readerFX) {
		readerFX.disconnect();
    }
//	
//
//	public void changeEPCIDByTagId(RfidReaderWrapper readerFX, String tagId, String newTagId)   throws InvalidUsageException, OperationFailureException {
//		
//		readerFX.changeEPCIDByTagId(tagId, newTagId);
//		
//	}
//	
//	@Async
//	public void login(RfidReaderWrapper readerFX) {
//		readerFX.login();
//		
//	}
//	
//	@Async
//	public void logout(RfidReaderWrapper readerFX) {
//		readerFX.logout();
//	}
//	
//	@Async
//	public String sendScrapping(String arc, String mtc, String lenum, String werks) {
//		
//		boolean exists = false;
//		
//		
//		
//		MtcModel mtcModel = interfacesService.getConectionFromMTC(mtc);
//		if(mtcModel!=null) {
//			exists = taskService.existsByACTTYPEAndLENUMAndSTATUSAndUSRIDLike(ActTypeEnum.SCU, lenum, "", mtcModel.getUser());
//			System.out.println(String.format("lenum %s para desguace %s con MTC %s", lenum, exists, mtcModel.getMtc()));
//		}else {
//			System.out.println(String.format("lenum %s para desguace %s con MTC %s", lenum, exists, null));
//		}
//		
//		if(exists) {
//			sendMessage(arc, mtc, lenum, werks);
//		}
//		
//		return Boolean.toString(exists);
//		
//	}
//	
//	
//	
//	@Async
//	public String sendEntry(String arc, String mtc, String lenum,String  werks) {
//		boolean exists = false;
//		
//		exists = otService.existsByOTTYPEAndLENUM("EN", lenum);
////		
////		exists = otService.existsByOTTYPEAndLENUMAndLIFTIsNotNullAndLIFTIsNot("EN", lenum, "");
//		
////		OpenOtEntity openOtEntity = otService.findByOTTYPEAndLENUM("EN", lenum);
////		
////		if(openOtEntity!=null) {
////			
////			if(openOtEntity.getLIFT().equals("") || openOtEntity.getLIFT()==null) {
////				
////			}
////			
////		}
//		
//		
////		String lift = otService.findByOTTYPEAndLENUM
////				
////				Enviar mensaje si
////					en una OT de entrada
////					hay un lenum
////					si està libre de lift != null y !=vacìo
//		
//		if(exists) {
//			System.out.println(String.format("Se envía mensaje de entrada a mtc [%s] con bobina [%s] en arc [%s]", mtc, lenum, arc));
//			sendMessage(arc, mtc, lenum, werks);
//			
////			otService.updateOpenOtByLIFTByOpenOtId();
//			
//		}else {
//			System.out.println(String.format("No se envía mensaje de entrada a mtc [%s] con bobina [%s] en arc [%s]. Datos no conformes.", mtc, lenum, arc));
//		}
//
//		return Boolean.toString(exists);
//	}
//	
//	
//	@Async
//	public String sendShipment(String arc, String mtc, String lenum, String werks) {
//		
////		System.out.println(String.format("Revisión de embarque: [%s] [%s] [%s]", arc, mtc, lenum));
//		
//		boolean exists = false;
//		
//		//Extraer el material de la bobina
//		String MATNR = stockService.findMATNRByLENUMInCurrentStock(lenum);
//		System.out.println("Material de la bobina encontrada: " + MATNR);
//		
//		if(MATNR==null) {return null;}
//
////			//Detectar si existe una asignaciòn de entrega con el Material y el nombre del arco
////			exists = openDeliveryService.existsByIDDOORAndMATNR(readerModel.getName(),MATNR);
//			
//			List<OpenDeliveryEntity> openDeliveryEntityList = openDeliveryService.findAllByIDDOORAndMATNR(arc,MATNR);
//			if(openDeliveryEntityList.size()>0) {
//				MtcModel mtcModel = interfacesService.getConectionFromMTC(mtc);
//				
//				if(mtcModel==null) {
//					System.out.println(String.format("El MTC no tiene sesión de usuario"));
//				}else {
//					
//					System.out.println("Usuario de montacargas " + mtcModel.getUser());
//					
//					System.out.println(String.format("MTC %s tiene sesión con usuario %s", mtcModel.getMtc(), mtcModel.getUser()));
//				}
//				
//				for (OpenDeliveryEntity openDeliveryEntity : openDeliveryEntityList) {
////					System.out.println(openDeliveryEntity);
//
//						exists = taskService.existsByACTTYPEAndVBELNAndSTATUSAndUSRIDLike(
//								ActTypeEnum.DEL, 
//								openDeliveryEntity.getVBELN(),
//								"", 
//								mtcModel.getUser());
//						
//						
//						System.out.println("Tipo de tarea " + ActTypeEnum.DEL + " Entrega: " + openDeliveryEntity.getVBELN() + " Status: '' Usuario: " + 	mtcModel.getUser());
//						
////						System.out.println("Existe tarea " + exists);
////						System.out.println("exists Embarque " + openDeliveryEntity.getVBELN());
////						System.out.println("exists tarea " + mtc);
//						
//						if(exists) {
////							System.out.println("Existe tarea " + exists);
////							System.out.println("exists tarea " + openDeliveryEntity.getVBELN());
////							System.out.println("exists tarea " + mtc.getUser());
//							break;
//						}
//				}
//			}
//
//			System.out.println(exists + " Hay una entrega con arco "+ arc + " y material "+ MATNR);
//		
//		LOGGER.info(String.format("Embarque: Arco: %s MTC: %s UA: %s", arc, mtc,lenum));
//		
//		
//		if(exists) {
//			System.out.println(String.format("Se envía mensaje de embarque a mtc [%s] con bobina [%s] en arc [%s]", mtc, lenum, arc));
//			sendMessage(arc, mtc, lenum, werks);
//		}else {
//			System.out.println(String.format("No se envía mensaje de embarque a mtc [%s] con bobina [%s] en arc [%s]. Datos no conformes.", mtc, lenum, arc));
//		}
//		
//		return Boolean.toString(exists);
//		
//	}
//	
//	
//	
//	public void sendMessage(String arc, String mtc, String lenum, String werks) {
//
//		DetectedTagModel detectedTagModel = new DetectedTagModel();
//		detectedTagModel.setCreateDate(new Date());
//		detectedTagModel.setLENUM(lenum);
//		detectedTagModel.setREADER(arc);
//		detectedTagModel.setLIFT(mtc);
//		detectedTagModel.setWerks(werks);		
//		EndpointJson response = interfacesService.reportToSAPRollInArc(detectedTagModel);
//		LOGGER.info(String.format("UA en arco: Input: %s Output: %s", detectedTagModel, response));
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
	//Monitorear conexión con lector. Si hay tareas pendientes entonces se conecta, de lo contrario se desconecta
	@Async
	public void monitorRfidReader(RfidReaderWrapper readerFX) {
		
		//Obtener resultado de ping a 2 seg
		boolean ping = UtilsPLS.ping(readerFX.getRfidReaderModel().getIp());
//		boolean tasks = this.scanRequired(readerFX);
//		readerFX.getRfidReaderModel().setTasks(tasks);
		
		System.out.println("readerFX.getRfidReaderModel().getEnabled()" + readerFX.getRfidReaderModel().getEnabled());
		
		//Si está habilitado
		if (readerFX.getRfidReaderModel().getEnabled()) {
			
			System.out.println("readerFX.getRfidReader().isConnected() " + readerFX.getRfidReader().isConnected());
			
			//Si está conectado
			if(readerFX.getRfidReader().isConnected()) {
				
				//Si hay ping con el lector
				if(ping) {
					
					System.out.println("ping " + ping);
					
					//Si hay tareas activas
					if(true) {
						
						System.out.println("!readerFX.getRfidReaderModel().getScanning() && !readerFX.getRfidReaderModel().getRecording()" + (!readerFX.getRfidReaderModel().getScanning() && !readerFX.getRfidReaderModel().getRecording()));
						
						//Si no está escaneando se activa, excepto si está en proceso de grabación
						if(!readerFX.getRfidReaderModel().getScanning() && !readerFX.getRfidReaderModel().getRecording()) {
							
							System.out.println("Se inicia el escaneo");
							fxManager.rfidReaderScan(readerFX, -1);
							readerFX.getRfidReaderModel().setMessage("Escaneando tags");
						}
						
					//Si no  hay tareas activas, detiene el escaneo y desconecta
					}else {
						readerFX.getRfidReaderModel().setMessage("Se desconecta, no hay tareas por ejecutar");
						this.disconnect(readerFX);
					}
//					readerFX.getRfidReaderModel().setMessage(tasks?"Con tareas pendientes": "Sin tareas a ejecutar");
				
				//Si no hay ping, se forza la desconexión
				}else {
					
					LOGGER.info(String.format("Evento %s en lector %s. Motivo: %s", "CONECTION_LOST",readerFX.getRfidReaderModel().getName(), "Ping rechazado"));
					readerFX.getRfidReaderModel().setMessage("Per perdió conexión, verificar lector");
					this.disconnect(readerFX);
				}
				

			//Si no está conectado pero hay tareas entonces se conecta
			}else if(true) {
				readerFX.getRfidReaderModel().setMessage("Conectado para ejecutar tareas");
				this.connect(readerFX);
			}

			
		//Si está deshabilitado
		}else {
			
			//Si está conectado
			if (readerFX.getRfidReader().isConnected()) {
				readerFX.disconnect();
			}
			
		}
		
//		System.out.println(String.format(
//			"Reader %s \t[tipo: %s] [ping: %s] [habilitado: %s] [conectado: %s] [escaneando: %s] [tareas: %s]", 
//			readerFX.getRfidReaderModel().getIp(),
//			readerFX.getRfidReaderModel().getType().toString().subSequence(0, 5),
//			Boolean.toString(ping).subSequence(0, 4), 
//			Boolean.toString(readerFX.getRfidReaderModel().isEnabled()).subSequence(0, 4), 
//			Boolean.toString(readerFX.getRfidReader().isConnected()).subSequence(0, 4),
//			Boolean.toString(readerFX.getRfidReaderModel().isScanning()).subSequence(0, 4),
//			Boolean.toString(readerFX.getRfidReaderModel().isScanning()).subSequence(0, 4),
//			Boolean.toString(tasks).subSequence(0, 4)
//		));
		
		
	}
//	
//	
//	
//	/*
//	 * Validar si existen tareas según el tipo de lector
//	 */
//	public boolean scanRequired(RfidReaderWrapper readerFX) {
//		
//		boolean result = false;
//		
//		switch (readerFX.getRfidReaderModel().getType()) {
//		case ENTRY:
//			result = otService.existsByOTTYPE("EN");
//			break;
//		case SCRAPPING:
//			result = taskService.existsByACTTYPE(ActTypeEnum.SCU);
//			break;
//		case SHIPMENT:
//			result = taskService.existsByACTTYPE(ActTypeEnum.DEL);
//			
//			break;
//		case INVENTORY:
//			result = inventoryService.existsByStatusAndTechnology(InventoryStatusEnum.OPEN, TechnologyEnum.RFID);
//			break;
//		case RECORD:
//			result = true;
//			break;
//		}
//		
//		return result;
//		
//	}
//	
//	@Async
//	public void sendRfidReaderToMonitor(RfidReaderWrapper rrw) {
//		monitorService.sendRfidReaderToMonitor(rrw.getRfidReaderModel());
//	}
//
}
