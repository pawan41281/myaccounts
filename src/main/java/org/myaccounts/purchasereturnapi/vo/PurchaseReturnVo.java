package org.myaccounts.purchasereturnapi.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReturnVo {
	private Long id;
	private Date transactionDate;
	private Long supplierId;
	private String invoiceNo;
	private boolean deleteflag;
	private String notes;
	private List<PurchaseReturnProductVo> products;

	@Data
	public static class PurchaseReturnProductVo {
		private Long id;
		private Long productId;
		private BigDecimal productPrice;
		private Integer quantity;
		private BigDecimal taxRate;
		@JsonIgnore
		private PurchaseReturnVo purchaseReturnVo;
		private String notes;
	}
}
