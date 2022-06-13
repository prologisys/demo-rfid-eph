package com.prolosys.rfid.microservices.users.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;

public interface UserService {
	
	public String findPasswordByUsername(String username);
	
	public int updatePasswordByUsername(String username, String password);
	
	public String findPasswordById(Long id);

//	public List<UserEntity> findAll();
	
	public List<UserEntity> findAllByUsernameNotAndEnabled(String username, boolean enabled);

	public UserEntity create (UserEntity userEntity) throws UniqueFieldException;

	public UserEntity update(UserEntity UserEntity) throws UniqueFieldException;

	UserEntity findByEmailAndPasswordToken(String email, String passwordToken);
	
	int updatePasswordTokenByEmail(String email, String passwordToken);

	public UserEntity enableById(Long id, boolean enable);

	public UserEntity findById(Long id);

	public UserEntity findByEmail(String email);

	public UserEntity findByUsername(String username) throws EntityNotFoundException;
	
	public boolean existsByEmailAndIdNot(String email, Long id);

	public boolean existsByUsernameAndIdNot(String username, Long id);


}
