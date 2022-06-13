package com.prolosys.rfid.microservices.users.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.microservices.users.repositories.RoleRepository;
import com.prolosys.rfid.microservices.users.repositories.entities.RoleEntity;
import com.prolosys.rfid.microservices.users.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public RoleEntity findById(Long id) {
		Optional<RoleEntity> roleEntity = roleRepository.findById(id);
		if(roleEntity.isPresent()) {
			return roleEntity.get();
		}else {
			return null;
		}
	}

	@Override
	public RoleEntity create(RoleEntity roleEntity) throws UniqueFieldException {
		boolean existsByName = roleRepository.existsByName(roleEntity.getName());
		if (existsByName) {
			throw new UniqueFieldException("name", "El nombre de rol ya esta registrado");
		}
		roleEntity = roleRepository.save(roleEntity);
		return roleEntity;
	}

	@Override
	public RoleEntity update(RoleEntity roleEntity) throws UniqueFieldException {
		boolean existsByName = roleRepository.existsByNameAndIdNot(roleEntity.getName(), roleEntity.getId());
		if (existsByName) {
			throw new UniqueFieldException("name", "El nombre de rol ya esta registrado");
		}
		roleEntity = roleRepository.save(roleEntity);
		
		return roleEntity;
	}

	@Override
	public RoleEntity enableById(Long id, boolean enable) {
		
		
		Optional<RoleEntity> roleEntity = roleRepository.findById(id);

		if (!roleEntity.isPresent()) {
			throw new EntityNotFoundException("Registro no encontrado");
		}

		roleRepository.enableById(id, enable);
		roleEntity.get().setEnabled(enable);

		return roleEntity.get();
	}

	@Override
	public List<RoleEntity> findAllByUserId(Long id) {
		
		List<RoleEntity> roleEntityList = roleRepository.findAllByUsersId(id);
		
		return roleEntityList;
	}

	@Override
	public RoleEntity findByName(String name) {
		
		Optional<RoleEntity> roleEntity = roleRepository.findByName(name);
		
		if(roleEntity.isPresent()) {
			return roleEntity.get();
		}else {
			return null;
		}
		
	}

	@Override
	public List<RoleEntity> findAllByEnabled(boolean enabled) {
		
		List<RoleEntity> roleEntityList = roleRepository.findAllByEnabled(enabled);
		
		return roleEntityList;
		
	}

	@Override
	public boolean existsByNameAndIdNot(String name, Long id) {
		
		boolean exists = roleRepository.existsByNameAndIdNot(name, id);
		
		return exists;
	}

}
