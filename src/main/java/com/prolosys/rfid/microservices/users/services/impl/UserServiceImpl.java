package com.prolosys.rfid.microservices.users.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.microservices.users.repositories.UserRepository;
import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;
import com.prolosys.rfid.microservices.users.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

//    @Override
//    public List<UserEntity> findAll() {
//
//    	List<UserEntity> userEntityList =(List<UserEntity>) userRepository.findAll();
//
//        return userEntityList;
//    }

    @Override
    public UserEntity findByUsername(String username) throws EntityNotFoundException {
    	
    	Optional<UserEntity> userEntity = userRepository.findByUsername(username);
    	
		if (!userEntity.isPresent()) {
			throw new EntityNotFoundException("Registro no encontrado");
		}

		return userEntity.get();
    }

    
    @Override
    public UserEntity create(UserEntity userEntity) throws UniqueFieldException{
    	
    	boolean existsByEmail = existsByEmailAndIdNot(userEntity.getEmail(), Long.parseLong("0"));
    	if(existsByEmail) {
    		throw new UniqueFieldException("email","El correo electrónico ya esta registrado");
    	}
    	
    	boolean existsByUsername = existsByUsernameAndIdNot(userEntity.getUsername(), Long.parseLong("0"));
    	if(existsByUsername) {
    		throw new UniqueFieldException("username","El nombre de usuario ya esta registrado");
    	}
    	userEntity = userRepository.save(userEntity);
    	
    	return userEntity;

    }
    
    @Override
    public UserEntity update(UserEntity userEntity) throws UniqueFieldException {
    	
    	Optional<UserEntity> saved = userRepository.findById(userEntity.getId());
    	
		if (!saved.isPresent()) {
			throw new EntityNotFoundException("Registro no encontrado");
		}
    	
		boolean existsByEmailAndIdNot = userRepository.existsByEmailAndIdNot(userEntity.getEmail(), userEntity.getId());
		
		if(existsByEmailAndIdNot) {					
			throw new UniqueFieldException("email","El correo electrónico ya esta registrado");
		}
		
		boolean existsByUsernameAndIdNot = userRepository.existsByUsernameAndIdNot(userEntity.getUsername(), userEntity.getId());

		if(existsByUsernameAndIdNot) {
			throw new UniqueFieldException("username","El nombre de usuario ya esta registrado");
		}
		
		userEntity.setUsername(saved.get().getUsername());
		
		return userRepository.save(userEntity);
    }


    @Override
    public UserEntity findById(Long id) {
    	
    	Optional<UserEntity> userEntity = userRepository.findById(id);
    	
		if (!userEntity.isPresent()) {
			throw new EntityNotFoundException("Registro no encontrado");
		}

		return userEntity.get();
		
    }

    @Override
    public boolean existsByUsernameAndIdNot(String username, Long id) {

        boolean exist = userRepository.existsByUsernameAndIdNot(username,id);

        return exist;
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Long id) {

        boolean exist = userRepository.existsByEmailAndIdNot(email,id);

        return exist;
    }

    @Override
    public UserEntity findByEmail(String email) {
    	
    	UserEntity userEntity = userRepository.findByEmail(email);
      
        return userEntity;
    }


	@Override
	public int updatePasswordTokenByEmail(String email, String passwordToken) {
		
		int affectedRow = userRepository.updatePasswordTokenByEmail(email,  passwordToken);
		return affectedRow;
	}

	@Override
	public UserEntity findByEmailAndPasswordToken(String email, String passwordToken) {
		
		Optional<UserEntity> userEntity = userRepository.findByEmailAndPasswordToken(email, passwordToken);
		
		if(userEntity.isPresent()) {
			return userEntity.get();
		}else {
			return null;
		}
	}

	@Override
	public UserEntity enableById(Long id, boolean enable) {
		
		Optional<UserEntity> userEntity = userRepository.findById(id);

		if (!userEntity.isPresent()) {
			throw new EntityNotFoundException("Registro no encontrado");
		}

		userRepository.enableById(id, enable);
		userEntity.get().setEnabled(enable);

		return userEntity.get();
		
	}

	@Override
	public String findPasswordById(Long id) {
		String password = userRepository.findPasswordById(id);
		return password;
	}

	@Override
	public int updatePasswordByUsername(String username, String password) {
		int affectedRows = userRepository.updatePasswordByUsername(username, password);
		return affectedRows;
	}

	@Override
	public List<UserEntity> findAllByUsernameNotAndEnabled(String username, boolean enabled) {
		
		List<UserEntity> userEntityList = userRepository.findAllByUsernameNotAndEnabled(username, enabled);

		return userEntityList;
	}

	@Override
	public String findPasswordByUsername(String username) {
		
		String password = userRepository.findPasswordByUsername(username);
		return password;
	}

}
