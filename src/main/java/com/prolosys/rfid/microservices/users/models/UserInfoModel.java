package com.prolosys.rfid.microservices.users.models;

import com.prolosys.rfid.common.models.MainModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserInfoModel extends MainModel{
	private static final long serialVersionUID = 3927741128607036284L;
	private String firstname;
	private String lastname;
	private String email;
}
