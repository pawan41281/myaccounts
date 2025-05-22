package org.myaccounts.purchaseapi.service;

import java.util.List;

import org.myaccounts.purchaseapi.vo.PurchaseVo;

public interface PurchaseService {

	public PurchaseVo save(PurchaseVo productVo);

	public PurchaseVo update(PurchaseVo productVo);

	public PurchaseVo delete(Long id);

	public List<PurchaseVo> list();

	public PurchaseVo find(Long purchaseId);

	public List<PurchaseVo> findBySupplierId(Long supplierId);

	public List<PurchaseVo> findByInvoiceNo(String invoiceNo);

	public List<PurchaseVo> findByInvoiceDate(Long invoiceDate);
	
//	public List<PurchaseVo> findBySupplierIdOrInvoiceNoOrInvoiceDate(String supplierId, String invoiceNo, Long invoiceDate);
	
	public List<PurchaseVo> findByInvoiceDateRange(Long fromDate, Long toDate);

}
