package org.myaccounts.customerapi.service;

import java.util.List;
import java.util.Optional;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.customerapi.entity.CustomerEntity;
import org.myaccounts.customerapi.mapper.CustomerMapper;
import org.myaccounts.customerapi.repository.CustomerRepository;
import org.myaccounts.customerapi.vo.CustomerVo;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	@Override
	public List<CustomerVo> findByNameIgnoreCase(String value) throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository.findByNameIgnoreCase(value);
		return customerMapper.convertToCustomerVoList(list);
	}

	@Override
	public List<CustomerVo> findByCityIgnoreCase(String value) throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository.findByCityIgnoreCase(value);
		return customerMapper.convertToCustomerVoList(list);
	}

	@Override
	public List<CustomerVo> findByStateIgnoreCase(String value) throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository.findByStateIgnoreCase(value);
		return customerMapper.convertToCustomerVoList(list);
	}

	@Override
	public List<CustomerVo> findBymobileIgnoreCase(String value) throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository.findBymobileIgnoreCase(value);
		return customerMapper.convertToCustomerVoList(list);
	}

	@Override
	public List<CustomerVo> findByemailIgnoreCase(String value) throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository.findByemailIgnoreCase(value);
		return customerMapper.convertToCustomerVoList(list);
	}

	@Override
	public List<CustomerVo> findByGstnoIgnoreCase(String value) throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository.findByGstnoIgnoreCase(value);
		return customerMapper.convertToCustomerVoList(list);
	}

	@Override
	public List<CustomerVo> findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
			String name, String city, String state, String mobile, String email, String gstno)
			throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository
				.findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(name,
						city, state, mobile, email, gstno);
		return customerMapper.convertToCustomerVoList(list);
	}

	@Override
	public CustomerVo save(CustomerVo customerVo) throws UnableToProcessException, ResourceAlreadyExistsException {
		CustomerEntity customerEntity = customerMapper.convert(customerVo);
		customerEntity = customerRepository.save(customerEntity);
		return customerMapper.convert(customerEntity);
	}

	@Override
	public CustomerVo update(CustomerVo customerVo) throws UnableToProcessException, ResourceNotFoundException {
		try {
			CustomerEntity customerEntity = customerMapper.convert(customerVo);
			customerEntity = customerRepository.save(customerEntity);
			return customerMapper.convert(customerEntity);
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public CustomerVo delete(Long id) throws ResourceNotFoundException {
		try {
			Optional<CustomerEntity> optional = customerRepository.findById(id);
			if (optional.isPresent()) {
				CustomerEntity customerEntity = optional.get();
				customerEntity.setDeleteflag(true);
				customerEntity = customerRepository.save(customerEntity);
				return customerMapper.convert(customerEntity);
			} else {
				throw new ResourceNotFoundException("Customer not exists");
			}
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<CustomerVo> list() throws ResourceNotFoundException {
		List<CustomerEntity> list = customerRepository.findAll();
		return customerMapper.convertToCustomerVoList(list);
	}

}
