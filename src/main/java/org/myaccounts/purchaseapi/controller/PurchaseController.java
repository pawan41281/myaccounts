package org.myaccounts.purchaseapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.purchaseapi.service.PurchaseService;
import org.myaccounts.purchaseapi.vo.PurchaseVo;
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
@RequestMapping("/v1/purchases")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "Purchase Transaction Management", description = "Purchase transaction operations")
@Slf4j
public class PurchaseController {

	private final PurchaseService purchaseService;

	@PostMapping
	@Operation(summary = "Create Operation", description = "New purchase transaction")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseVo>> save(@Valid @RequestBody PurchaseVo purchaseVo)
			throws UnableToProcessException, ResourceAlreadyExistsException {
		try {
			purchaseVo = purchaseService.save(purchaseVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchase data saved", purchaseVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseVo, null));
		}
	}

	@PatchMapping
	@Operation(summary = "Update Operation", description = "Update existing purchase")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseVo>> update(@Valid @RequestBody PurchaseVo purchaseVo)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			purchaseVo = purchaseService.update(purchaseVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchase data updated", purchaseVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseVo, null));
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Operation", description = "Delete existing purchase")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseVo>> delete(@PathVariable Long id)
			throws UnableToProcessException, ResourceNotFoundException {
		PurchaseVo purchaseVo = null;
		try {
			purchaseVo = purchaseService.delete(id);
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchase data deleted", purchaseVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseVo, null));
		}
	}

	@GetMapping("/supplier/{supplierId}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on supplier")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findBySupplier(@PathVariable Long supplierId)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching purchases transactions by supplier Id: {}", supplierId);
		try {
			List<PurchaseVo> list = purchaseService.findBySupplierId(supplierId);
			log.info("Rcord found: {}", list.size());
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases transactions by supplier Id: {}", supplierId, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoiceno/{invoiceNo}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on invoice number")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findByInvoiceNo(@PathVariable String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching purchases transactions by invoice number: {}", invoiceNo);
		try {
			List<PurchaseVo> list = purchaseService.findByInvoiceNo(invoiceNo);
			log.info("Rcord found: {}", list.size());
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases transactions by invoice number: {}", invoiceNo, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedate/{invoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on invoice date")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findByInvoiceDate(@PathVariable Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching purchases transactions by invoice date: {}", invoiceDate);
		try {
			List<PurchaseVo> list = purchaseService.findByInvoiceDate(invoiceDate);
			log.info("Rcord found: {}", list.size());
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases transactions by invoice date: {}", invoiceDate, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedaterange/{fromInvoiceDate}/{toInvoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on invoice date range")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findByInvoiceDateRange(@PathVariable Long fromInvoiceDate,
			@PathVariable Long toInvoiceDate) throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<PurchaseVo> list = purchaseService.findByInvoiceDateRange(fromInvoiceDate, toInvoiceDate);
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping
	@Operation(summary = "List Operation", description = "List all existing purchases transactions")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> list()
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<PurchaseVo> list = purchaseService.list();
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success("Purchases transactions exists as per requested criteria", list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch purchases transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

}
