package com.prolosys.rfid.microservices.masters.repositories.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolosys.rfid.common.entities.MainEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity
@Table(name = "cat_centers")
@NoArgsConstructor
@Getter
@Setter
public class CenterEntity extends MainEntity{
	
	private static final long serialVersionUID = -607259250456873480L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "code", unique = true, nullable = false, length = 12)
	private String code;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "werks", unique = true, nullable = false)
	private String werks;
	
	@Column(name = "coordinates", unique = false, nullable = false)
	private String coordinates;
	

	@Column(name = "dimension", unique = false, nullable = true)
	private double dimension;
	
//	@JoinColumn(name = "id_zone", nullable = false)
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	private ZoneEntity zone;
	
//	@OneToMany(cascade =  {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "center", fetch = FetchType.LAZY)
//	private List<ProductiveUnitEntity> productiveUnit;
	
//	@OneToMany(cascade =  {CascadeType.MERGE, CascadeType.REFRESH},mappedBy = "center", fetch = FetchType.LAZY)
//	private List<StorageUnitEntity> storageUnit;
	
	
//	@OneToMany(cascade =  {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "center")
//	private List<PurchaseOrderEntity> purchaseOrders;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "center")
//	private List<WarehouseEntity> warehouses;
//	
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "center")
//	private List<ProductiveUnitEntity> productiveUnitList;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "center")
//	private List<ServiceEntity> servicesList;
//	
//	@OneToOne(mappedBy = "center", cascade = CascadeType.ALL)
//	private EntryBatchEntity entryBatch;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "center")
//	private List<StorageUnitEntity> storageUnitList;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "center")
//	private List<NotificationsEntity> notificationList;
}
