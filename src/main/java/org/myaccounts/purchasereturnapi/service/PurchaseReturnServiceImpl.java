package org.myaccounts.purchasereturnapi.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.purchasereturnapi.entity.PurchaseReturnEntity;
import org.myaccounts.purchasereturnapi.mapper.PurchaseReturnMapper;
import org.myaccounts.purchasereturnapi.repository.PurchaseReturnRepository;
import org.myaccounts.purchasereturnapi.vo.PurchaseReturnVo;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PurchaseReturnServiceImpl implements PurchaseReturnService {

	private final PurchaseReturnMapper purchaseReturnMapper;
	private final PurchaseReturnRepository purchaseReturnRepository;

	@Override
	public PurchaseReturnVo save(PurchaseReturnVo purchaseReturnVo) throws UnableToProcessException, ResourceAlreadyExistsException {
		try {
			PurchaseReturnEntity purchaseReturnEntity = purchaseReturnMapper.convert(purchaseReturnVo);
			purchaseReturnEntity = purchaseReturnRepository.save(purchaseReturnEntity);
			purchaseReturnVo = purchaseReturnMapper.convert(purchaseReturnEntity);
			return purchaseReturnVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public PurchaseReturnVo update(PurchaseReturnVo purchaseReturnVo) throws UnableToProcessException, ResourceNotFoundException {
		try {
			PurchaseReturnEntity purchaseReturnEntity = purchaseReturnMapper.convert(purchaseReturnVo);
			purchaseReturnEntity = purchaseReturnRepository.save(purchaseReturnEntity);
			purchaseReturnVo = purchaseReturnMapper.convert(purchaseReturnEntity);
			return purchaseReturnVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public PurchaseReturnVo delete(Long id) throws UnableToProcessException, ResourceNotFoundException {
		try {
			PurchaseReturnVo purchaseReturnVo = null;
			Optional<PurchaseReturnEntity> optional = purchaseReturnRepository.findById(id);
			if (optional.isPresent()) {
				PurchaseReturnEntity purchaseReturnEntity = optional.get();
				purchaseReturnEntity.setDeleteflag(true);
				purchaseReturnEntity = purchaseReturnRepository.save(purchaseReturnEntity);
				purchaseReturnVo = purchaseReturnMapper.convert(purchaseReturnEntity);
			}
			return purchaseReturnVo;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<PurchaseReturnVo> findBySupplierId(Long supplierId)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<PurchaseReturnVo> list = new ArrayList<>();
			purchaseReturnRepository.findBySupplierId(supplierId).stream().forEach(entity -> {
				list.add(purchaseReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<PurchaseReturnVo> list() throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<PurchaseReturnVo> list = new ArrayList<>();
			purchaseReturnRepository.findAll().stream().forEach(entity -> {
				list.add(purchaseReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public PurchaseReturnVo find(Long purchaseId) throws UnableToProcessException, ResourceNotFoundException {
		try {
			Optional<PurchaseReturnEntity> optional = purchaseReturnRepository.findById(purchaseId);
			if (optional.isPresent())
				return purchaseReturnMapper.convert(optional.get());

			return null;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<PurchaseReturnVo> findByInvoiceNo(String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<PurchaseReturnVo> list = new ArrayList<>();
			purchaseReturnRepository.findByInvoiceNo(invoiceNo).stream().forEach(entity -> {
				list.add(purchaseReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public List<PurchaseReturnVo> findByInvoiceDate(Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			Date transactionDate = new Date(invoiceDate);
			List<PurchaseReturnVo> list = new ArrayList<>();
			purchaseReturnRepository.findByTransactionDate(transactionDate).stream().forEach(entity -> {
				list.add(purchaseReturnMapper.convert(entity));
			});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

//	@Override
//	public List<PurchaseReturnVo> findBySupplierIdOrInvoiceNoOrInvoiceDate(String supplierId, String invoiceNo, Long invoiceDate) throws UnableToProcessException, ResourceNotFoundException{
//		Date transactionDate = new Date(invoiceDate);
//		List<PurchaseReturnVo> list = new ArrayList<>();
//		purchaseRepository.findBySupplierIdOrInvoiceNoOrTransactionDate(supplierId, invoiceNo, transactionDate).stream().forEach(entity -> {
//			list.add(purchaseMapper.convert(entity));
//		});
//		return list;
//	}

	@Override
	public List<PurchaseReturnVo> findByInvoiceDateRange(Long fromDate, Long toDate)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			Date fromInvoiceDate = new Date(fromDate);
			Date toInvoiceDate = new Date(toDate);
			List<PurchaseReturnVo> list = new ArrayList<>();
			purchaseReturnRepository.findByTransactionDateBetweenOrderByTransactionDate(fromInvoiceDate, toInvoiceDate)
					.stream().forEach(entity -> {
						list.add(purchaseReturnMapper.convert(entity));
					});
			return list;
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

}