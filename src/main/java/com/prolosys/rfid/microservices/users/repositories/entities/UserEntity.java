package com.prolosys.rfid.microservices.users.repositories.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolosys.rfid.common.entities.MainEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity

@Table(name = "cat_users",indexes = {
	@Index(name = "idx_username",  columnList="username", unique = true),
	@Index(name = "idx_email",  columnList="email", unique = true)
})
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends MainEntity {

	private static final long serialVersionUID = 7967930477079216606L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre de usuario no puede estar vacio")
	@Size(min = 4, max = 30)
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password")
	private String password;

	@NotBlank(message = "El campo de nombre no puede estar vacio")
	@Size(min = 1, max = 80, message = "El nombre debe contener minimo un caracter y maximo 80")
	@Column(name = "firstname", nullable = false)
	private String firstname;

	@NotBlank(message = "El campo de apellido no puede estar vacio")
	@Size(min = 1, max = 80, message = "El nombre debe contener minimo un caracter y maximo 80")
	@Column(name = "lastname", length = 80)
	private String lastname;

	@Email(message = "Digite un email valido")
	@Size(min = 6, max = 80, message = "El email debe contener entre 6 y 80 caracteres")
	@Column(name = "email", unique=true, nullable = false)
	private String email;
	
	@Column(name = "password_token", nullable = true)
	private String passwordToken;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( 
        name = "cat_users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
    private Collection<RoleEntity> roles;
    
    
    @Transient
    private List<String> permissions = new ArrayList<>();

	public UserEntity(Long id,
			@NotBlank(message = "El campo de nombre no puede estar vacio") @Size(min = 1, max = 80, message = "El nombre debe contener minimo un caracter y maximo 80") String firstname) {
		super();
		this.id = id;
		this.firstname = firstname;
	}

	
	
}
