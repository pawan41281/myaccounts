package org.myaccounts.salesreturnapi.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.salesreturnapi.service.SalesReturnService;
import org.myaccounts.salesreturnapi.vo.SalesReturnVo;
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
@RequestMapping("/v1/salesreturns")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "SalesReturn Transaction Management", description = "Sales return transaction operations")
@Slf4j
public class SalesReturnController {

	private final SalesReturnService salesReturnService;

	@PostMapping
	@Operation(summary = "Create Operation", description = "New Salesreturn transaction")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<SalesReturnVo>> save(@Valid @RequestBody SalesReturnVo salesReturnVo)
			throws UnableToProcessException, ResourceAlreadyExistsException {
		try {
			salesReturnVo = salesReturnService.save(salesReturnVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Salesreturn data saved", salesReturnVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), salesReturnVo, null));
		}
	}

	@PatchMapping
	@Operation(summary = "Update Operation", description = "Update existing Salesreturn")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<SalesReturnVo>> update(@Valid @RequestBody SalesReturnVo salesReturnVo)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			salesReturnVo = salesReturnService.update(salesReturnVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Salesreturn data updated", salesReturnVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), salesReturnVo, null));
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Operation", description = "Delete existing Salesreturn")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<SalesReturnVo>> delete(@PathVariable Long id)
			throws UnableToProcessException, ResourceNotFoundException {
		SalesReturnVo salesReturnVo = null;
		try {
			salesReturnVo = salesReturnService.delete(id);
			return ResponseEntity.ok(ApiResponseWrapper.success("Salesreturn data deleted", salesReturnVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), salesReturnVo, null));
		}
	}

	@GetMapping("/customer/{customerId}")
	@Operation(summary = "Find Operation", description = "Find all existing Salesreturn transactions based on customer")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesReturnVo>>> findByCustomer(@PathVariable Long customerId)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching Salesreturn transactions by customer Id: {}", customerId);
		try {
			List<SalesReturnVo> list = salesReturnService.findByCustomerId(customerId);
			log.info("Rcord found: {}", list.size());
			String message = list.size()>0?"Salesreturn transactions exists":"Salesreturn transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch Salesreturn transactions by customer Id: {}", customerId, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoiceno/{invoiceNo}")
	@Operation(summary = "Find Operation", description = "Find all existing Salesreturn transactions based on invoice number")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesReturnVo>>> findByInvoiceNo(@PathVariable String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching Salesreturn transactions by invoice number: {}", invoiceNo);
		try {
			List<SalesReturnVo> list = salesReturnService.findByInvoiceNo(invoiceNo);
			log.info("Rcord found: {}", list.size());
			String message = list.size()>0?"Salesreturn transactions exists":"Salesreturn transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch Salesreturn transactions by invoice number: {}", invoiceNo, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedate/{invoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing sales returns transactions based on invoice date")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesReturnVo>>> findByInvoiceDate(@PathVariable Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching Salesreturn transactions by invoice date: {}", invoiceDate);
		try {
			List<SalesReturnVo> list = salesReturnService.findByInvoiceDate(invoiceDate);
			log.info("Rcord found: {}", list.size());
			String message = list.size()>0?"Salesreturn transactions exists":"Salesreturn transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch Salesreturn transactions by invoice date: {}", invoiceDate, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedaterange/{fromInvoiceDate}/{toInvoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing Salesreturn transactions based on invoice date range")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesReturnVo>>> findByInvoiceDateRange(@PathVariable Long fromInvoiceDate,
			@PathVariable Long toInvoiceDate) throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesReturnVo> list = salesReturnService.findByInvoiceDateRange(fromInvoiceDate, toInvoiceDate);
			String message = list.size()>0?"Salesreturn transactions exists":"Salesreturn transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch Salesreturn transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping
	@Operation(summary = "List Operation", description = "List all existing Salesreturn transactions")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesReturnVo>>> list()
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesReturnVo> list = salesReturnService.list();
			String message = list.size()>0?"Salesreturn transactions exists":"Salesreturn transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch Salesreturn transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

}
