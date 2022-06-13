package com.prolosys.rfid.microservices.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolosys.rfid.common.models.MainModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordModel extends MainModel{
	
	private static final long serialVersionUID = 2146630575046782410L;
	private String password;
	private String confirmPassword;
	private String username;
}
