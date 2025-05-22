package org.myaccounts.purchaseapi.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchaseproducttransaction")
public class PurchaseProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;
	private BigDecimal productPrice;
	private Integer quantity;
	private BigDecimal taxRate;
	private String notes;

	@ManyToOne
	@JoinColumn(name = "trans_ref")
	private PurchaseEntity purchaseEntity;
}
