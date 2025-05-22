package org.myaccounts.customerapi.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.myaccounts.customerapi.entity.CustomerEntity;
import org.myaccounts.customerapi.vo.CustomerVo;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomerMapper {

	private final ModelMapper modelMapper;

	public CustomerVo convert(CustomerEntity customerEntity) {
		CustomerVo customerVo = modelMapper.map(customerEntity, CustomerVo.class);
		return customerVo;
	}

	public CustomerEntity convert(CustomerVo customerVo) {
		CustomerEntity customerEntity = modelMapper.map(customerVo, CustomerEntity.class);
		return customerEntity;
	}

	public List<CustomerVo> convertToCustomerVoList(List<CustomerEntity> customerEntityList) {
		List<CustomerVo> customerVoList = new ArrayList<>();
		customerEntityList.stream().forEach(entity -> {
			CustomerVo customerVo = convert(entity);
			customerVoList.add(customerVo);
		});
		return customerVoList;
	}

	public List<CustomerEntity> convertToCustomerEntityList(List<CustomerVo> customerVoList) {
		List<CustomerEntity> customerEntityList = new ArrayList<>();
		customerVoList.stream().forEach(vo -> {
			CustomerEntity customerEntity = convert(vo);
			customerEntityList.add(customerEntity);
		});
		return customerEntityList;
	}

}
