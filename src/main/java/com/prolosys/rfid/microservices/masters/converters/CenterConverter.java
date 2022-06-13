package com.prolosys.rfid.microservices.masters.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prolosys.rfid.microservices.masters.models.CenterModel;
import com.prolosys.rfid.microservices.masters.repositories.entities.CenterEntity;

@Component
public class CenterConverter {
	
//	@Autowired
//	private ZoneConverter zoneConverter;
//	
//	@Autowired
//	private ProductiveUnitConverter productiveUnitConverter;
	
//	@Autowired
//	private StorageUnitConverter storageUnitConverter;

	public CenterModel centerEntityToCenterModel (CenterEntity centerEntity, List<String> params) {
		
		CenterModel centerModel = new CenterModel();
		
		centerModel.setId(centerEntity.getId());
		centerModel.setCode(centerEntity.getCode());
		centerModel.setName(centerEntity.getName());
		centerModel.setCoordinates(centerEntity.getCoordinates());
		centerModel.setDimension(centerEntity.getDimension());
		centerModel.setEnabled(centerEntity.getEnabled());
		
//		if(params.contains("zone")) {
//			centerModel.setZone(zoneConverter.zoneEntityToZoneModel(centerEntity.getZone()));
//		}
//		
//		if(params.contains("productiveU")) {
//			centerModel.setProductiveUnits(productiveUnitConverter.productiveUnitEntityListToProductiveUnitModelList(centerEntity.getProductiveUnit(), params));
//		}
//		if(params.contains("storageUnits")) {
//			centerModel.setStorageUnits(storageUnitConverter.storageUnitEntityListToStorageUnitModelList(centerEntity.getStorageUnit(), params));
//		}
		
		return centerModel;
	}
	
	public CenterEntity centerModelToCenterEntity (CenterModel centerModel, List<String> params) {
		
		CenterEntity centerEntity = new CenterEntity();
		
		centerEntity.setId(centerModel.getId());
		centerEntity.setName(centerModel.getName());
		centerEntity.setCoordinates(centerModel.getCoordinates());
		centerEntity.setDimension(centerModel.getDimension());

//		if(params.contains("zone")) {
//			centerEntity.setZone(zoneConverter.zoneModelToZoneEntity(centerModel.getZone()));
//		}
		
		return centerEntity;
	}
	
	public List<CenterModel> centerEntityListToCenterModelList (List<CenterEntity> centerEntityList,List<String> params){
		
		List<CenterModel> models = new ArrayList<CenterModel>();
		for (CenterEntity centerEntity : centerEntityList) {
			models.add(centerEntityToCenterModel(centerEntity,params));
		}
		return models;
		
	}
	
}
