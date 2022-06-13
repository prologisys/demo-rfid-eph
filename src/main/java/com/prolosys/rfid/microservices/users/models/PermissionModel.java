package com.prolosys.rfid.microservices.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class PermissionModel{
	
	private Long id;
    private String name;
    private String path;
    private String method;
    private String module;
    private boolean token;
    private String description;
}
