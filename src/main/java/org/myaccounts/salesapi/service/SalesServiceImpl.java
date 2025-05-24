package org.myaccounts.salesapi.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.salesapi.entity.SalesEntity;
import org.myaccounts.salesapi.mapper.SalesMapper;
import org.myaccounts.salesapi.repository.SalesRepository;
import org.myaccounts.salesapi.vo.SalesVo;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SalesServiceImpl implements SalesService {

	private final SalesMapper salesMapper;
	private final SalesRepository salesRepository;

	@Override
	public SalesVo save(SalesVo salesVo) throws UnableToProcessException, ResourceAlreadyExistsException {
		try {
			SalesEntity salesEntity = salesMapper.convert(salesVo);
			salesEntity = salesRepository.save(salesEntity);
			salesVo = salesMapper.convert(salesEntity);
			return salesVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public SalesVo update(SalesVo salesVo) throws UnableToProcessException, ResourceNotFoundException {
		try {
			SalesEntity salesEntity = salesMapper.convert(salesVo);
			salesEntity = salesRepository.save(salesEntity);
			salesVo = salesMapper.convert(salesEntity);
			return salesVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public SalesVo delete(Long id) throws UnableToProcessException, ResourceNotFoundException {
		try {
			SalesVo salesVo = null;
			Optional<SalesEntity> optional = salesRepository.findById(id);
			if (optional.isPresent()) {
				SalesEntity salesEntity = optional.get();
				salesEntity.setDeleteflag(true);
				salesEntity = salesRepository.save(salesEntity);
				salesVo = salesMapper.convert(salesEntity);
			}
			return salesVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesVo> findByCustomerId(Long customerId)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesVo> list = new ArrayList<>();
			salesRepository.findByCustomerId(customerId).stream().forEach(entity -> {
				list.add(salesMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesVo> list() throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesVo> list = new ArrayList<>();
			salesRepository.findAll().stream().forEach(entity -> {
				list.add(salesMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public SalesVo find(Long salesId) throws UnableToProcessException, ResourceNotFoundException {
		try {
			Optional<SalesEntity> optional = salesRepository.findById(salesId);
			if (optional.isPresent())
				return salesMapper.convert(optional.get());

			return null;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesVo> findByInvoiceNo(String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesVo> list = new ArrayList<>();
			salesRepository.findByInvoiceNo(invoiceNo).stream().forEach(entity -> {
				list.add(salesMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesVo> findByInvoiceDate(Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			Date transactionDate = new Date(invoiceDate);
			List<SalesVo> list = new ArrayList<>();
			salesRepository.findByTransactionDate(transactionDate).stream().forEach(entity -> {
				list.add(salesMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

//	@Override
//	public List<SalesVo> findByCustomerIdOrInvoiceNoOrInvoiceDate(String customerId, String invoiceNo, Long invoiceDate) throws UnableToProcessException, ResourceNotFoundException{
//		Date transactionDate = new Date(invoiceDate);
//		List<SalesVo> list = new ArrayList<>();
//		salesRepository.findByCustomerIdOrInvoiceNoOrTransactionDate(customerId, invoiceNo, transactionDate).stream().forEach(entity -> {
//			list.add(salesMapper.convert(entity));
//		});
//		return list;
//	}

	@Override
	public List<SalesVo> findByInvoiceDateRange(Long fromDate, Long toDate)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			Date fromInvoiceDate = new Date(fromDate);
			Date toInvoiceDate = new Date(toDate);
			List<SalesVo> list = new ArrayList<>();
			salesRepository.findByTransactionDateBetweenOrderByTransactionDate(fromInvoiceDate, toInvoiceDate)
					.stream().forEach(entity -> {
						list.add(salesMapper.convert(entity));
					});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

}