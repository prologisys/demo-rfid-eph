package com.prolosys.rfid.microservices.users.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolosys.rfid.common.models.MainModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel extends MainModel{
	
	private static final long serialVersionUID = -1315103524650433195L;
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String telephone;
	private String password;
	private String current;
	private String passwordToken;

	private List<RoleModel> roles;

}
