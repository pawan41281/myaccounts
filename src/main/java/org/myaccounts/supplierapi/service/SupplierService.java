package org.myaccounts.supplierapi.service;

import java.util.List;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.supplierapi.vo.SupplierVo;

public interface SupplierService {

	public List<SupplierVo> findByNameIgnoreCase(String value) throws ResourceNotFoundException;

	public List<SupplierVo> findByCityIgnoreCase(String value) throws ResourceNotFoundException;

	public List<SupplierVo> findByStateIgnoreCase(String value) throws ResourceNotFoundException;

	public List<SupplierVo> findBymobileIgnoreCase(String value) throws ResourceNotFoundException;

	public List<SupplierVo> findByemailIgnoreCase(String value) throws ResourceNotFoundException;

	public List<SupplierVo> findByGstnoIgnoreCase(String value) throws ResourceNotFoundException;

	public List<SupplierVo> findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
			String name, String city, String state, String mobile, String email, String gstno) throws ResourceNotFoundException;

	public SupplierVo save(SupplierVo supplierVo) throws UnableToProcessException, ResourceAlreadyExistsException;

	public SupplierVo update(SupplierVo supplierVo) throws UnableToProcessException, ResourceNotFoundException;

	public SupplierVo delete(Long id) throws ResourceNotFoundException;

	public List<SupplierVo> list() throws ResourceNotFoundException;

}
