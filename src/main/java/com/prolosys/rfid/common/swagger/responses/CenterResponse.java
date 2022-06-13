package com.prolosys.rfid.common.swagger.responses;

import com.prolosys.rfid.common.models.ResponseModel;
import com.prolosys.rfid.microservices.masters.models.CenterModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CenterResponse extends ResponseModel{
	CenterModel data;
}
