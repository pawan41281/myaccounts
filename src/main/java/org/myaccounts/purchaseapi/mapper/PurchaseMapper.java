package org.myaccounts.purchaseapi.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.myaccounts.purchaseapi.entity.PurchaseEntity;
import org.myaccounts.purchaseapi.entity.PurchaseProductEntity;
import org.myaccounts.purchaseapi.vo.PurchaseVo;
import org.myaccounts.purchaseapi.vo.PurchaseVo.PurchaseProductVo;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PurchaseMapper {

	private final ModelMapper modelMapper;
	private final PurchaseProductMapper purchaseProductMapper;

	public PurchaseVo convert(PurchaseEntity entity) {
		PurchaseVo vo = modelMapper.map(entity, PurchaseVo.class);
//		List<PurchaseProductVo> list = //new ArrayList<>();
//		entity.getProducts().stream().map(purchaseProductEntity -> {
//			PurchaseProductVo purchaseProductVo = new PurchaseProductVo();
//			purchaseProductVo.setId(purchaseProductEntity.getId());
//			purchaseProductVo.setProductId(purchaseProductEntity.getProductId());
//			purchaseProductVo.setProductPrice(purchaseProductEntity.getProductPrice());
//			purchaseProductVo.setQuantity(purchaseProductEntity.getQuantity());
//			purchaseProductVo.setTaxRate(purchaseProductEntity.getTaxRate());
//			return purchaseProductVo;
//		}).collect(Collectors.toList());
		
		List<PurchaseProductVo> list = new ArrayList<>();
		entity.getProducts().stream().forEach(purchaseProductEntity -> {
			PurchaseProductVo purchaseProductVo = purchaseProductMapper.convert(purchaseProductEntity);
			list.add(purchaseProductVo);
		});	
				
		vo.setProducts(list);
		return vo;
	}

	public PurchaseEntity convert(PurchaseVo vo) {
		PurchaseEntity purchaseEntity = modelMapper.map(vo, PurchaseEntity.class);
		List<PurchaseProductEntity> list = new ArrayList<>();
		vo.getProducts().stream().forEach(purchaseProductVo -> {
//			PurchaseProductEntity productEntity = new PurchaseProductEntity();
//			productEntity.setId(purchaseProductVo.getId());
//			productEntity.setProductId(purchaseProductVo.getProductId());
//			productEntity.setProductPrice(purchaseProductVo.getProductPrice());
//			productEntity.setQuantity(purchaseProductVo.getQuantity());
//			productEntity.setTaxRate(purchaseProductVo.getTaxRate());
//			productEntity.setPurchaseEntity(entity);
//			list.add(productEntity);
			
			PurchaseProductEntity purchaseProductEntity = purchaseProductMapper.convert(purchaseProductVo);
			purchaseProductEntity.setPurchaseEntity(purchaseEntity);
			list.add(purchaseProductEntity);
			
		});
		purchaseEntity.setProducts(list);
		return purchaseEntity;
	}

	public List<PurchaseVo> convertToPurchaseVoList(List<PurchaseEntity> entityList) {
		List<PurchaseVo> voList = new ArrayList<>();
		entityList.stream().forEach(entity -> {
			PurchaseVo vo = convert(entity);
			voList.add(vo);
		});
		return voList;
	}

	public List<PurchaseEntity> convertToPurchaseEntityList(List<PurchaseVo> voList) {
		List<PurchaseEntity> entityList = new ArrayList<>();
		voList.stream().forEach(vo -> {
			PurchaseEntity entity = convert(vo);
			entityList.add(entity);
		});
		return entityList;
	}

}
