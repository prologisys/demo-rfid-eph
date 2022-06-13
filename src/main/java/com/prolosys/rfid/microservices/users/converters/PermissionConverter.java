package com.prolosys.rfid.microservices.users.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.microservices.users.models.PermissionModel;
import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;

@Component
public class PermissionConverter {

	public PermissionModel permissionEntityToPermissionModel(PermissionEntity permissionEntity) {

		PermissionModel permissionModel = new PermissionModel();
		permissionModel.setId(permissionEntity.getId());
		permissionModel.setName(permissionEntity.getName().toString());
		permissionModel.setModule(permissionEntity.getModule());

		return permissionModel;
	}

	public PermissionEntity permissionModelToPermissionEntity(PermissionModel permissionModel) {
		
		PermissionEntity permissionEntity = new PermissionEntity();
		
		permissionEntity.setId(permissionModel.getId());
		permissionEntity.setName(PermissionEnum.valueOf(permissionModel.getName()));
		permissionEntity.setModule(permissionModel.getModule());

		return permissionEntity;
	}

	public List<PermissionModel> permissionEntityListToPermissionModelList(List<PermissionEntity> permissionEntityList) {
		
		List<PermissionModel> permissionModelList = new ArrayList<PermissionModel>();
		if(permissionEntityList==null) {
			return permissionModelList;
		}

		for (PermissionEntity permissionEntity : permissionEntityList) {
			PermissionModel permissionModel = permissionEntityToPermissionModel(permissionEntity);
			permissionModelList.add(permissionModel);
		}
		return permissionModelList;
	}
	
	public List<PermissionEntity> permissionModelListToPermissionEntityList(List<PermissionModel> permissionModelList) {
		
		List<PermissionEntity> permissionEntityList = new ArrayList<PermissionEntity>();
		if(permissionModelList==null) {
			return permissionEntityList;
		}

		for (PermissionModel permissionModel : permissionModelList) {
			PermissionEntity permissionEntity = permissionModelToPermissionEntity(permissionModel);
			permissionEntityList.add(permissionEntity);
		}
		return permissionEntityList;
	}
	

}
