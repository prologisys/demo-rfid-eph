package com.prolosys.rfid.microservices.masters.models;

import java.util.ArrayList;
import java.util.List;

import com.mot.rfid.api3.CableLossCompensation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AntennaModel {
	private int number;
	private boolean connected;

	 
	private List<String> preFilters = new ArrayList<String>();
	
	CableLossCompensation cableLossCompensation;
	
	public AntennaModel(int number) {
		super();
		this.number = number;
	}
	
}
