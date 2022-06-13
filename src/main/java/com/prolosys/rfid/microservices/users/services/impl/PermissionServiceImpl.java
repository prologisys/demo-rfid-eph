package com.prolosys.rfid.microservices.users.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.microservices.users.repositories.PermissionRepository;
import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;
import com.prolosys.rfid.microservices.users.services.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public List<PermissionEntity> findAll() {
		
		List<PermissionEntity> permissionEntityList = (List<PermissionEntity>) permissionRepository.findAll();

		return permissionEntityList;
	}

	@Override
	public PermissionEntity save(PermissionEntity permissionEntity) {

		permissionEntity = permissionRepository.save(permissionEntity);

		return permissionEntity;
	}

	@Override
	public List<PermissionEntity> findAllByRoleId(Long id) {
		
		List<PermissionEntity> permissionsEntity = permissionRepository.findAllByRoleId(id);
		
		return permissionsEntity;
	}

	@Override
	public PermissionEntity findByName(PermissionEnum name) {

		PermissionEntity permissionEntity = permissionRepository.findByName(name);

		return permissionEntity;
	}

	@Override
	public PermissionEntity findByName(String name) {

		PermissionEntity permissionEntity = permissionRepository.findByName(name);

		return permissionEntity;
	}
	
	@Override
	public List<PermissionEntity> findAllByUserId(Long id) {
		
		List<PermissionEntity> permissionsEntityList = permissionRepository.findAllByUserId(id);

		return permissionsEntityList;
	}

}
