package com.prolosys.rfid.microservices.masters.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolosys.rfid.microservices.masters.repositories.RfidReaderRepository;
import com.prolosys.rfid.microservices.masters.repositories.entities.RfidReaderEntity;
import com.prolosys.rfid.microservices.masters.services.RfidReaderService;

@Service
public class RfidReaderServiceImpl implements RfidReaderService {
	
	@Autowired
	RfidReaderRepository rfidReaderRepository;

	@Override
	public List<RfidReaderEntity> findAllByEnabled(boolean enabled) {
		return rfidReaderRepository.findAllByEnabled(enabled);
	}

//	@Autowired
//	private CenterRepository centerRepository;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(CenterService.class);
//
//	@Override
//	public CenterEntity create(CenterEntity centerEntity) throws UniqueFieldException {
//
//		boolean existsName = centerRepository.existsByName(centerEntity.getName());
//
//		if (existsName) {
//			throw new UniqueFieldException("name", "Ya existe un centro con el nombre " + centerEntity.getName());
//		}
//		centerEntity = centerRepository.save(centerEntity);
//
//		LOGGER.info("Se almacenó registro de centro con nombre: " + centerEntity.getName());
//
//		return centerEntity;
//
//	}
//
//	@Override
//	public CenterEntity update(CenterEntity centerEntity) throws UniqueFieldException {
//
//		Optional<CenterEntity> saved = centerRepository.findById(centerEntity.getId());
//
//		if (!saved.isPresent()) {
//			throw new EntityNotFoundException("Registro no encontrado");
//		}
//
//		boolean existsKey = this.existsByNameAndIdNot(centerEntity.getName(), centerEntity.getId());
//
//		if (existsKey) {
//			throw new UniqueFieldException("name", "Ya existe un centro con el nombre " + centerEntity.getName());
//		}
//
//		centerEntity.setCode(saved.get().getCode());
//		centerEntity.setEnabled(saved.get().isEnabled());
//
//		centerEntity = centerRepository.save(centerEntity);
//
//		LOGGER.info("Se actualizó registro de Familia con calave: " + centerEntity.getName());
//
//		return centerEntity;
//	}
//
//
//
//	@Override
//	public CenterEntity findById(Long id) {
//
//		Optional<CenterEntity> centerEntity = centerRepository.findById(id);
//
//		if (!centerEntity.isPresent()) {
//			throw new EntityNotFoundException("Registro no encontrado");
//		}
//
//		return centerEntity.get();
//
//	}
//
//	@Override
//	public CenterEntity enableById(Long id, boolean enabled) {
//
//		Optional<CenterEntity> centerEntity = centerRepository.findById(id);
//
//		if (!centerEntity.isPresent()) {
//			throw new EntityNotFoundException("Registro no encontrado");
//		}
//
//		centerRepository.enableById(id, enabled);
//		centerEntity.get().setEnabled(enabled);
//
//		return centerEntity.get();
//
//	}
//
//	@Override
//	public boolean existsByNameAndIdNot(String name, Long id) {
//
//		Boolean exists = centerRepository.existsByNameAndIdNot(name, id);
//
//		return exists;
//	}
//
//	@Override
//	public CenterEntity findTopByOrderByIdDesc() {
//		CenterEntity centerEntity = centerRepository.findTopByOrderByIdDesc();
//		return centerEntity;
//	}
//
//	@Override
//	public List<CenterEntity> findAllByEnabled(boolean enabled) {
//		
//		List<CenterEntity> centerEntityList = centerRepository.findAllByEnabled(enabled);
//		return centerEntityList;
//	}
//
////	@Override
////	public List<CenterEntity> findAllJoinZoneByCenterEnabled(boolean enabled) {
////		
////		return centerRepository.findAllJoinZoneByCenterEnabled(enabled);
////		
////	}

}
