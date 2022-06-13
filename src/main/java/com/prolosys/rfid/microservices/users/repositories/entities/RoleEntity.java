package com.prolosys.rfid.microservices.users.repositories.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolosys.rfid.common.entities.MainEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cat_roles",indexes = {
		@Index(name = "idx_name",  columnList="name", unique = true)
	})

public class RoleEntity extends MainEntity {

	private static final long serialVersionUID = 3226738079272571620L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "description")
	private String description;
	
    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;
	
	@ManyToMany
    @JoinTable(
        name = "cat_roles_permissions", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "permission_id", referencedColumnName = "id"))
    private Collection<PermissionEntity> permissions;

	public RoleEntity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	} 
	
	

}
