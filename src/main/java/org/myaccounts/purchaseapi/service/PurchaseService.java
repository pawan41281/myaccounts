package org.myaccounts.purchaseapi.service;

import java.util.List;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.purchaseapi.vo.PurchaseVo;

public interface PurchaseService {

	public PurchaseVo save(PurchaseVo productVo) throws UnableToProcessException, ResourceAlreadyExistsException;

	public PurchaseVo update(PurchaseVo productVo) throws UnableToProcessException, ResourceNotFoundException;

	public PurchaseVo delete(Long id) throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseVo> list() throws UnableToProcessException, ResourceNotFoundException;

	public PurchaseVo find(Long purchaseId) throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseVo> findBySupplierId(Long supplierId)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseVo> findByInvoiceNo(String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseVo> findByInvoiceDate(Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException;

//	public List<PurchaseVo> findBySupplierIdOrInvoiceNoOrInvoiceDate(String supplierId, String invoiceNo, Long invoiceDate)throws UnableToProcessException, ResourceNotFoundException;

	public List<PurchaseVo> findByInvoiceDateRange(Long fromDate, Long toDate)
			throws UnableToProcessException, ResourceNotFoundException;

}
