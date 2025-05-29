package org.myaccounts.salesreturnapi.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.salesreturnapi.entity.SalesReturnEntity;
import org.myaccounts.salesreturnapi.mapper.SalesReturnMapper;
import org.myaccounts.salesreturnapi.repository.SalesReturnRepository;
import org.myaccounts.salesreturnapi.vo.SalesReturnVo;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SalesReturnServiceImpl implements SalesReturnService {

	private final SalesReturnMapper salesReturnMapper;
	private final SalesReturnRepository salesReturnRepository;

	@Override
	public SalesReturnVo save(SalesReturnVo salesReturnVo) throws UnableToProcessException, ResourceAlreadyExistsException {
		try {
			SalesReturnEntity salesReturnEntity = salesReturnMapper.convert(salesReturnVo);
			salesReturnEntity = salesReturnRepository.save(salesReturnEntity);
			salesReturnVo = salesReturnMapper.convert(salesReturnEntity);
			return salesReturnVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public SalesReturnVo update(SalesReturnVo salesReturnVo) throws UnableToProcessException, ResourceNotFoundException {
		try {
			SalesReturnEntity salesReturnEntity = salesReturnMapper.convert(salesReturnVo);
			salesReturnEntity = salesReturnRepository.save(salesReturnEntity);
			salesReturnVo = salesReturnMapper.convert(salesReturnEntity);
			return salesReturnVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public SalesReturnVo delete(Long id) throws UnableToProcessException, ResourceNotFoundException {
		try {
			SalesReturnVo salesReturnVo = null;
			Optional<SalesReturnEntity> optional = salesReturnRepository.findById(id);
			if (optional.isPresent()) {
				SalesReturnEntity salesReturnEntity = optional.get();
				salesReturnEntity.setDeleteflag(true);
				salesReturnEntity = salesReturnRepository.save(salesReturnEntity);
				salesReturnVo = salesReturnMapper.convert(salesReturnEntity);
			}
			return salesReturnVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesReturnVo> findByCustomerId(Long customerId)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesReturnVo> list = new ArrayList<>();
			salesReturnRepository.findByCustomerId(customerId).stream().forEach(entity -> {
				list.add(salesReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesReturnVo> list() throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesReturnVo> list = new ArrayList<>();
			salesReturnRepository.findAll().stream().forEach(entity -> {
				list.add(salesReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public SalesReturnVo find(Long salesReturnId) throws UnableToProcessException, ResourceNotFoundException {
		try {
			Optional<SalesReturnEntity> optional = salesReturnRepository.findById(salesReturnId);
			if (optional.isPresent())
				return salesReturnMapper.convert(optional.get());

			return null;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesReturnVo> findByInvoiceNo(String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesReturnVo> list = new ArrayList<>();
			salesReturnRepository.findByInvoiceNo(invoiceNo).stream().forEach(entity -> {
				list.add(salesReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<SalesReturnVo> findByInvoiceDate(Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			Date transactionDate = new Date(invoiceDate);
			List<SalesReturnVo> list = new ArrayList<>();
			salesReturnRepository.findByTransactionDate(transactionDate).stream().forEach(entity -> {
				list.add(salesReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

//	@Override
//	public List<SalesReturnVo> findByCustomerIdOrInvoiceNoOrInvoiceDate(String customerId, String invoiceNo, Long invoiceDate) throws UnableToProcessException, ResourceNotFoundException{
//		Date transactionDate = new Date(invoiceDate);
//		List<SalesReturnVo> list = new ArrayList<>();
//		salesReturnRepository.findByCustomerIdOrInvoiceNoOrTransactionDate(customerId, invoiceNo, transactionDate).stream().forEach(entity -> {
//			list.add(salesReturnMapper.convert(entity));
//		});
//		return list;
//	}

	@Override
	public List<SalesReturnVo> findByInvoiceDateRange(Long fromDate, Long toDate)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			Date fromInvoiceDate = new Date(fromDate);
			Date toInvoiceDate = new Date(toDate);
			List<SalesReturnVo> list = new ArrayList<>();
			salesReturnRepository.findByTransactionDateBetweenOrderByTransactionDate(fromInvoiceDate, toInvoiceDate)
					.stream().forEach(entity -> {
						list.add(salesReturnMapper.convert(entity));
					});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

}