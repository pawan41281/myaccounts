package org.myaccounts.purchasereturnapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.purchasereturnapi.service.PurchaseReturnService;
import org.myaccounts.purchasereturnapi.vo.PurchaseReturnVo;
import org.myaccounts.userapi.vo.ApiResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/purchasesreturn")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "Purchase Return Transaction Management", description = "Purchase return transaction operations")
@Slf4j
public class PurchaseReturnController {

	private final PurchaseReturnService purchaseReturnService;

	@PostMapping
	@Operation(summary = "Create Operation", description = "New purchase transaction")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseReturnVo>> save(@Valid @RequestBody PurchaseReturnVo purchaseReturnVo)
			throws UnableToProcessException, ResourceAlreadyExistsException {
		try {
			purchaseReturnVo = purchaseReturnService.save(purchaseReturnVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchase return data saved", purchaseReturnVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseReturnVo, null));
		}
	}

	@PatchMapping
	@Operation(summary = "Update Operation", description = "Update existing purchase return")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseReturnVo>> update(@Valid @RequestBody PurchaseReturnVo purchaseReturnVo)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			purchaseReturnVo = purchaseReturnService.update(purchaseReturnVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchase return data updated", purchaseReturnVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseReturnVo, null));
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Operation", description = "Delete existing purchase return")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseReturnVo>> delete(@PathVariable Long id)
			throws UnableToProcessException, ResourceNotFoundException {
		PurchaseReturnVo purchaseReturnVo = null;
		try {
			purchaseReturnVo = purchaseReturnService.delete(id);
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchase return data deleted", purchaseReturnVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseReturnVo, null));
		}
	}

	@GetMapping("/supplier/{supplierId}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases return transactions based on supplier")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseReturnVo>>> findBySupplier(@PathVariable Long supplierId)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching purchases return transactions by supplier Id: {}", supplierId);
		try {
			List<PurchaseReturnVo> list = purchaseReturnService.findBySupplierId(supplierId);
			log.info("Rcord found: {}", list.size());
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases return transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases return transactions by supplier Id: {}", supplierId, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoiceno/{invoiceNo}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases return transactions based on invoice number")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseReturnVo>>> findByInvoiceNo(@PathVariable String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching purchases return transactions by invoice number: {}", invoiceNo);
		try {
			List<PurchaseReturnVo> list = purchaseReturnService.findByInvoiceNo(invoiceNo);
			log.info("Rcord found: {}", list.size());
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases return transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases return transactions by invoice number: {}", invoiceNo, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedate/{invoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases return transactions based on invoice date")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseReturnVo>>> findByInvoiceDate(@PathVariable Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching purchases return transactions by invoice date: {}", invoiceDate);
		try {
			List<PurchaseReturnVo> list = purchaseReturnService.findByInvoiceDate(invoiceDate);
			log.info("Rcord found: {}", list.size());
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases transactions return exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases return transactions by invoice date: {}", invoiceDate, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedaterange/{fromInvoiceDate}/{toInvoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases return transactions based on invoice date range")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseReturnVo>>> findByInvoiceDateRange(@PathVariable Long fromInvoiceDate,
			@PathVariable Long toInvoiceDate) throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<PurchaseReturnVo> list = purchaseReturnService.findByInvoiceDateRange(fromInvoiceDate, toInvoiceDate);
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases return transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases return transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping
	@Operation(summary = "List Operation", description = "List all existing purchases return transactions")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseReturnVo>>> list()
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<PurchaseReturnVo> list = purchaseReturnService.list();
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases return transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases return transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

}
