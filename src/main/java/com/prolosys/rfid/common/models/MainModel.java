package com.prolosys.rfid.common.models;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainModel implements Serializable {

	private static final long serialVersionUID = 3664426311424310069L;
	
	private Boolean reserved = false;
	private Boolean enabled = true;
	private Date createDate;
	private Date updateDate;

}
