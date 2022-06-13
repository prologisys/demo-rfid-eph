package com.prolosys.rfid.microservices.users.repositories;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;

@Repository
public interface PermissionRepository extends PagingAndSortingRepository<PermissionEntity, Serializable> {

	PermissionEntity findById(int id);
	
	PermissionEntity findByName(PermissionEnum name);

	PermissionEntity findByName(String name);

	@Modifying
	@Transactional
	@Query("UPDATE PermissionEntity p SET enable = :enable WHERE p.id = :id")
    int enableById(@Param("enable") boolean enable, @Param("id") int id);

    @Query("SELECT DISTINCT new PermissionEntity(p.id, p.name) FROM PermissionEntity p INNER JOIN p.roles r INNER JOIN r.users u WHERE u.id=:id")
	public List<PermissionEntity> findAllByUserId(@Param("id") Long id);
    
    @Query("SELECT DISTINCT new PermissionEntity(p.id, p.name) FROM PermissionEntity p INNER JOIN p.roles r WHERE r.id=:id")
    public List<PermissionEntity> findAllByRoleId(@Param("id") Long id);
	
}
