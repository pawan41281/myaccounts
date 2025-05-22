package org.myaccounts.purchaseapi.vo;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.myaccounts.purchaseapi.entity.PurchaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseVo {
	private Long id;
	private Date transactionDate;
	private Long supplierId;
	private String invoiceNo;
	private boolean deleteflag;
	private String notes;
	private List<PurchaseProductVo> products;

	@Data
	public static class PurchaseProductVo {
		private Long id;
		private Long productId;
		private BigDecimal productPrice;
		private Integer quantity;
		private BigDecimal taxRate;
		private PurchaseEntity purchaseEntity;
		private String notes;
	}
}
