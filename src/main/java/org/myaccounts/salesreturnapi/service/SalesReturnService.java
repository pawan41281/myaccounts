package org.myaccounts.salesreturnapi.service;


import java.util.List;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.salesreturnapi.vo.SalesReturnVo;

public interface SalesReturnService {

	public SalesReturnVo save(SalesReturnVo productVo) throws UnableToProcessException, ResourceAlreadyExistsException;

	public SalesReturnVo update(SalesReturnVo productVo) throws UnableToProcessException, ResourceNotFoundException;

	public SalesReturnVo delete(Long id) throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesReturnVo> list() throws UnableToProcessException, ResourceNotFoundException;

	public SalesReturnVo find(Long salesReturnId) throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesReturnVo> findByCustomerId(Long customerId)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesReturnVo> findByInvoiceNo(String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesReturnVo> findByInvoiceDate(Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException;

//	public List<SalesReturnVo> findByCustomerIdOrInvoiceNoOrInvoiceDate(String customerId, String invoiceNo, Long invoiceDate)throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesReturnVo> findByInvoiceDateRange(Long fromDate, Long toDate)
			throws UnableToProcessException, ResourceNotFoundException;

}
