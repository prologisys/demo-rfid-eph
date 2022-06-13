//package com.prolosys.rfid.microservices.users.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.prolosys.rfid.common.models.EndpointResponse;
//import com.prolosys.rfid.microservices.users.converters.PermissionConverter;
//import com.prolosys.rfid.microservices.users.models.PermissionModel;
//import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;
//import com.prolosys.rfid.microservices.users.services.PermissionService;
//
//@RestController
//@RequestMapping("${api.prefix}")
//public class PermissionRestController {
//
//    @Autowired
//    private PermissionService permissionService;
//    
//    @Autowired
//    private PermissionConverter permissionConverter;
//
//    /*
//     * Consultar toda la lista depermisos de la app
//     */
//    @GetMapping("${SCL_PERMISSIONS_LIST}")
//    public ResponseEntity<?> findAll() {
//    	List<PermissionEntity> permissionEntityList = permissionService.findAll();
//    	List<PermissionModel>  permissionModelList = permissionConverter.permissionEntityListToPermissionModelList(permissionEntityList);
//    	return EndpointResponse.end("Lista de permisos", HttpStatus.OK, permissionModelList);
//    }
//    
//
////	/*
////	 * Buscar permisos por id de usuario
////	 */
////	@GetMapping("${SCL_BACK_PERMISSIONS_USERS_BYID}")
////	public ResponseEntity<?> findPermissionsByUserId(@PathVariable Long id) {
////		List<PermissionEntity> permissionEntityList = permissionService.findAllByUserId(id);
////		List<PermissionModel> permissionModelList = permissionConverter.permissionEntityListToPermissionModelList(permissionEntityList);
////		
////		System.out.println("Permisos por usuario id "+ id+ " " +permissionModelList);
////		return new ResponseEntity<>(permissionModelList, HttpStatus.OK);
////		
////	}
//    
//}
