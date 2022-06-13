package com.prolosys.rfid.microservices.masters.repositories;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Positive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prolosys.rfid.common.constants.RfidReaderTypeEnum;
import com.prolosys.rfid.microservices.masters.repositories.entities.RfidReaderEntity;

/**
 * The Interface RfidReaderRepository.
 */
@Repository
public interface RfidReaderRepository extends JpaRepository<RfidReaderEntity, Long> {

	/**
	 * Exists by ip.
	 *
	 * @param ip the ip
	 * @return the boolean
	 */
	Boolean existsByIp(String ip);

	/**
	 * Exists by ip and id not.
	 *
	 * @param ip the ip
	 * @param id the id
	 * @return true, if successful
	 */
	boolean existsByIpAndIdNot(String ip, Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE RfidReaderEntity b set b.enabled =:enabled WHERE b.id = :id")
	void enableById(@Positive Long id, boolean enabled);

	@Query("SELECT b FROM RfidReaderEntity b INNER JOIN b.center c WHERE c.id=:id")
	public List<RfidReaderEntity> findAllByCenterId(@Positive Long id);
	
	@Query("SELECT r.enabled FROM RfidReaderEntity r WHERE r.id=:id")
	public Boolean findEnabledById(Long id);
	
	@Query("SELECT r.reserved FROM RfidReaderEntity r WHERE r.id=:id")
	public Boolean findReservedById(Long id);

	@Query("SELECT r,c FROM RfidReaderEntity r INNER JOIN r.center c WHERE c.werks=:werks AND r.type=:type")
	List<RfidReaderEntity> findAllByCenterWerksAndRfidReaderType(String werks, RfidReaderTypeEnum type);

	
	
	
	
	
	
	@Query("SELECT r,c FROM RfidReaderEntity r INNER JOIN r.center c WHERE r.enabled=:enabled")
	List<RfidReaderEntity> findAllByEnabled(boolean enabled);
	

}
