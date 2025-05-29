package org.myaccounts.purchasereturnapi.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.myaccounts.purchasereturnapi.entity.PurchaseReturnEntity;
import org.myaccounts.purchasereturnapi.entity.PurchaseReturnProductEntity;
import org.myaccounts.purchasereturnapi.vo.PurchaseReturnVo;
import org.myaccounts.purchasereturnapi.vo.PurchaseReturnVo.PurchaseReturnProductVo;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PurchaseReturnMapper {

	private final ModelMapper modelMapper;

	public PurchaseReturnVo convert(PurchaseReturnEntity entity) {
		PurchaseReturnVo vo = modelMapper.map(entity, PurchaseReturnVo.class);
		List<PurchaseReturnProductVo> list = //new ArrayList<>();
		entity.getProducts().stream().map(pp -> {
			PurchaseReturnProductVo purchaseReturnProductVo = new PurchaseReturnProductVo();
			purchaseReturnProductVo.setId(pp.getId());
			purchaseReturnProductVo.setProductId(pp.getProductId());
			purchaseReturnProductVo.setProductPrice(pp.getProductPrice());
			purchaseReturnProductVo.setQuantity(pp.getQuantity());
			purchaseReturnProductVo.setTaxRate(pp.getTaxRate());
			purchaseReturnProductVo.setNotes(pp.getNotes());
			return purchaseReturnProductVo;
		}).collect(Collectors.toList());
		vo.setProducts(list);
		return vo;
	}

	public PurchaseReturnEntity convert(PurchaseReturnVo vo) {
		PurchaseReturnEntity entity = modelMapper.map(vo, PurchaseReturnEntity.class);
		List<PurchaseReturnProductEntity> list = new ArrayList<>();
		vo.getProducts().stream().forEach(purchaseProductVo -> {
			PurchaseReturnProductEntity productEntity = new PurchaseReturnProductEntity();
			productEntity.setId(purchaseProductVo.getId());
			productEntity.setProductId(purchaseProductVo.getProductId());
			productEntity.setProductPrice(purchaseProductVo.getProductPrice());
			productEntity.setQuantity(purchaseProductVo.getQuantity());
			productEntity.setTaxRate(purchaseProductVo.getTaxRate());
			productEntity.setNotes(purchaseProductVo.getNotes());
			productEntity.setPurchaseReturnEntity(entity);
			list.add(productEntity);
		});
		entity.setProducts(list);
		return entity;
	}

	public List<PurchaseReturnVo> convertToPurchaseReturnVoList(List<PurchaseReturnEntity> entityList) {
		List<PurchaseReturnVo> voList = new ArrayList<>();
		entityList.stream().forEach(entity -> {
			PurchaseReturnVo vo = convert(entity);
			voList.add(vo);
		});
		return voList;
	}

	public List<PurchaseReturnEntity> convertToPurchaseEntityList(List<PurchaseReturnVo> voList) {
		List<PurchaseReturnEntity> entityList = new ArrayList<>();
		voList.stream().forEach(vo -> {
			PurchaseReturnEntity entity = convert(vo);
			entityList.add(entity);
		});
		return entityList;
	}

}
