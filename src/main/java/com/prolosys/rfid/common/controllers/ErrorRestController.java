package com.prolosys.rfid.common.controllers;

import javax.persistence.EntityNotFoundException;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.prolosys.rfid.common.exceptions.UniqueFieldException;
import com.prolosys.rfid.common.models.EndpointResponse;

@ControllerAdvice
public class ErrorRestController{
	
	//Se detectó una calve repetida
	@ExceptionHandler(UniqueFieldException.class)
	public final ResponseEntity<?> handleUniqueFieldException(UniqueFieldException ex, WebRequest request) {
		
		ex.printStackTrace();
		return EndpointResponse.endEx(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, null);
	}
	
	//Se detectó una calve repetida
	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		
		System.out.println(ex.getMessage());
		ex.printStackTrace();
		return EndpointResponse.endEx(ex.getMessage(), HttpStatus.NOT_FOUND, null);
	}
	
	//Hay error generando un PDF
//	@ExceptionHandler(DocumentException.class)
//	public final ResponseEntity<?> handleDocumentException(DocumentException ex, WebRequest request) {
//		ex.printStackTrace();
//		return EndpointResponse.endEx(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
//	}
	
	//Hay un error al guardar en base de datos
	@ExceptionHandler(value = {HibernateException.class, IllegalStateException.class})
	public final ResponseEntity<?> handleHibernateException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		
		StringBuilder message = new StringBuilder();
		if(ex.getMessage().contains("Multiple representations of the same entity") || 
		   ex.getMessage().contains("More than one row with the given identifier was found")) {
			
			message.append("No se permite multiples registros de un mismo ");
		}else {
			message.append(ex.getMessage());
		}
		
		if(ex.getMessage().contains("VendorMaterialEntity") || 
		   ex.getMessage().contains("MaterialConsumptionEntity")) {
			message.append("material.");
		}else {
			message.append("proveedor.");
		}
		
		return EndpointResponse.endEx(message, HttpStatus.BAD_REQUEST, null);
	}
	
}
