package com.prolosys.rfid.microservices.users.services;

import java.util.List;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.microservices.users.repositories.entities.RoleEntity;

public interface RoleService {
	
	public List<RoleEntity> findAllByEnabled(boolean enabled);

	public RoleEntity findById(Long id);
	
	public RoleEntity findByName(String name);

	public RoleEntity create (RoleEntity roleEntity) throws UniqueFieldException;

	public RoleEntity update(RoleEntity roleEntity) throws UniqueFieldException;

	public RoleEntity enableById(Long id, boolean enable);

	public List<RoleEntity> findAllByUserId(Long id);
	
	public boolean existsByNameAndIdNot(String name, Long id);
}
