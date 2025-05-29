package org.myaccounts.salesreturnapi.mapper;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.myaccounts.salesreturnapi.entity.SalesReturnEntity;
import org.myaccounts.salesreturnapi.entity.SalesReturnProductEntity;
import org.myaccounts.salesreturnapi.vo.SalesReturnVo;
import org.myaccounts.salesreturnapi.vo.SalesReturnVo.SalesReturnProductVo;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SalesReturnMapper {

	private final ModelMapper modelMapper;

	public SalesReturnVo convert(SalesReturnEntity entity) {
		SalesReturnVo vo = modelMapper.map(entity, SalesReturnVo.class);
		List<SalesReturnProductVo> list = //new ArrayList<>();
		entity.getProducts().stream().map(salesReturnProductEntity -> {
			SalesReturnProductVo salesReturnProductVo = new SalesReturnProductVo();
			salesReturnProductVo.setId(salesReturnProductEntity.getId());
			salesReturnProductVo.setProductId(salesReturnProductEntity.getProductId());
			salesReturnProductVo.setProductPrice(salesReturnProductEntity.getProductPrice());
			salesReturnProductVo.setQuantity(salesReturnProductEntity.getQuantity());
			salesReturnProductVo.setTaxRate(salesReturnProductEntity.getTaxRate());
			salesReturnProductVo.setNotes(salesReturnProductEntity.getNotes());
			return salesReturnProductVo;
		}).collect(Collectors.toList());
		vo.setProducts(list);
		return vo;
	}

	public SalesReturnEntity convert(SalesReturnVo vo) {
		SalesReturnEntity entity = modelMapper.map(vo, SalesReturnEntity.class);
		List<SalesReturnProductEntity> list = new ArrayList<>();
		vo.getProducts().stream().forEach(salesReturnProductVo -> {
			SalesReturnProductEntity productEntity = new SalesReturnProductEntity();
			productEntity.setId(salesReturnProductVo.getId());
			productEntity.setProductId(salesReturnProductVo.getProductId());
			productEntity.setProductPrice(salesReturnProductVo.getProductPrice());
			productEntity.setQuantity(salesReturnProductVo.getQuantity());
			productEntity.setTaxRate(salesReturnProductVo.getTaxRate());
			productEntity.setNotes(salesReturnProductVo.getNotes());
			productEntity.setSalesReturnEntity(entity);
			list.add(productEntity);
		});
		entity.setProducts(list);
		return entity;
	}

	public List<SalesReturnVo> convertToSalesReturnVoList(List<SalesReturnEntity> entityList) {
		List<SalesReturnVo> voList = new ArrayList<>();
		entityList.stream().forEach(entity -> {
			SalesReturnVo vo = convert(entity);
			voList.add(vo);
		});
		return voList;
	}

	public List<SalesReturnEntity> convertToSalesReturnEntityList(List<SalesReturnVo> voList) {
		List<SalesReturnEntity> entityList = new ArrayList<>();
		voList.stream().forEach(vo -> {
			SalesReturnEntity entity = convert(vo);
			entityList.add(entity);
		});
		return entityList;
	}

}
