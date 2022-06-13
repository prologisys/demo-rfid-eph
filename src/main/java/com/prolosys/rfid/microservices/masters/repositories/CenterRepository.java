package com.prolosys.rfid.microservices.masters.repositories;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prolosys.rfid.microservices.masters.repositories.entities.CenterEntity;

@Repository
public interface CenterRepository extends PagingAndSortingRepository<CenterEntity, Serializable>{

	public boolean existsByName(String name);
	
	public boolean existsByNameAndIdNot(String name, Long id);
	
//	public List<CenterEntity> findAllByEnabled(boolean enabled);
	
	public CenterEntity findTopByOrderByIdDesc();
	
	@Query("SELECT c.enabled FROM CenterEntity c WHERE c.id = :id")
	public boolean findEnabledById(Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE CenterEntity c set c.enabled =:enable WHERE c.id = :id")
	public int enableById(@Param("id") Long id, @Param("enable") boolean enable);

	public List<CenterEntity> findAllByEnabled(boolean enabled);
	
//	@Query("SELECT c,z FROM CenterEntity c INNER JOIN c.zone z where c.enabled=:enabled")
//	public List<CenterEntity> findAllJoinZoneByCenterEnabled(boolean enabled);
	
	
}
