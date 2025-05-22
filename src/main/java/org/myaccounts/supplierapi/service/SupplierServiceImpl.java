package org.myaccounts.supplierapi.service;

import java.util.List;
import java.util.Optional;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.supplierapi.entity.SupplierEntity;
import org.myaccounts.supplierapi.mapper.SupplierMapper;
import org.myaccounts.supplierapi.repository.SupplierRepository;
import org.myaccounts.supplierapi.vo.SupplierVo;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {

	private final SupplierRepository supplierRepository;
	private final SupplierMapper supplierMapper;

	@Override
	public List<SupplierVo> findByNameIgnoreCase(String value) throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository.findByNameIgnoreCase(value);
		return supplierMapper.convertToSupplierVoList(list);
	}

	@Override
	public List<SupplierVo> findByCityIgnoreCase(String value) throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository.findByCityIgnoreCase(value);
		return supplierMapper.convertToSupplierVoList(list);
	}

	@Override
	public List<SupplierVo> findByStateIgnoreCase(String value) throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository.findByStateIgnoreCase(value);
		return supplierMapper.convertToSupplierVoList(list);
	}

	@Override
	public List<SupplierVo> findBymobileIgnoreCase(String value) throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository.findBymobileIgnoreCase(value);
		return supplierMapper.convertToSupplierVoList(list);
	}

	@Override
	public List<SupplierVo> findByemailIgnoreCase(String value) throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository.findByemailIgnoreCase(value);
		return supplierMapper.convertToSupplierVoList(list);
	}

	@Override
	public List<SupplierVo> findByGstnoIgnoreCase(String value) throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository.findByGstnoIgnoreCase(value);
		return supplierMapper.convertToSupplierVoList(list);
	}

	@Override
	public List<SupplierVo> findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
			String name, String city, String state, String mobile, String email, String gstno)
			throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository
				.findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(name,
						city, state, mobile, email, gstno);
		return supplierMapper.convertToSupplierVoList(list);
	}

	@Override
	public SupplierVo save(SupplierVo supplierVo) throws UnableToProcessException, ResourceAlreadyExistsException {
		SupplierEntity supplierEntity = supplierMapper.convert(supplierVo);
		supplierEntity = supplierRepository.save(supplierEntity);
		return supplierMapper.convert(supplierEntity);
	}

	@Override
	public SupplierVo update(SupplierVo supplierVo) throws UnableToProcessException, ResourceNotFoundException {
		try {
			SupplierEntity supplierEntity = supplierMapper.convert(supplierVo);
			supplierEntity = supplierRepository.save(supplierEntity);
			return supplierMapper.convert(supplierEntity);
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public SupplierVo delete(Long id) throws ResourceNotFoundException {
		try {
			Optional<SupplierEntity> optional = supplierRepository.findById(id);
			if (optional.isPresent()) {
				SupplierEntity supplierEntity = optional.get();
				supplierEntity.setDeleteflag(true);
				supplierEntity = supplierRepository.save(supplierEntity);
				return supplierMapper.convert(supplierEntity);
			} else {
				throw new ResourceNotFoundException("Supplier not exists");
			}
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SupplierVo> list() throws ResourceNotFoundException {
		List<SupplierEntity> list = supplierRepository.findAll();
		return supplierMapper.convertToSupplierVoList(list);
	}

}
