package com.prolosys.rfid.microservices.masters.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prolosys.rfid.common.constants.RfidReaderTypeEnum;
import com.prolosys.rfid.common.entities.MainEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_rfid_readers",indexes = {
	@Index(name = "idx_ip",  columnList="ip", unique = true),
	@Index(name = "idx_name",  columnList="name", unique = true),
})
public class RfidReaderEntity extends MainEntity implements Serializable{

	private static final long serialVersionUID = 3495775015560628955L;
		
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", unique = false, nullable = false, length = 10)
	private String name;

	@Column(name = "username", unique = false, nullable = false, length = 10)
	private String username;

	@Column(name = "password", unique = false, nullable = false, length = 10)
	private String password;

	@Column(name = "ip", unique = true, nullable = false, length = 15)
	private String ip;
	
	@Column(name = "location", unique = false, nullable = true, length = 80)
	private String location;

	@Column(name = "port", unique = false, nullable = false, length = 5)
	private Integer port = 0;
	
	@Column(name = "rssi", unique = false, nullable = false, length = 3)
	private Integer rssi = 0;
	
	@Column(name = "type", unique = false, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private RfidReaderTypeEnum type;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "center_id", referencedColumnName = "id", nullable = false, updatable = true )
	private CenterEntity center;

}
