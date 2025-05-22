package org.myaccounts.purchaseapi.repository;

import java.sql.Date;
import java.util.List;

import org.myaccounts.purchaseapi.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

	public List<PurchaseEntity> findBySupplierId(Long supplierId);

	public List<PurchaseEntity> findByInvoiceNo(String invoiceNo);

	public List<PurchaseEntity> findByTransactionDate(Date transactionDate);
	
//	public List<PurchaseEntity> findBySupplierIdOrInvoiceNoOrTransactionDate(String supplierId, String invoiceNo, Date transactionDate);
	
	public List<PurchaseEntity> findByTransactionDateBetweenOrderByTransactionDate(Date minTransactionDate, Date maxTransactionDate);

}
