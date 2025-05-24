package org.myaccounts.salesapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.salesapi.service.SalesService;
import org.myaccounts.salesapi.vo.SalesVo;
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
@RequestMapping("/v1/saless")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "Sales Transaction Management", description = "Sales transaction operations")
@Slf4j
public class SalesController {

	private final SalesService salesService;

	@PostMapping
	@Operation(summary = "Create Operation", description = "New sales transaction")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<SalesVo>> save(@Valid @RequestBody SalesVo salesVo)
			throws UnableToProcessException, ResourceAlreadyExistsException {
		try {
			salesVo = salesService.save(salesVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Sales data saved", salesVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), salesVo, null));
		}
	}

	@PatchMapping
	@Operation(summary = "Update Operation", description = "Update existing sales")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<SalesVo>> update(@Valid @RequestBody SalesVo salesVo)
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			salesVo = salesService.update(salesVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("Sales data updated", salesVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), salesVo, null));
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Operation", description = "Delete existing sales")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<SalesVo>> delete(@PathVariable Long id)
			throws UnableToProcessException, ResourceNotFoundException {
		SalesVo salesVo = null;
		try {
			salesVo = salesService.delete(id);
			return ResponseEntity.ok(ApiResponseWrapper.success("Sales data deleted", salesVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), salesVo, null));
		}
	}

	@GetMapping("/customer/{customerId}")
	@Operation(summary = "Find Operation", description = "Find all existing saless transactions based on customer")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesVo>>> findByCustomer(@PathVariable Long customerId)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching saless transactions by customer Id: {}", customerId);
		try {
			List<SalesVo> list = salesService.findByCustomerId(customerId);
			log.info("Rcord found: {}", list.size());
			String message = list.size()>0?"Saless transactions exists":"Saless transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch saless transactions by customer Id: {}", customerId, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoiceno/{invoiceNo}")
	@Operation(summary = "Find Operation", description = "Find all existing saless transactions based on invoice number")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesVo>>> findByInvoiceNo(@PathVariable String invoiceNo)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching saless transactions by invoice number: {}", invoiceNo);
		try {
			List<SalesVo> list = salesService.findByInvoiceNo(invoiceNo);
			log.info("Rcord found: {}", list.size());
			String message = list.size()>0?"Saless transactions exists":"Saless transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch saless transactions by invoice number: {}", invoiceNo, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedate/{invoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing saless transactions based on invoice date")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesVo>>> findByInvoiceDate(@PathVariable Long invoiceDate)
			throws UnableToProcessException, ResourceNotFoundException {
		log.info("Fetching saless transactions by invoice date: {}", invoiceDate);
		try {
			List<SalesVo> list = salesService.findByInvoiceDate(invoiceDate);
			log.info("Rcord found: {}", list.size());
			String message = list.size()>0?"Saless transactions exists":"Saless transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch saless transactions by invoice date: {}", invoiceDate, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping("/invoicedaterange/{fromInvoiceDate}/{toInvoiceDate}")
	@Operation(summary = "Find Operation", description = "Find all existing saless transactions based on invoice date range")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesVo>>> findByInvoiceDateRange(@PathVariable Long fromInvoiceDate,
			@PathVariable Long toInvoiceDate) throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesVo> list = salesService.findByInvoiceDateRange(fromInvoiceDate, toInvoiceDate);
			String message = list.size()>0?"Saless transactions exists":"Saless transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch saless transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

	@GetMapping
	@Operation(summary = "List Operation", description = "List all existing saless transactions")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<List<SalesVo>>> list()
			throws UnableToProcessException, ResourceNotFoundException {
		try {
			List<SalesVo> list = salesService.list();
			String message = list.size()>0?"Saless transactions exists":"Saless transactions not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			log.error("Failed to fetch saless transactions", null, e);
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}
	}

}
