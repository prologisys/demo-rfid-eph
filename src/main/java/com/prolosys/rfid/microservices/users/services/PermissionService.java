package com.prolosys.rfid.microservices.users.services;

import java.util.List;

import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;

public interface PermissionService {
	
	public List<PermissionEntity> findAllByUserId(Long id);

	public List<PermissionEntity> findAll();

	public PermissionEntity save(PermissionEntity permissionEntity);

	public PermissionEntity findByName(PermissionEnum name);

	public PermissionEntity findByName(String name);
	
	public List<PermissionEntity> findAllByRoleId(Long id);
	
}
