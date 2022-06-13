package com.prolosys.rfid.microservices.users.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.common.mail.components.TemplateEmails;
import com.prolosys.rfid.common.mail.services.MailService;
import com.prolosys.rfid.common.models.EndpointResponse;
import com.prolosys.rfid.microservices.users.converters.RoleConverter;
import com.prolosys.rfid.microservices.users.converters.UserConverter;
import com.prolosys.rfid.microservices.users.models.ResetPasswordModel;
import com.prolosys.rfid.microservices.users.models.UserModel;
import com.prolosys.rfid.microservices.users.repositories.entities.RoleEntity;
import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;
import com.prolosys.rfid.microservices.users.services.RoleService;
import com.prolosys.rfid.microservices.users.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("${api.prefix}")
public class UserRestController {
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	@Qualifier("templateEmails")
	private TemplateEmails templateEmails;
	
    @Autowired
    private RoleConverter roleConverter;
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	private static final Gson GSON = new Gson();

	/*
	 * Crear nuevo usuario
	 */
	@PostMapping("${SCL_USERS_CREATE}")
	public CompletableFuture<ResponseEntity<?>> createUser(@RequestBody UserModel userModel) throws UniqueFieldException{
		
//		String string = GSON.toJson(userModel);
		
		
		userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
		UserEntity userEntity = userConverter.userModelToUserEntity(userModel);
		userEntity.setRoles(roleConverter.roleModelListToRoleEntityList(userModel.getRoles()));
		userEntity = userService.create(userEntity);

		return EndpointResponse.end("Se creó el usuario "+ userEntity.getUsername(), HttpStatus.OK, userConverter.userEntityToUserModel(userEntity));
	}
	
	/*
	 * Actualizar usuario mediante el ID
	 */
	@PutMapping("${SCL_USERS_UPDATE_BYID}")
	public CompletableFuture<ResponseEntity<?>> updateUser(@PathVariable Long id, @RequestBody UserModel userModel) throws UniqueFieldException{
		
		userModel.setId(id);

		if (userModel.getPassword() == null) {
			userModel.setPassword(userService.findPasswordById(id));
		} else {
//			System.out.println("Se va ancriptar la contraseña: "+ userModel.getPassword());
			userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
		}

		UserEntity userEntity = userConverter.userModelToUserEntity(userModel);
		userEntity.setRoles(roleConverter.roleModelListToRoleEntityList(userModel.getRoles()));
		userEntity = userService.update(userEntity);

		return EndpointResponse.end("Se actualizó el usuario "+ userEntity.getUsername(), HttpStatus.OK, userConverter.userEntityToUserModel(userEntity));
	}

	/*
	 * Actualizar password de usuario que inicia sesion
	 */
	@PutMapping("${SCL_USERS_UPDATE_MY_PASSWORD}")
	public CompletableFuture<ResponseEntity<?>> updateMyPassword(Authentication authentication, @RequestBody UserModel userModel) {
		
		String current = userService.findPasswordByUsername(authentication.getName());
		if(bCryptPasswordEncoder.matches(userModel.getCurrent(), current)) {
			userService.updatePasswordByUsername(authentication.getName(), bCryptPasswordEncoder.encode(userModel.getPassword()));
			return EndpointResponse.end("Se actualizó la contraseña", HttpStatus.OK, null);
		}else {
			return EndpointResponse.end("La contraseña actual no es correcta", HttpStatus.BAD_REQUEST, null);
		}
	}
	
