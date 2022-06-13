package com.prolosys.rfid.microservices.masters.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prolosys.rfid.microservices.masters.models.RfidReaderModel;
import com.prolosys.rfid.microservices.masters.repositories.entities.RfidReaderEntity;

@Component
public class RfidReaderConverter {
	
	@Autowired
	private CenterConverter centerConverter;
	
	
	public RfidReaderEntity rfidReaderModelToRfidReaderEntity(RfidReaderModel rfidReaderModel, List<String> relations) {
		
		RfidReaderEntity rfidReaderEntity = new RfidReaderEntity();
		
		rfidReaderEntity.setId(rfidReaderModel.getId());
		rfidReaderEntity.setName(rfidReaderModel.getName());
		rfidReaderEntity.setLocation(rfidReaderModel.getLocation());
		rfidReaderEntity.setIp(rfidReaderModel.getIp());
		rfidReaderEntity.setPort(rfidReaderModel.getPort());
		
		rfidReaderEntity.setType(rfidReaderModel.getType());
		rfidReaderEntity.setEnabled(rfidReaderModel.getEnabled());
		rfidReaderEntity.setUsername(rfidReaderModel.getUsername());
		rfidReaderEntity.setPassword(rfidReaderModel.getPassword());
		rfidReaderEntity.setRssi(rfidReaderModel.getRssi());
		
		
		
		
		if(relations.contains("center")) {
			rfidReaderEntity.setCenter(centerConverter.centerModelToCenterEntity(rfidReaderModel.getCenter(), Arrays.asList()));
		}
				
		return rfidReaderEntity;
	}
	
	public RfidReaderModel rfidReaderEntityToRfidReaderModel(RfidReaderEntity rfidReaderEntity, List<String> relations) {
		
		RfidReaderModel rfidReaderModel = new RfidReaderModel();
		
		rfidReaderModel.setId(rfidReaderEntity.getId());
		rfidReaderModel.setName(rfidReaderEntity.getName());
		rfidReaderModel.setLocation(rfidReaderEntity.getLocation());
		rfidReaderModel.setIp(rfidReaderEntity.getIp());
		rfidReaderModel.setPort(rfidReaderEntity.getPort());
		rfidReaderModel.setEnabled(rfidReaderEntity.getEnabled());
		rfidReaderModel.setCreateDate(rfidReaderEntity.getCreateDate());
		rfidReaderModel.setUpdateDate(rfidReaderEntity.getUpdateDate());
		rfidReaderModel.setEnabled(rfidReaderEntity.getEnabled());
		rfidReaderModel.setUsername(rfidReaderEntity.getUsername());
		rfidReaderModel.setPassword(rfidReaderEntity.getPassword());
		rfidReaderModel.setType(rfidReaderEntity.getType());
		rfidReaderModel.setRssi(rfidReaderEntity.getRssi());
		
		// Relación WarehouseModel-WarehouseEntity
		if (relations.contains("center")) {
			rfidReaderModel.setCenter(centerConverter.centerEntityToCenterModel(rfidReaderEntity.getCenter(), Arrays.asList()));
		}
		
		return rfidReaderModel;
	}
	
	public List<RfidReaderEntity> rfidReaderModelListToRfidReaderEntityList(List<RfidReaderModel> rfidReaderModelList, List<String> relations){
		
		List<RfidReaderEntity> rfidReaderEntityList = new ArrayList<>();

		for (RfidReaderModel rfidReaderModel : rfidReaderModelList) {
			RfidReaderEntity rfidReaderEntity = rfidReaderModelToRfidReaderEntity(rfidReaderModel,relations);
			rfidReaderEntityList.add(rfidReaderEntity);
		}
		
		return rfidReaderEntityList;
	}
	
	
	public List<RfidReaderModel> rfidReaderEntityListToRfidReaderModelList(List<RfidReaderEntity> rfidReaderEntityList, List<String> relations){
		
		List<RfidReaderModel> rfidReaderModelList = new ArrayList<>();

		for (RfidReaderEntity rfidReaderEntity : rfidReaderEntityList) {
			RfidReaderModel rfidReaderModel = rfidReaderEntityToRfidReaderModel(rfidReaderEntity,relations);
			rfidReaderModelList.add(rfidReaderModel);
		}
		
		return rfidReaderModelList;
	}
	
}
