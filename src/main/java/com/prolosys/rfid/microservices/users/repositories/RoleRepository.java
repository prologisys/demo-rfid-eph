package com.prolosys.rfid.microservices.users.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prolosys.rfid.microservices.users.repositories.entities.RoleEntity;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Serializable> {

	Optional<RoleEntity> findById(int id);
	
	Optional<RoleEntity> findByName(String  name);
	
	public List<RoleEntity> findAllByEnabled(boolean enabled);
	
	public boolean existsByName(String name);
	
	public boolean existsByNameAndIdNot(String email, Long id);
    
	//Habilitar / Deshabilitar un rol mediante el ID
	@Transactional
	@Modifying
	@Query("UPDATE RoleEntity r set r.enabled =:enable WHERE r.id = :id")
	int enableById(@Param("id") Long id, @Param("enable") boolean enable);

	@Transactional
	@Modifying
	@Query("update RoleEntity r set r.name = :name where r.id = :id")
	int update(@Param("id") int id, @Param("name") String name);

	@Transactional
	@Modifying
	@Query("update RoleEntity r set r.enabled = :enabled where r.id = :id")
	int update(@Param("id") int id, @Param("enabled") boolean enabled);
	
	@Query("SELECT new RoleEntity(r.id, r.name) FROM RoleEntity r INNER JOIN r.users u WHERE u.id=:id ")
	//SELECT c1, c2 FROM Country c1 INNER JOIN c1.neighbors c2
	public List<RoleEntity> findAllByUsersId(@Param("id") Long id);

}
