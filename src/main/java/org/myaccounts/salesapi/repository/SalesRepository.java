package org.myaccounts.salesapi.repository;

import java.sql.Date;
import java.util.List;

import org.myaccounts.salesapi.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long> {

	public List<SalesEntity> findByCustomerId(Long customerId);

	public List<SalesEntity> findByInvoiceNo(String invoiceNo);

	public List<SalesEntity> findByTransactionDate(Date transactionDate);
	
//	public List<SalesEntity> findByCustomerIdOrInvoiceNoOrTransactionDate(String customerId, String invoiceNo, Date transactionDate);
	
	public List<SalesEntity> findByTransactionDateBetweenOrderByTransactionDate(Date minTransactionDate, Date maxTransactionDate);

}
