package org.myaccounts.salesreturnapi.vo;


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
public class SalesReturnVo {
	private Long id;
	private Date transactionDate;
	private Long customerId;
	private String invoiceNo;
	private boolean deleteflag;
	private String notes;
	private List<SalesReturnProductVo> products;

	@Data
	public static class SalesReturnProductVo {
		@JsonIgnore
		private Long id;
		private Long productId;
		private BigDecimal productPrice;
		private Integer quantity;
		private BigDecimal taxRate;
		@JsonIgnore
		private SalesReturnVo salesReturnVo;
		private String notes;
	}
}
