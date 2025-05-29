package org.myaccounts.salesreturnapi.entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "salesreturntransaction")
public class SalesReturnEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date transactionDate;
	private Long customerId;
	private String invoiceNo;
	private boolean deleteflag;
	private String notes;
	@OneToMany(mappedBy = "salesReturnEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SalesReturnProductEntity> products = new ArrayList<>();
}