	/*
	 * Habilitar / deshabilitar mediante ID de usuario
	 */
	@PutMapping("${SCL_USERS_ENABLE_BYID}")
	public CompletableFuture<ResponseEntity<?>> enabledById(@PathVariable(name="id", required = true) @Positive Long id, @PathVariable(name="status", required=true) String status){

		boolean enabled = status.equals("enable") ? true : false;
		
		UserEntity userEntity = userService.enableById(id, enabled);
		
		UserModel userModel = userConverter.userEntityToUserModel(userEntity);
		
		return EndpointResponse.end(String.format("Se %s el usuario %s ", (enabled)? "habilita":"deshabilita", userEntity.getUsername()), HttpStatus.OK, userModel);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	 * Consultar usuario mediante el ID
	 */
	@GetMapping("${SCL_USERS_VIEW_BYID}")
	public CompletableFuture<ResponseEntity<?>> findUserById(@PathVariable Long id) {
		UserEntity userEntity = userService.findById(id);
		List<RoleEntity> roleEntityList =  roleService.findAllByUserId(id);
		
		userEntity.setRoles(roleEntityList);
		
		UserModel userModel = userConverter.userEntityToUserModel(userEntity);
		userModel.setRoles(roleConverter.roleEntityListToRoleModelList(roleEntityList));
		
		return EndpointResponse.end("Detalle de usuario", HttpStatus.OK, userModel);
	}

	
	/*
	 * Consultar existencia de usuario mediante el ID 
	 */
//	@GetMapping("${SCL_USERS_EXISTS_BY__USERNAME_BYUSERNAME}")
//	public ResponseEntity<?> existsByUsername(@PathVariable String username) {
//		
//		boolean existsByUsername = userService.existsByUsername(username);
//		
//		return EndpointResponse.end("Exists "+username, HttpStatus.OK, existsByUsername);
//		
//	}
	
	/*
	 * Buscar usuario por nombre de usuario
	 * /api/users/search/username?username=username buscado
	 */
//	@GetMapping("${SCL_USERS_EXISTS_BY__EMAIL_BYEMAIL}")
//	public ResponseEntity<?> existsByEmail(@PathVariable String email) {
////		UserEntity userEntity = userService.findByUsername(username);
////		UserModel userModel = userConverter.userEntityToUserWithPasswordModel(userEntity);
//
//		return EndpointResponse.end("Existe usuario por nombre de usuario", HttpStatus.OK, null);
//	}
	

	/*
	 * Consultar lista de usuarios
	 */
	
	@GetMapping("${SCL_USERS_ACTIVE_LIST}")
//	@PreAuthorize("hasAuthority('USER_LIST')")
	public CompletableFuture<ResponseEntity<?>> findAllActiveUsers() {
		
		List<UserEntity> userEntityList = userService.findAllByUsernameNotAndEnabled("root", true);
		List<UserModel> userModelList = userConverter.userEntityListToUserModelEntity(userEntityList);
		
		return EndpointResponse.end("User List", HttpStatus.OK, userModelList);

	}
	
	
	@GetMapping("${SCL_USERS_INACTIVE_LIST}")
//	@PreAuthorize("hasAuthority('USER_LIST')")
	public CompletableFuture<ResponseEntity<?>> findAllInactiveUsers() {
		
		List<UserEntity> userEntityList = userService.findAllByUsernameNotAndEnabled("root", false);
		List<UserModel> userModelList = userConverter.userEntityListToUserModelEntity(userEntityList);
		
		return EndpointResponse.end("User List", HttpStatus.OK, userModelList);
	}
	
	
	
	
	

//	/*
//	 * Buscar usuario por nombre de usuario
//	 */
//	@GetMapping("${SCL_BACK_USERS_BY__USERNAME_BYUSERNAME}")
//	public ResponseEntity<?> findByUsername(@PathVariable String username) {
//		UserEntity userEntity = userService.findByUsername(username);
//		UserModel userModel = userConverter.userEntityToUserWithPasswordModel(userEntity);
//
//		return new ResponseEntity<>(userModel, HttpStatus.OK);
//	}


	
	
	
	/*
	 * Solicitar la recuperación de contraseña del usuario
	 */
	@PutMapping("${SCL_PUBLIC_USERS_RECOVER_PASSWORD}")
	public CompletableFuture<ResponseEntity<?>> recoverPassword(@RequestBody ResetPasswordModel resetPasswordModel,
			@RequestHeader(value = "referer", required = false) final String referer) {
		
		UserEntity userEntity = userService.findByEmail(resetPasswordModel.getEmail());

    	if(userEntity!=null) {
    		userEntity.setPasswordToken(UUID.randomUUID().toString().replace("-", ""));
    		
    		userService.updatePasswordTokenByEmail(resetPasswordModel.getEmail(), userEntity.getPasswordToken());
    				
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("firstname", userEntity.getFirstname());
			map.put("frontendUrl", referer +"#/password/reset?passwordToken="+userEntity.getPasswordToken() + "&email=" + userEntity.getEmail());
			String body = templateEmails.recoverPassword(map);
			String subject = "Solicitud de cambio de contraseña - SCL";
			mailService.send("no-reply@prolosys.com", userEntity.getEmail(), subject, body);
			
		}
    	
    	return EndpointResponse.end("Si está registrado el correo se le enviará un mensaje con las instrucciones para actualizar la contraseña.", HttpStatus.OK, null);
	}

	/*
	 * Resetear la contraseña olvidada de un usuario
	 */
	@PutMapping("${SCL_PUBLIC_USERS_RESET_PASSWORD}")
	public CompletableFuture<ResponseEntity<?>> resetPassword(@RequestBody ResetPasswordModel resetPasswordModel) {
		
		UserEntity userEntity = userService.findByEmailAndPasswordToken(resetPasswordModel.getEmail(), resetPasswordModel.getPasswordToken());

    	if(userEntity!=null) {
    		
			UserModel userModel = new UserModel();
			userModel.setPassword(resetPasswordModel.getPassword());
			
			userService.updatePasswordByUsername(userEntity.getUsername(), bCryptPasswordEncoder.encode(resetPasswordModel.getPassword()));
			userService.updatePasswordTokenByEmail(userEntity.getEmail(), null);
			
			return EndpointResponse.end("Contraseña actualizada. Puede iniciar sesión", HttpStatus.OK, null);
    			
		}else {
			return EndpointResponse.end("Verifique los datos suministrados", HttpStatus.BAD_REQUEST, null);
		}
    	
	}
	
	
	
	
	@GetMapping("${SCL_USERS_EXISTS__BY}")
	@ApiOperation(value = "Validar si existe registro por un campo")
	public CompletableFuture<ResponseEntity<?>> existsBy(@RequestParam(required=true) String field, @RequestParam(required=true) String value,  @RequestParam(required=false, defaultValue = "0") Long id){

		boolean exists = false;
		switch (field) {
		case "username":
			exists  = userService.existsByUsernameAndIdNot(value, id);
			break;
		case "email":
			exists  = userService.existsByEmailAndIdNot(value, id);
			break;
		}
		return EndpointResponse.end("Existe valor", HttpStatus.OK, exists);
	}
	
}
