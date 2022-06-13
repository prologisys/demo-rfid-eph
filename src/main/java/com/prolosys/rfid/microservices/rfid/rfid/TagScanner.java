//package com.prolosys.rfid.microservices.rfid.rfid;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.prolosys.rfid.common.constants.RfidReaderTypeEnum;
//
//import lombok.Getter;
//
//@Component
//public class TagScanner {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(TagScanner.class);
//	private static final String MTCS = "mtcs";
//	private static final String LENUMS = "lenums";
//	
//	@Getter
//	private Map<String, String> centers = new HashMap<>();
//		
//	@Getter
//	private Map<String, Long> rolls = new HashMap<>();
//	
//	@Autowired
//	private RfidReaderAsync asyncFX;
//	
//	private Map<String, Map<String, Map<String, Integer>>> arcs = new HashMap<>();
//	
//	private String getArc(String string) {
//		String[] arcType = string.split("%");
//		return arcType[0];
//	}
//	
//	private RfidReaderTypeEnum getReaderType(String string) {
//		String[] arcType = string.split("%");
//		RfidReaderTypeEnum readerTypeEnum = RfidReaderTypeEnum.valueOf(arcType[1]);
//		
//		return readerTypeEnum;
//	}
//	
//	@Async
//	public void addMtc(String arc, String mtc, String werks) {
//		
//		try {
//			centers.put(this.getArc(arc), werks);
//			
//			if(!arcs.containsKey(arc)) {
//				arcs.put(arc, new HashMap<>());
//			}
//			if(!arcs.get(arc).containsKey(MTCS)) {
//				arcs.get(arc).put(MTCS, new HashMap<>());
//			}
//			if(!arcs.get(arc).containsKey(LENUMS)) {
//				arcs.get(arc).put(LENUMS, new HashMap<>());
//			}
//			if(!arcs.get(arc).get(MTCS).containsKey(mtc)) {
//				arcs.get(arc).get(MTCS).put(mtc, 0);
//			}
//			
//			arcs.get(arc).get(MTCS).put(mtc, (arcs.get(arc).get(MTCS).get(mtc)+1));
//			
//		} catch (Exception e) {}
//				
//	}
//	
//	
//	
//	@Async
//	public void addRoll(String arc, String lenum, String werks) {
//		
//		try {
//			centers.put(this.getArc(arc), werks);
//			
//			if(!arcs.containsKey(arc)) {
//				arcs.put(arc, new HashMap<>());
//			}
//			if(!arcs.get(arc).containsKey(MTCS)) {
//				arcs.get(arc).put(MTCS, new HashMap<>());
//			}
//			if(!arcs.get(arc).containsKey(LENUMS)) {
//				arcs.get(arc).put(LENUMS, new HashMap<>());
//			}
//			if(!arcs.get(arc).get(LENUMS).containsKey(lenum)) {
//				arcs.get(arc).get(LENUMS).put(lenum, 0);
//			}
//			
//			arcs.get(arc).get(LENUMS).put(lenum, (arcs.get(arc).get(LENUMS).get(lenum)+1));
//		
//			
//		} catch (Exception e) {}
//				
//	}
//	
//	
//	
//	
//	public String getClosestMtc(String arc) {
//		
//		int max = 0;
//		String mtcMax = null;
//		
//		try {
//			if(arcs.containsKey(arc)) {
//				Iterator<?> itMtcs = arcs.get(arc).get("mtcs").entrySet().iterator();
//				while (itMtcs.hasNext()) {
//					Map.Entry<?, ?> entryMtcs = (Map.Entry<?, ?>) itMtcs.next();
//					int veces =  (Integer) entryMtcs.getValue();
//					String mtc = (String) entryMtcs.getKey();
//					if(veces>max) {
//						max = veces;
//						mtcMax = mtc;
//					}
//				}
//			}
//			
//			if(mtcMax==null) {
////				System.out.println(String.format("No hay MTC en arco %s", arc));
//			}else {
////				System.out.println(String.format("MTC %s es el más cercano en arco %s con %s lecturas/s", mtcMax, arc,max));
//			}
//			
//		} catch (Exception e) {
////			e.printStackTrace();
//		}
//		
//		return mtcMax;
//	}
//	
//	
//	
//	public String getClosestRoll(String arc) {
//		
//		int max = 0;
//		String lenumMax = null;
//		
//		try {
//			if(arcs.containsKey(arc)) {
//				Iterator<?> itRolls = arcs.get(arc).get("lenums").entrySet().iterator();
//
//				while (itRolls.hasNext()) {
//					Map.Entry<?, ?> entryMtcs = (Map.Entry<?, ?>) itRolls.next();
//					int veces =  (Integer) entryMtcs.getValue();
//					String lenum = (String) entryMtcs.getKey();
//					
//					if(veces>max) {
//						max = veces;
//						lenumMax = lenum;
//					}
//				}
//			}
//		
//		} catch (Exception e) {}
//
////		System.out.println(String.format("2 Rollos más cercanos en arco %s - \n %s", arc,arcs.get(arc).get("lenums") ));
//		return lenumMax;
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Scheduled(fixedDelay = 3000, initialDelay = 1000 * 15)
//	protected void sendMessages() {
//			
//			Iterator<?> itRolls = rolls.entrySet().iterator();
//			while (itRolls.hasNext()) {
//				Map.Entry<?, ?> entryType = (Map.Entry<?, ?>) itRolls.next();
//				Long rollTime = (Long) entryType.getValue();
//
//				Long current = System.currentTimeMillis();
//				
//				if(current - rollTime>= 15000) {
//					itRolls.remove();
//				}
//			}
//			
//			//Finaliza recorrido de arco
//			Iterator<?> itArcs = arcs.entrySet().iterator();
//			while (itArcs.hasNext()) {
//				Map.Entry<?, ?> entryArcs = (Map.Entry<?, ?>) itArcs.next();
//
//				String arc = (String) entryArcs.getKey();
//				
//				String lenum = this.getClosestRoll(arc);
//				String mtc = this.getClosestMtc(arc);
//				
//				RfidReaderTypeEnum arcType = this.getReaderType(arc);
//				String arcName = this.getArc(arc);
//					
//				if(lenum!=null && mtc!=null) {
//					
//					if(!rolls.containsKey(lenum)){
//						rolls.put(lenum, System.currentTimeMillis());
//					}
//					
//					switch (arcType) {
//					case ENTRY:
//						asyncFX.sendEntry(arcName, mtc, lenum, centers.get(arcName));
//						break;
//					case SHIPMENT:		
//						asyncFX.sendShipment(arcName, mtc, lenum, centers.get(arcName));
//						break;
//					case SCRAPPING:
//						asyncFX.sendScrapping(arcName, mtc, lenum, centers.get(arcName));
//						break;
//					default:
//						break;
//					}
//				}
//			}
//			LOGGER.info(arcs.toString());
//			arcs.clear();
//				
//
//	}
//	
//
//}
