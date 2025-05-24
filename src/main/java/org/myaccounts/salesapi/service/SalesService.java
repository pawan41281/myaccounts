package org.myaccounts.salesapi.service;

import java.util.List;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.salesapi.vo.SalesVo;

public interface SalesService {

	public SalesVo save(SalesVo productVo) throws UnableToProcessException, ResourceAlreadyExistsException;

	public SalesVo update(SalesVo productVo) throws UnableToProcessException, ResourceNotFoundException;

	public SalesVo delete(Long id) throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesVo> list() throws UnableToProcessException, ResourceNotFoundException;

	public SalesVo find(Long salesId) throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesVo> findByCustomerId(Long customerId)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesVo> findByInvoiceNo(String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesVo> findByInvoiceDate(Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException;

//	public List<SalesVo> findByCustomerIdOrInvoiceNoOrInvoiceDate(String customerId, String invoiceNo, Long invoiceDate)throws UnableToProcessException, ResourceNotFoundException;

	public List<SalesVo> findByInvoiceDateRange(Long fromDate, Long toDate)
			throws UnableToProcessException, ResourceNotFoundException;

}
