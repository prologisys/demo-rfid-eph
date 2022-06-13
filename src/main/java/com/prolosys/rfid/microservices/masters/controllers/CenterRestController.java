package com.prolosys.rfid.microservices.masters.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.common.models.EndpointResponse;
import com.prolosys.rfid.common.swagger.responses.CenterListResponse;
import com.prolosys.rfid.microservices.masters.converters.CenterConverter;
import com.prolosys.rfid.microservices.masters.models.CenterModel;
import com.prolosys.rfid.microservices.masters.repositories.entities.CenterEntity;
import com.prolosys.rfid.microservices.masters.services.CenterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("${api.prefix}")
@Api(tags = "Módulo de centros", description = "Acciones sobre centros")
public class CenterRestController {
	
	
	@Autowired
	private CenterService centerService;
	
	@Autowired 
	private CenterConverter centerConverter;
	

	
//	@PostMapping("${SCL_CENTERS_CREATE}")
//	@ApiOperation(value = "Crear un centro", response = HttpStatus.class)
//	public CompletableFuture<ResponseEntity<?>> create(@RequestBody CenterModel centerModel) throws UniqueFieldException{
//
//		CenterEntity centerEntity = centerConverter.centerModelToCenterEntity(centerModel, Arrays.asList("zone"));
//		
//		ConfigurationEntity configurationEntity = configurationService.findById(ConfigurationEnum.PREFIX_CENTER);
//		
//		CenterEntity lastCenter = centerService.findTopByOrderByIdDesc();
//		
//		// inicio bloque de codigo
//		if (lastCenter == null) {
//			centerEntity.setCode(UtilsSCL.buildCode(configurationEntity.getValue(), "-", "1", 12));
//		} else {
//			String numberString = UtilsSCL.getNumberFromKey(lastCenter.getCode(), "-");
//			int number = UtilsSCL.stringNumberToInt(numberString);
//			String newKey = UtilsSCL.buildCode(configurationEntity.getValue(), "-", Integer.toString(number + 1), 12);
//			centerEntity.setCode(newKey);
//		}
//		// fin bloque de codigo
//		
//		centerEntity = centerService.create(centerEntity);
//		
//		return EndpointResponse.end(String.format("Se crea centro %s",centerEntity.getName()), HttpStatus.CREATED, 
//				centerConverter.centerEntityToCenterModel(centerEntity, Arrays.asList("zone")));
//	
//	}

	@PutMapping("${SCL_CENTERS_UPDATE_BYID}")
	@ApiOperation(value = "Actualizar un centro por ID")
	public CompletableFuture<ResponseEntity<?>> updateCenterById(@PathVariable Long id, @RequestBody CenterModel centerModel) throws UniqueFieldException{
		
		centerModel.setId(id);
		CenterEntity centerEntity = centerConverter.centerModelToCenterEntity(centerModel, Arrays.asList("zone"));
		centerEntity = centerService.update(centerEntity);
		return EndpointResponse.end("Se actualiza el centro " + centerEntity.getName(), HttpStatus.OK, 
				centerConverter.centerEntityToCenterModel(centerEntity, Arrays.asList("zone")));
	}
	
	@PutMapping("${SCL_CENTERS_ENABLE_BYID}")
	@ApiOperation(value = "Habilitar Deshabilitar centros por ID")
	public CompletableFuture<ResponseEntity<?>> enableById(@PathVariable(name="id", required = true) @Positive Long id, @PathVariable(name="status", required=true) String status) throws InterruptedException, ExecutionException{
		
		boolean enabled = status.equals("enable") ? true : false;
		
		CenterEntity centerEntity = centerService.enableById(id, enabled);
		
		CenterModel centerModel = centerConverter.centerEntityToCenterModel(centerEntity, Arrays.asList("zone"));
		
		return EndpointResponse.end(String.format("Se %s el centro %s ", (enabled)? "habilita":"deshabilita", centerModel.getName()), 
				HttpStatus.OK, centerModel);

	}
	
//	@GetMapping("${SCL_CENTERS_ACTIVE_LIST}")
//	@ApiOperation(value = "Listar centros habilitados", response = CenterListResponse.class )
//	public CompletableFuture<ResponseEntity<?>> findEnabledCenters(){
//		
//		List<CenterModel> centerModelList = centerConverter.centerEntityListToCenterModelList(
//				centerService.findAllJoinZoneByCenterEnabled(true), Arrays.asList("zone","productiveU")); 
//		
//		return EndpointResponse.end("Se recupera lista de granjas", HttpStatus.OK, centerModelList);
//	}
//	
//	@GetMapping("${SCL_CENTERS_INACTIVE_LIST}")
//	@ApiOperation(value = "Listar centros habilitados", response = CenterListResponse.class )
//	public CompletableFuture<ResponseEntity<?>> findDisabledCenters(){
//		
//		List<CenterModel> centerModelList = centerConverter.centerEntityListToCenterModelList(
//				centerService.findAllJoinZoneByCenterEnabled(false), Arrays.asList("zone")); 
//		
//		return EndpointResponse.end("Se recupera lista de granjas", HttpStatus.OK, centerModelList);
//	}
	
	
	
	
	@GetMapping("${SCL_CENTERS_VIEW_BYID}")
	@ApiOperation(value = " Ver detalle de centro por ID")
	public CompletableFuture<ResponseEntity<?>> findCenterById(@PathVariable Long id) {
		
		CenterEntity centerEntity = centerService.findById(id);
		
		return EndpointResponse.end("Detalle de centro " + centerEntity.getName(), HttpStatus.OK, 
				centerConverter.centerEntityToCenterModel(centerEntity, Arrays.asList("zone")));
	}
	
	
	@GetMapping("${SCL_CENTERS_EXISTS__BY}")
	@ApiOperation(value = "Validar si existe registro por un campo")
	public CompletableFuture<ResponseEntity<?>> existsBy(@RequestParam(required=true) String field, @RequestParam(required=true) String value,  @RequestParam(required=false, defaultValue = "0") Long id){

		boolean exists = false;
		switch (field) {
		case "name":
			exists  = centerService.existsByNameAndIdNot(value, id);
			break;
		default:
			break;
		}
		return EndpointResponse.end("Existe valor", HttpStatus.OK, exists);
	}

}
