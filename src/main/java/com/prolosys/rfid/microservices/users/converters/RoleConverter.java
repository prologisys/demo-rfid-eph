package com.prolosys.rfid.microservices.users.converters;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prolosys.rfid.microservices.users.models.RoleModel;
import com.prolosys.rfid.microservices.users.repositories.entities.RoleEntity;

@Component
public class RoleConverter {

    public RoleEntity roleModelToRoleEntity(RoleModel roleModel) {

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId(roleModel.getId());
        roleEntity.setName(roleModel.getName());
        roleEntity.setDescription(roleModel.getDescription());
        roleEntity.setEnabled(roleModel.getEnabled());

        return roleEntity;
    }

    public RoleModel roleEntityToRoleModel(RoleEntity roleEntity) {

        RoleModel roleModel = new RoleModel();

        roleModel.setId(roleEntity.getId());
        roleModel.setName(roleEntity.getName());
        roleModel.setDescription(roleEntity.getDescription());
        roleModel.setEnabled(roleEntity.getEnabled());

        return roleModel;
    }
    
    public List<RoleModel> roleEntityListToRoleModelList(List<RoleEntity> roleEntityList){
    	List<RoleModel> roleModelList = new ArrayList<RoleModel>();
    	for (RoleEntity roleEntity : roleEntityList) {
			RoleModel roleModel = this.roleEntityToRoleModel(roleEntity);
			roleModelList.add(roleModel);
		}
    	return roleModelList;
    }
    
	public List<RoleEntity> roleModelListToRoleEntityList(List<RoleModel> roleModelList) {
		List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();

		for (RoleModel roleModel : roleModelList) {
			roleEntityList.add(this.roleModelToRoleEntity(roleModel));
		}
		return roleEntityList;
	}

}
