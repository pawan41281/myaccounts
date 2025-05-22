package org.myaccounts.customerapi.service;

import java.util.List;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.customerapi.vo.CustomerVo;

public interface CustomerService {

	public List<CustomerVo> findByNameIgnoreCase(String value) throws ResourceNotFoundException;

	public List<CustomerVo> findByCityIgnoreCase(String value) throws ResourceNotFoundException;

	public List<CustomerVo> findByStateIgnoreCase(String value) throws ResourceNotFoundException;

	public List<CustomerVo> findBymobileIgnoreCase(String value) throws ResourceNotFoundException;

	public List<CustomerVo> findByemailIgnoreCase(String value) throws ResourceNotFoundException;

	public List<CustomerVo> findByGstnoIgnoreCase(String value) throws ResourceNotFoundException;

	public List<CustomerVo> findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
			String name, String city, String state, String mobile, String email, String gstno) throws ResourceNotFoundException;

	public CustomerVo save(CustomerVo customerVo) throws UnableToProcessException, ResourceAlreadyExistsException;

	public CustomerVo update(CustomerVo customerVo) throws UnableToProcessException, ResourceNotFoundException;

	public CustomerVo delete(Long id) throws ResourceNotFoundException;

	public List<CustomerVo> list() throws ResourceNotFoundException;

}
