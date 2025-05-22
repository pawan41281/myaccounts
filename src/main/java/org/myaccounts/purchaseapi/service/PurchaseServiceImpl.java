package org.myaccounts.purchaseapi.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.myaccounts.purchaseapi.entity.PurchaseEntity;
import org.myaccounts.purchaseapi.mapper.PurchaseMapper;
import org.myaccounts.purchaseapi.repository.PurchaseRepository;
import org.myaccounts.purchaseapi.vo.PurchaseVo;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final PurchaseMapper purchaseMapper;
	private final PurchaseRepository purchaseRepository;

	@Override
	public PurchaseVo save(PurchaseVo purchaseVo) {

		PurchaseEntity purchaseEntity = purchaseMapper.convert(purchaseVo);
		purchaseEntity = purchaseRepository.save(purchaseEntity);
		purchaseVo = purchaseMapper.convert(purchaseEntity);
		return purchaseVo;
	}

	@Override
	public PurchaseVo update(PurchaseVo purchaseVo) {
		PurchaseEntity purchaseEntity = purchaseMapper.convert(purchaseVo);
		purchaseEntity = purchaseRepository.save(purchaseEntity);
		purchaseVo = purchaseMapper.convert(purchaseEntity);
		return purchaseVo;
	}

	@Override
	public PurchaseVo delete(Long id) {
		PurchaseVo purchaseVo = null;
		Optional<PurchaseEntity> optional = purchaseRepository.findById(id);
		if (optional.isPresent()) {
			PurchaseEntity purchaseEntity = optional.get();
			purchaseEntity.setDeleteflag(true);
			purchaseEntity = purchaseRepository.save(purchaseEntity);
			purchaseVo = purchaseMapper.convert(purchaseEntity);
		}
		return purchaseVo;
	}

	@Override
	public List<PurchaseVo> findBySupplierId(Long supplierId) {
		List<PurchaseVo> list = new ArrayList<>();
		purchaseRepository.findBySupplierId(supplierId).stream().forEach(entity -> {
			list.add(purchaseMapper.convert(entity));
		});
		return list;
	}

	@Override
	public List<PurchaseVo> list() {
		List<PurchaseVo> list = new ArrayList<>();
		purchaseRepository.findAll().stream().forEach(entity -> {
			list.add(purchaseMapper.convert(entity));
		});
		return list;
	}

	@Override
	public PurchaseVo find(Long purchaseId) {
		Optional<PurchaseEntity> optional = purchaseRepository.findById(purchaseId);
		if (optional.isPresent())
			return purchaseMapper.convert(optional.get());

		return null;
	}

	@Override
	public List<PurchaseVo> findByInvoiceNo(String invoiceNo) {
		List<PurchaseVo> list = new ArrayList<>();
		purchaseRepository.findByInvoiceNo(invoiceNo).stream().forEach(entity -> {
			list.add(purchaseMapper.convert(entity));
		});
		return list;
	}

	@Override
	public List<PurchaseVo> findByInvoiceDate(Long invoiceDate) {
		Date transactionDate = new Date(invoiceDate);
		List<PurchaseVo> list = new ArrayList<>();
		purchaseRepository.findByTransactionDate(transactionDate).stream().forEach(entity -> {
			list.add(purchaseMapper.convert(entity));
		});
		return list;
	}

//	@Override
//	public List<PurchaseVo> findBySupplierIdOrInvoiceNoOrInvoiceDate(String supplierId, String invoiceNo, Long invoiceDate) {
//		Date transactionDate = new Date(invoiceDate);
//		List<PurchaseVo> list = new ArrayList<>();
//		purchaseRepository.findBySupplierIdOrInvoiceNoOrTransactionDate(supplierId, invoiceNo, transactionDate).stream().forEach(entity -> {
//			list.add(purchaseMapper.convert(entity));
//		});
//		return list;
//	}

	@Override
	public List<PurchaseVo> findByInvoiceDateRange(Long fromDate, Long toDate) {
		Date fromInvoiceDate = new Date(fromDate);
		Date toInvoiceDate = new Date(toDate);
		List<PurchaseVo> list = new ArrayList<>();
		purchaseRepository.findByTransactionDateBetweenOrderByTransactionDate(fromInvoiceDate, toInvoiceDate).stream().forEach(entity -> {
			list.add(purchaseMapper.convert(entity));
		});
		return list;
	}

}
