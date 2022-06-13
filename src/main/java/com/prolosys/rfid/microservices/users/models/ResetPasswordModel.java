package com.prolosys.rfid.microservices.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolosys.rfid.common.models.MainModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class ResetPasswordModel extends MainModel{
	
	private static final long serialVersionUID = -2065788817078248070L;
	private String email;
	private String passwordToken;
	private String password;
	private String rePassword;

}
