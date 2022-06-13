package com.prolosys.rfid.microservices.masters.services;

import java.util.List;

import com.prolosys.rfid.microservices.masters.repositories.entities.RfidReaderEntity;

public interface RfidReaderService {
	
	public List<RfidReaderEntity> findAllByEnabled(boolean enabled);
	
//	public CenterEntity create(CenterEntity centerEntity) throws UniqueFieldException;
//	
//	public CenterEntity update(CenterEntity centerEntity) throws UniqueFieldException;
//	
////	public List<CenterEntity> findAllByEnabled(boolean enabled);
//	
////	public List<CenterEntity> findAllJoinZoneByCenterEnabled(boolean enabled);
//	
//	public CenterEntity findById(Long id);
//	
//	public CenterEntity enableById(Long id, boolean enabled);
//	
//	public boolean existsByNameAndIdNot(String name, Long id);
//	
//	public CenterEntity findTopByOrderByIdDesc();


}
