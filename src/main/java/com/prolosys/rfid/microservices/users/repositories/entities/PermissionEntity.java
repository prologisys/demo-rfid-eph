package com.prolosys.rfid.microservices.users.repositories.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.common.entities.MainEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity
@Table(name = "cat_permissions")
@NoArgsConstructor
@Getter
@Setter
public class PermissionEntity extends MainEntity {

    private static final long serialVersionUID = 4108916380080652726L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PermissionEnum name;
    
    @Column(name = "module")
    private String module;

    @ManyToMany(mappedBy = "permissions")
    private Collection<RoleEntity> roles;

	public PermissionEntity(Long id, PermissionEnum name) {
		super();
		this.id = id;
		this.name = name;
	}

}
