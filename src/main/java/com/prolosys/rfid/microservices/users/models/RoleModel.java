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
@JsonIgnoreProperties(value = { "loading" })
public class RoleModel extends MainModel{

	private static final long serialVersionUID = -7534545976347577281L;
	private Long id;
    private String name;
    private String description;
    private List<PermissionModel> permissions;
}
