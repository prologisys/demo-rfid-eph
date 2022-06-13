package com.prolosys.rfid.microservices.users.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prolosys.rfid.microservices.users.models.UserModel;
import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;

@Component
public class UserConverter {

    /*
     * Uso exclusivo para iniciar sesión, solo se requiere los datos del usuario con contraseña.
     * No roles ni permisos
     */
    public UserModel userEntityToUserWithPasswordModel(UserEntity userEntity) {
    	
    	if(userEntity ==null) {
    		return null;
    	}

        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFirstname(userEntity.getFirstname());
        userModel.setLastname(userEntity.getLastname());
        userModel.setEnabled(userEntity.getEnabled());
        userModel.setPassword(userEntity.getPassword());
//        userModel.setPasswordToken(userEntity.getPasswordToken());

        return userModel;
    }


    /*
     * Mostrar el detalle de un usuario sin contraseña, con sus roles
     */
    public UserModel userEntityToUserModel(UserEntity userEntity) {

        UserModel userModel = new UserModel();

        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFirstname(userEntity.getFirstname());
        userModel.setLastname(userEntity.getLastname());
        userModel.setEnabled(userEntity.getEnabled());
        
        return userModel;
    }

    public UserModel userEntityToUserModelInfo(UserEntity userEntity) {

        UserModel userModel = new UserModel();

        userModel.setUsername(userEntity.getUsername());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFirstname(userEntity.getFirstname());
        userModel.setLastname(userEntity.getLastname());

        return userModel;
    }

    public UserEntity userModelToUserEntity(UserModel userModel) {

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userModel.getId());
        userEntity.setUsername(userModel.getUsername());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFirstname(userModel.getFirstname());
        userEntity.setLastname(userModel.getLastname());
        userEntity.setPassword(userModel.getPassword());

        return userEntity;
    }

    public List<UserModel> userEntityListToUserModelEntity(List<UserEntity> userEntityList) {
        List<UserModel> models = new ArrayList<UserModel>();
        for (UserEntity userEntity : userEntityList) {
            models.add(userEntityToUserModel(userEntity));
        }
        return models;
    }

}
