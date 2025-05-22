package org.myaccounts.purchaseapi.mapper;

import org.myaccounts.purchaseapi.entity.PurchaseProductEntity;
import org.myaccounts.purchaseapi.vo.PurchaseVo.PurchaseProductVo;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
public class PurchaseProductMapper {

	//private final ModelMapper modelMapper;

	public PurchaseProductVo convert(PurchaseProductEntity purchaseProductEntity) {
		PurchaseProductVo purchaseProductVo = new PurchaseProductVo();
		purchaseProductVo.setId(purchaseProductEntity.getId());
		purchaseProductVo.setProductId(purchaseProductEntity.getProductId());
		purchaseProductVo.setProductPrice(purchaseProductEntity.getProductPrice());
		purchaseProductVo.setQuantity(purchaseProductEntity.getQuantity());
		purchaseProductVo.setTaxRate(purchaseProductEntity.getTaxRate());
		return purchaseProductVo;
	}

	public PurchaseProductEntity convert(PurchaseProductVo purchaseProductVo) {
		PurchaseProductEntity productEntity = new PurchaseProductEntity();
		productEntity.setId(purchaseProductVo.getId());
		productEntity.setProductId(purchaseProductVo.getProductId());
		productEntity.setProductPrice(purchaseProductVo.getProductPrice());
		productEntity.setQuantity(purchaseProductVo.getQuantity());
		productEntity.setTaxRate(purchaseProductVo.getTaxRate());
		//productEntity.setPurchaseEntity(entity);
		return productEntity;
	}

}
