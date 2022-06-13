package com.prolosys.rfid.common.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
	
	private Object message;
	private int status;
	private Date timestamp = new Date(System.currentTimeMillis());
	

}
