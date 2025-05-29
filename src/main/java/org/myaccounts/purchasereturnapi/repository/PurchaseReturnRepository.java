package org.myaccounts.purchasereturnapi.repository;

import java.sql.Date;
import java.util.List;

import org.myaccounts.purchasereturnapi.entity.PurchaseReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseReturnRepository extends JpaRepository<PurchaseReturnEntity, Long> {

	public List<PurchaseReturnEntity> findBySupplierId(Long supplierId);

	public List<PurchaseReturnEntity> findByInvoiceNo(String invoiceNo);

	public List<PurchaseReturnEntity> findByTransactionDate(Date transactionDate);
	
//	public List<PurchaseReturnEntity> findBySupplierIdOrInvoiceNoOrTransactionDate(String supplierId, String invoiceNo, Date transactionDate);
	
	public List<PurchaseReturnEntity> findByTransactionDateBetweenOrderByTransactionDate(Date minTransactionDate, Date maxTransactionDate);

}
