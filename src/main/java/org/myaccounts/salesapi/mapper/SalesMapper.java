package org.myaccounts.salesapi.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.myaccounts.salesapi.entity.SalesEntity;
import org.myaccounts.salesapi.entity.SalesProductEntity;
import org.myaccounts.salesapi.vo.SalesVo;
import org.myaccounts.salesapi.vo.SalesVo.SalesProductVo;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SalesMapper {

	private final ModelMapper modelMapper;

	public SalesVo convert(SalesEntity entity) {
		SalesVo vo = modelMapper.map(entity, SalesVo.class);
		List<SalesProductVo> list = //new ArrayList<>();
		entity.getProducts().stream().map(salesProductEntity -> {
			SalesProductVo salesProductVo = new SalesProductVo();
			salesProductVo.setId(salesProductEntity.getId());
			salesProductVo.setProductId(salesProductEntity.getProductId());
			salesProductVo.setProductPrice(salesProductEntity.getProductPrice());
			salesProductVo.setQuantity(salesProductEntity.getQuantity());
			salesProductVo.setTaxRate(salesProductEntity.getTaxRate());
			salesProductVo.setNotes(salesProductEntity.getNotes());
			return salesProductVo;
		}).collect(Collectors.toList());
		vo.setProducts(list);
		return vo;
	}

	public SalesEntity convert(SalesVo vo) {
		SalesEntity entity = modelMapper.map(vo, SalesEntity.class);
		List<SalesProductEntity> list = new ArrayList<>();
		vo.getProducts().stream().forEach(salesProductVo -> {
			SalesProductEntity productEntity = new SalesProductEntity();
			productEntity.setId(salesProductVo.getId());
			productEntity.setProductId(salesProductVo.getProductId());
			productEntity.setProductPrice(salesProductVo.getProductPrice());
			productEntity.setQuantity(salesProductVo.getQuantity());
			productEntity.setTaxRate(salesProductVo.getTaxRate());
			productEntity.setNotes(salesProductVo.getNotes());
			productEntity.setSalesEntity(entity);
			list.add(productEntity);
		});
		entity.setProducts(list);
		return entity;
	}

	public List<SalesVo> convertToSalesVoList(List<SalesEntity> entityList) {
		List<SalesVo> voList = new ArrayList<>();
		entityList.stream().forEach(entity -> {
			SalesVo vo = convert(entity);
			voList.add(vo);
		});
		return voList;
	}

	public List<SalesEntity> convertToSalesEntityList(List<SalesVo> voList) {
		List<SalesEntity> entityList = new ArrayList<>();
		voList.stream().forEach(vo -> {
			SalesEntity entity = convert(vo);
			entityList.add(entity);
		});
		return entityList;
	}

}
