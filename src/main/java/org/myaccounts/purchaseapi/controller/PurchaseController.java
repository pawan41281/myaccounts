package org.myaccounts.purchaseapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@RestController
@RequestMapping("/v1/purchases")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "Purchase Transaction Management", description = "Purchase transaction operations")
public class PurchaseController {

	private final PurchaseService purchaseService;

//	@GetMapping("/supplierorinvoicenoorinvoicedate")
//	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions")
//	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> find(
//			@RequestParam(required = false) Long supplierId, 
//			@RequestParam(required = false) String invoiceNo, 
//			@RequestParam(required = false) Long invoiceDate) {
//		List<PurchaseVo> list = purchaseService.findBySupplierIdOrInvoiceNoOrInvoiceDate(invoiceNo, invoiceNo, invoiceDate);
//		Map<String, String> metadata = new HashMap<>();
//		metadata.put("recordcount", String.valueOf(list.size()));
//		return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
//	}
	
	@GetMapping("/supplier/{supplierId}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on supplier")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findBySupplier(
			@PathVariable Long supplierId) {
		List<PurchaseVo> list = purchaseService.findBySupplierId(supplierId);
		Map<String, String> metadata = new HashMap<>();
		metadata.put("recordcount", String.valueOf(list.size()));
		return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
	}
	
	@GetMapping("/invoiceno/{invoiceNo}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on invoice number")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findByInvoiceNo(
			@PathVariable String invoiceNo) {
		List<PurchaseVo> list = purchaseService.findByInvoiceNo(invoiceNo);
		Map<String, String> metadata = new HashMap<>();
		metadata.put("recordcount", String.valueOf(list.size()));
		return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
	}
	
	@GetMapping("/invoicedate/{invoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on invoice date")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findByInvoiceDate(
			@PathVariable Long invoiceDate) {

		List<PurchaseVo> list = purchaseService.findByInvoiceDate(invoiceDate);
		Map<String, String> metadata = new HashMap<>();
		metadata.put("recordcount", String.valueOf(list.size()));
		return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
	}
	
	@GetMapping("/invoicedaterange/{fromInvoiceDate}/{toInvoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing purchases transactions based on invoice date range")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> findByInvoiceDateRange(
			@PathVariable Long fromInvoiceDate,
			@PathVariable Long toInvoiceDate) {

		List<PurchaseVo> list = purchaseService.findByInvoiceDateRange(fromInvoiceDate, toInvoiceDate);
		Map<String, String> metadata = new HashMap<>();
		metadata.put("recordcount", String.valueOf(list.size()));
		return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
	}
	
	@GetMapping
	@Operation(summary = "List Operation", description = "List all existing purchases transactions")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<PurchaseVo>>> list() {

		List<PurchaseVo> list = purchaseService.list();
		Map<String, String> metadata = new HashMap<>();
		metadata.put("recordcount", String.valueOf(list.size()));
		return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
	}

	@PostMapping
	@Operation(summary = "Create Operation", description = "New purchase transaction")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseVo>> save(@Valid @RequestBody PurchaseVo purchaseVo) {

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
	public ResponseEntity<ApiResponseVo<PurchaseVo>> update(@Valid @RequestBody PurchaseVo purchaseVo) {

		try {
			purchaseVo = purchaseService.update(purchaseVo);
			return ResponseEntity.ok(ApiResponseWrapper.success(null, purchaseVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseVo, null));
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Operation", description = "Delete existing purchase")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<PurchaseVo>> delete(@PathVariable Long id) {

		PurchaseVo purchaseVo = null;
		try {
			purchaseVo = purchaseService.delete(id);
			return ResponseEntity.ok(ApiResponseWrapper.success(null, purchaseVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), purchaseVo, null));
		}
	}

}
