package org.myaccounts.salesapi.entity;

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
@Table(name = "salestransaction")
public class SalesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date transactionDate;
	private Long customerId;
	private String invoiceNo;
	private boolean deleteflag;
	private String notes;
	@OneToMany(mappedBy = "salesEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SalesProductEntity> products = new ArrayList<>();
}
