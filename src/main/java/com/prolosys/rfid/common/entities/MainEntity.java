package com.prolosys.rfid.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class MainEntity  implements Serializable {

	private static final long serialVersionUID = -3305734872457195186L;

	@Column(name = "is_reserved", nullable = false, updatable = true)
	private Boolean reserved = false;
	
	@Column(name = "is_enabled", nullable = false, updatable = true)
	private Boolean enabled = true;

	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, updatable = false, precision=10, scale=2)
	private Date createDate;

	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false, updatable = true, precision=10, scale=2)
	private Date updateDate;

	@PrePersist
	protected void onCreate() {
		updateDate = createDate = new Date(System.currentTimeMillis());
	}

	@PreUpdate
	protected void onUpdate() {
		updateDate = new Date(System.currentTimeMillis());
	}

}