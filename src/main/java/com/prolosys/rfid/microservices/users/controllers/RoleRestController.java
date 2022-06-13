package com.prolosys.rfid.microservices.users.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.common.models.EndpointResponse;
import com.prolosys.rfid.microservices.users.converters.PermissionConverter;
import com.prolosys.rfid.microservices.users.converters.RoleConverter;
import com.prolosys.rfid.microservices.users.models.PermissionModel;
import com.prolosys.rfid.microservices.users.models.RoleModel;
import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;
import com.prolosys.rfid.microservices.users.repositories.entities.RoleEntity;
import com.prolosys.rfid.microservices.users.services.PermissionService;
import com.prolosys.rfid.microservices.users.services.RoleService;

@RestController
@RequestMapping("${api.prefix}")
public class RoleRestController {
	
	@Autowired
	PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleConverter roleConverter;
    
    @Autowired
    private PermissionConverter permissionConverter;

    /*
     * Consultar un rol por id
     */
    @GetMapping("${SCL_ROLES_VIEW_BYID}")
    public CompletableFuture<ResponseEntity<?>> findRoleById(@PathVariable Long id) {
        RoleEntity roleEntity = roleService.findById(id);
        RoleModel roleModel = roleConverter.roleEntityToRoleModel(roleEntity);
        List<PermissionEntity> permissionEntityList = permissionService.findAllByRoleId(roleEntity.getId());
        List<PermissionModel> permissionModelList = permissionConverter.permissionEntityListToPermissionModelList(permissionEntityList);
        roleModel.setPermissions(permissionModelList);
        
        
        return EndpointResponse.end(String.format("Detalle del rol %s", roleModel.getName()), HttpStatus.OK, roleModel);
    }

    /*
     * Consultar la lista de roles
     */
    @GetMapping("${SCL_ROLES_ACTIVE_LIST}")
    public CompletableFuture<ResponseEntity<?>> findAllActiveRoles() {
    	
    	List<RoleEntity> roleEntityList = roleService.findAllByEnabled(true);
    	List<RoleModel> roleModelList = roleConverter.roleEntityListToRoleModelList(roleEntityList);
    	
    	return EndpointResponse.end("Lista de roles", HttpStatus.OK, roleModelList);
    }
    
    /*
     * Consultar la lista de roles
     */
    @GetMapping("${SCL_ROLES_INACTIVE_LIST}")
    public CompletableFuture<ResponseEntity<?>> findAllInactiveRoles()  {
    	
    	List<RoleEntity> roleEntityList = roleService.findAllByEnabled(false);
    	List<RoleModel> roleModelList = roleConverter.roleEntityListToRoleModelList(roleEntityList);
    	
    	return EndpointResponse.end("Lista de roles", HttpStatus.OK, roleModelList);
    }
    
    

    /*
     * Crear un nuevo Rol
     */
    @PostMapping("${SCL_ROLES_CREATE}")
    public CompletableFuture<ResponseEntity<?>> createRole(@RequestBody RoleModel roleModel) throws UniqueFieldException  {
    	
    	RoleEntity preRoleEntity = roleConverter.roleModelToRoleEntity(roleModel);
    	
    	List<PermissionEntity> permissionEntityList = permissionConverter.permissionModelListToPermissionEntityList(roleModel.getPermissions());
    	preRoleEntity.setPermissions(permissionEntityList);
    	
    	RoleEntity roleEntity = roleService.create(preRoleEntity);
        roleModel = roleConverter.roleEntityToRoleModel(roleEntity);
        return EndpointResponse.end(String.format("Rol %s creado", roleModel.getName()), HttpStatus.OK, roleModel);
    }

    /*
     * Actualizar rol mediante ID
     */
    @PutMapping("${SCL_ROLES_UPDATE_BYID}")
    public CompletableFuture<ResponseEntity<?>> updateRole(@PathVariable int id, @RequestBody RoleModel roleModel) throws UniqueFieldException {

    	RoleEntity preroleEntity = roleConverter.roleModelToRoleEntity(roleModel);
    	List<PermissionEntity> permissionEntityList = permissionConverter.permissionModelListToPermissionEntityList(roleModel.getPermissions());
    	preroleEntity.setPermissions(permissionEntityList);
        RoleEntity roleEntity = roleService.update(preroleEntity);
        roleModel = roleConverter.roleEntityToRoleModel(roleEntity);
        
        return EndpointResponse.end(String.format("Rol %s actualizado", roleModel.getName()), HttpStatus.OK, roleModel);
    }

    /*
     * Habilitar / Deshabilitar rol mediante ID
     */
	@PutMapping("${SCL_ROLES_ENABLE_BYID}")
	public CompletableFuture<ResponseEntity<?>> enabledById(@PathVariable(required = true) @Positive Long id, @PathVariable(name="status", required=true) String status) {

		boolean enable = status.equals("enable") ? true : false;
		
		RoleEntity roleEntity = roleService.enableById(id, enable);
		
		RoleModel roleModel = roleConverter.roleEntityToRoleModel(roleEntity);
		
		return EndpointResponse.end(String.format("Se %s el rol %s ", (enable)? "habilita":"deshabilita", roleModel.getName()), HttpStatus.OK, roleModel);
		
	}
	
	
    /*
     * Consultar toda la lista depermisos de la app
     */
    @GetMapping("${SCL_ROLES_PERMISSIONS_LIST}")
    public CompletableFuture<ResponseEntity<?>> findAll(){
    	List<PermissionEntity> permissionEntityList = permissionService.findAll();
    	List<PermissionModel>  permissionModelList = permissionConverter.permissionEntityListToPermissionModelList(permissionEntityList);
    	return EndpointResponse.end("Lista de permisos", HttpStatus.OK, permissionModelList);
    }
    
    
	@GetMapping("${SCL_ROLES_EXISTS__BY}")
	public CompletableFuture<ResponseEntity<?>> existsBy(@RequestParam(required=true) String field, @RequestParam(required=true) String value,  @RequestParam(required=false, defaultValue = "0") Long id){

		boolean exists = false;
		switch (field) {
		case "name":
			exists  = roleService.existsByNameAndIdNot(value, id);
			break;
		}
		return EndpointResponse.end("Existe valor", HttpStatus.OK, exists);
	} 
    
    
    
    
    
}