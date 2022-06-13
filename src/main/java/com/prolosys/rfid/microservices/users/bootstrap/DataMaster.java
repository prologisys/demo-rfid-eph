package com.prolosys.rfid.microservices.users.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.prolosys.rfid.common.bootstrap.ApplicationContextProvider;
import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;
import com.prolosys.rfid.microservices.users.repositories.entities.RoleEntity;
import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;
import com.prolosys.rfid.microservices.users.services.PermissionService;
import com.prolosys.rfid.microservices.users.services.RoleService;
import com.prolosys.rfid.microservices.users.services.UserService;

public class DataMaster {
	
	ApplicationContext context = ApplicationContextProvider.getApplicationContext();
	
	private Environment environment = (Environment) context.getBean(Environment.class);
	private RoleService roleService = (RoleService) context.getBean(RoleService.class);
	private PermissionService permissionService = (PermissionService) context.getBean(PermissionService.class);
	private UserService userService = (UserService) context.getBean(UserService.class);
	private BCryptPasswordEncoder bCryptPasswordEncoder = (BCryptPasswordEncoder) context.getBean(BCryptPasswordEncoder.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(DataMaster.class);
    
    public void generateRootUser() {
    	
    	String rootUsername =environment.getProperty("com.prolosys.rfid.root.username");
    	String rootPassword = environment.getProperty("com.prolosys.rfid.root.password");
    	
    	UserEntity userEntity = new UserEntity();
    	
    	UserEntity rootEntity = null;
    	try {
    		userEntity = userService.findByUsername(rootUsername);
    		
		} catch (EntityNotFoundException e) {
			// TODO: handle exception
		}
    	
        userEntity.setUsername(rootUsername);
        userEntity.setPassword(bCryptPasswordEncoder.encode(rootPassword));
        userEntity.setFirstname("Root");
        userEntity.setLastname("Root");
        userEntity.setEmail("root@prolosys.com");
        userEntity.setEnabled(true);
        userEntity.setReserved(true);
        
    	if(rootEntity==null) {
    		try {
				userService.create(userEntity);
			} catch (UniqueFieldException e) {

			}
    	}else {
    		try {
				userService.update(userEntity);
			} catch (UniqueFieldException e) {

			}
    	}
    	LOGGER.info("Se inicia root");
    }

    @Async
    public void generateAdminUser() {
    	
    	UserEntity userEntity = new UserEntity();
    	RoleEntity roleEntity = new RoleEntity();
    	
    	
    	UserEntity adminUser = null;
    	try {
    		userEntity = userService.findByUsername("admin");
		} catch (EntityNotFoundException e) {
			
		}
    	
    	/*** PERMISOS ***/
        List<PermissionEntity> permissions = new ArrayList<>();
        
        Arrays.asList(PermissionEnum.values()).forEach(permission -> {
            if(permission.name().toString().startsWith(environment.getProperty("app.prefix").toUpperCase())) {
                if(!permission.name().contains("BACK")  && !permission.name().contains("PUBLIC")) {
                	PermissionEntity permissionEntity;
						permissionEntity = permissionService.findByName(permission);
						
						if (permissionEntity == null) {
	                        permissionEntity = new PermissionEntity();
	                        permissionEntity.setName(permission);
	                        permissionEntity.setModule(permission.module().toLowerCase());
	                        permissionEntity.setReserved(true);
	                        permissionEntity = permissionService.save(permissionEntity);
	                    }
						permissions.add(permissionEntity);
						
                }
            }
        });

        
        /*** ROLES ***/
        RoleEntity adminRole  = roleService.findByName("Prologisys");

        if (adminRole != null) {
        	roleEntity = adminRole;
        }
        roleEntity.setName("Prologisys");
        roleEntity.setPermissions(permissions);
        
//        roleEntity.setPermissions(new ArrayList<PermissionEntity>());
//	   
//        for (PermissionEntity permissionEntity : permissions) {
//        	roleEntity.getPermissions().add(permissionEntity);
//		  }
        
    	if(adminRole==null) {
    		try {
    			roleEntity = roleService.create(roleEntity);
			} catch (UniqueFieldException e) {}
    	}else {
    		try {
    			roleEntity = roleService.update(roleEntity);
			} catch (UniqueFieldException e) {}
    	}
        
        /*** USUARIO ***/
        userEntity.setUsername("admin");
        userEntity.setPassword(bCryptPasswordEncoder.encode("admin"));
        userEntity.setFirstname("Administrador");
        userEntity.setLastname("General");
        userEntity.setEmail("acasique@prolosys.com");
        userEntity.setEnabled(true);
        userEntity.setReserved(true);
        userEntity.setRoles(Arrays.asList(roleEntity));

        if(adminUser==null) {
    		try {
				userService.create(userEntity);
			} catch (UniqueFieldException e) {}
    	}else {
    		try {
				userService.update(userEntity);
			} catch (UniqueFieldException e) {}
    	}
        LOGGER.info("Se inicia admin");
    }
}
