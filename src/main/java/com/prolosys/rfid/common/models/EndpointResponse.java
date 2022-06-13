package com.prolosys.rfid.common.models;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EndpointResponse {

	static public CompletableFuture<ResponseEntity<?>> end(Object message, HttpStatus status, Object data) {

		EndpointJson endpointJson = new EndpointJson();

		endpointJson.setMessage(message == null ? status : message.toString());
		endpointJson.setStatus(status.value());
		endpointJson.setData(data);
		endpointJson.setTimestamp(new Date());
		
		return CompletableFuture.completedFuture(new ResponseEntity<>(endpointJson, status));
	}
	
	
	static public ResponseEntity<?> endEx(Object message, HttpStatus status, Object data) {

		EndpointJson endpointJson = new EndpointJson();

		endpointJson.setMessage(message == null ? status : message.toString());
		endpointJson.setStatus(status.value());
		endpointJson.setData(data);
		endpointJson.setTimestamp(new Date());
		
		return new ResponseEntity<>(endpointJson, status);
	}

}
