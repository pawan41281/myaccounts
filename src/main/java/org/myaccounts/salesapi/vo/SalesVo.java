package org.myaccounts.salesapi.vo;

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
public class SalesVo {
	private Long id;
	private Date transactionDate;
	private Long customerId;
	private String invoiceNo;
	private boolean deleteflag;
	private String notes;
	private List<SalesProductVo> products;

	@Data
	public static class SalesProductVo {
		@JsonIgnore
		private Long id;
		private Long productId;
		private BigDecimal productPrice;
		private Integer quantity;
		private BigDecimal taxRate;
		@JsonIgnore
		private SalesVo salesVo;
		private String notes;
	}
}
