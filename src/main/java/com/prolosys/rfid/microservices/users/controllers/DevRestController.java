package com.prolosys.rfid.microservices.users.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.microservices.users.models.PermissionModel;

@RestController
@RequestMapping("/dev")
public class DevRestController {
	
	/*
	 * Obtener lista de permisos con formato para properties
	 */
	@GetMapping("/{prefix}/properties")
	public String generateUrlsForProperties(@PathVariable String prefix) {
		StringBuilder sb = new StringBuilder("<!DOCTYPE html><html><head><title></title></head><body><pre>");
        Arrays.asList(PermissionEnum.values()).forEach(permission -> {
        	
        	if(permission.name().toString().startsWith(prefix.toUpperCase()) || permission.name().toString().startsWith("PLS".toUpperCase())) {
        		sb.append(permission.name()+"="+permission.path()+ "\n");
        	}
        	
        });
		sb.append("</pre></body></html>");
		return sb.toString();
	}
	
	/*
	 * Obtener lista de permisos en formato JSON
	 */
//	@GetMapping("${PLS_PUBLIC_PERMISSIONS_BYPREFIX_JSON}")
	@GetMapping("/{prefix}/json")
	public ResponseEntity<?> generateUrlsForJson(@PathVariable String prefix) {
		
		Map<String,PermissionModel > map = new HashMap<String,PermissionModel>();
		
		
        Arrays.asList(PermissionEnum.values()).forEach(permission -> {
        	
        	if(permission.name().toString().startsWith(prefix.toUpperCase()) || permission.name().toString().startsWith("PLS".toUpperCase())) {
				if(!permission.name().contains("BACK")) {
        	
	        		PermissionModel permissionModel = new PermissionModel();
	            	permissionModel.setName(permission.name());
	            	permissionModel.setPath(permission.path());
	            	permissionModel.setMethod(permission.method());
	            	permissionModel.setToken(permission.token());
	            	permissionModel.setDescription(permission.description());
	            	map.put(permission.name(), permissionModel);
				}
        	}
        });
        PermissionModel permissionModel = new PermissionModel();
        permissionModel.setName("APP_HOME");
        map.put(permissionModel.getName(), permissionModel);
        
       return  new ResponseEntity<>(map, HttpStatus.OK);
		
	}
	
	/*
	 * Obtener lista de permisos con foramto para traducir en el PUBLIC
	 */
//	@GetMapping("${PLS_PUBLIC_PERMISSIONS_BYPREFIX_TRANSLATION}")
	@GetMapping("/{prefix}/translate")
	public String generateUrlsForTranslate(@PathVariable String prefix) {
		StringBuilder sb = new StringBuilder("<!DOCTYPE html><html><head><title></title></head><body><pre>");
		
		Arrays.asList(PermissionEnum.values()).forEach(permission -> {
			
			if(permission.name().toString().startsWith(prefix.toUpperCase()) || permission.name().toString().startsWith("PLS".toUpperCase())) {
				if(!permission.name().contains("BACK")  && !permission.name().contains("PUBLIC")) {
					sb.append("\""+permission.name()+"\""+ ":\""+permission.description()+"\",\n");
				}
			}

        });
        
		sb.append("</pre></body></html>");
		return sb.toString();
	}

}
