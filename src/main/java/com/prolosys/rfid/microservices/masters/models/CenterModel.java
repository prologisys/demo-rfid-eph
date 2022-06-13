package com.prolosys.rfid.microservices.masters.models;

import java.util.List;

import com.prolosys.rfid.common.entities.MainEntity;

//import io.swagger.annotations.ApiModel;
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
public class CenterModel extends MainEntity{

	private static final long serialVersionUID = -7614079647306350521L;

	private Long id;
	private String code;
	private String name;
	private String coordinates;
//	private ZoneModel zone;
	private double dimension;
//	private List<ProductiveUnitModel> productiveUnits;
//	private List<StorageUnitModel> storageUnits;
	
}














