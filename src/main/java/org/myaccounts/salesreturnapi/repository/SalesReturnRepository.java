package org.myaccounts.salesreturnapi.repository;


import java.sql.Date;
import java.util.List;

import org.myaccounts.salesreturnapi.entity.SalesReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReturnRepository extends JpaRepository<SalesReturnEntity, Long> {

	public List<SalesReturnEntity> findByCustomerId(Long customerId);

	public List<SalesReturnEntity> findByInvoiceNo(String invoiceNo);

	public List<SalesReturnEntity> findByTransactionDate(Date transactionDate);
	
//	public List<SalesReturnEntity> findByCustomerIdOrInvoiceNoOrTransactionDate(String customerId, String invoiceNo, Date transactionDate);
	
	public List<SalesReturnEntity> findByTransactionDateBetweenOrderByTransactionDate(Date minTransactionDate, Date maxTransactionDate);

}
