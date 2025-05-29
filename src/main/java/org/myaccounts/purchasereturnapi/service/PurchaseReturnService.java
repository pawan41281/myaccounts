package org.myaccounts.purchasereturnapi.service;

import java.util.List;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.purchasereturnapi.vo.PurchaseReturnVo;

public interface PurchaseReturnService {

	public PurchaseReturnVo save(PurchaseReturnVo productVo) throws UnableToProcessException, ResourceAlreadyExistsException;

	public PurchaseReturnVo update(PurchaseReturnVo productVo) throws UnableToProcessException, ResourceNotFoundException;

	public PurchaseReturnVo delete(Long id) throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseReturnVo> list() throws UnableToProcessException, ResourceNotFoundException;

	public PurchaseReturnVo find(Long purchaseId) throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseReturnVo> findBySupplierId(Long supplierId)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseReturnVo> findByInvoiceNo(String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseReturnVo> findByInvoiceDate(Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException;

//	public List<PurchaseReturnVo> findBySupplierIdOrInvoiceNoOrInvoiceDate(String supplierId, String invoiceNo, Long invoiceDate)throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseReturnVo> findByInvoiceDateRange(Long fromDate, Long toDate)
			throws UnableToProcessException, ResourceNotFoundException;

}
