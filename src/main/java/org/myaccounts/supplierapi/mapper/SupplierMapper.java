package org.myaccounts.supplierapi.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.myaccounts.supplierapi.entity.SupplierEntity;
import org.myaccounts.supplierapi.vo.SupplierVo;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SupplierMapper {

	private final ModelMapper modelMapper;

	public SupplierVo convert(SupplierEntity supplierEntity) {
		SupplierVo supplierVo = modelMapper.map(supplierEntity, SupplierVo.class);
		return supplierVo;
	}

	public SupplierEntity convert(SupplierVo supplierVo) {
		SupplierEntity supplierEntity = modelMapper.map(supplierVo, SupplierEntity.class);
		return supplierEntity;
	}

	public List<SupplierVo> convertToSupplierVoList(List<SupplierEntity> supplierEntityList) {
		List<SupplierVo> supplierVoList = new ArrayList<>();
		supplierEntityList.stream().forEach(entity -> {
			SupplierVo supplierVo = convert(entity);
			supplierVoList.add(supplierVo);
		});
		return supplierVoList;
	}

	public List<SupplierEntity> convertToSupplierEntityList(List<SupplierVo> supplierVoList) {
		List<SupplierEntity> supplierEntityList = new ArrayList<>();
		supplierVoList.stream().forEach(vo -> {
			SupplierEntity supplierEntity = convert(vo);
			supplierEntityList.add(supplierEntity);
		});
		return supplierEntityList;
	}

}
