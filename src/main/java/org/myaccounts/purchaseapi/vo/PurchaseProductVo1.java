package org.myaccounts.purchaseapi.vo;


import java.math.BigDecimal;

import org.myaccounts.purchaseapi.entity.PurchaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseProductVo1 {
	private Long id;
	private Long productId;
	private BigDecimal productPrice;
	private Integer quantity;
	private BigDecimal taxRate;
	private PurchaseEntity purchaseEntity;


}
