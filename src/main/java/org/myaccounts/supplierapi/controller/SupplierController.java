package org.myaccounts.supplierapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.supplierapi.service.SupplierService;
import org.myaccounts.supplierapi.vo.SupplierVo;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/suppliers")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Supplier Management", description = "Supplier operations")
@Slf4j
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    @Operation(summary = "Create Operation", description = "Create new supplier")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<SupplierVo>> save(@RequestBody SupplierVo supplierVo)
            throws UnableToProcessException, ResourceAlreadyExistsException {
        log.info("Creating supplier: {}", supplierVo);
        try {
            supplierVo = supplierService.save(supplierVo);
            log.info("Supplier created with ID: {}", supplierVo.getId());
            return ResponseEntity.ok(ApiResponseWrapper.success("Supplier created successfully", supplierVo, null));
        } catch (Exception e) {
            log.error("Supplier creation failed for input: {}", supplierVo, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), supplierVo, null));
        }
    }

    @PatchMapping
    @Operation(summary = "Update Operation", description = "Update existing supplier")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<SupplierVo>> update(@Valid @RequestBody SupplierVo supplierVo)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Updating supplier: {}", supplierVo);
        try {
            supplierVo = supplierService.update(supplierVo);
            log.info("Supplier updated with ID: {}", supplierVo.getId());
            return ResponseEntity.ok(ApiResponseWrapper.success("Supplier updated successfully", supplierVo, null));
        } catch (Exception e) {
            log.error("Supplier update failed for input: {}", supplierVo, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), supplierVo, null));
        }
    }

    @GetMapping("/name/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing suppliers by name")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findByNameIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching suppliers by name: {}", value);
        try {
            List<SupplierVo> list = supplierService.findByNameIgnoreCase(value);
            return successWithMetadata("Fetched by name", list);
        } catch (Exception e) {
            log.error("Failed to fetch suppliers by name: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/city/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing suppliers by city")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findByCityIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching suppliers by city: {}", value);
        try {
            List<SupplierVo> list = supplierService.findByCityIgnoreCase(value);
            return successWithMetadata("Fetched by city", list);
        } catch (Exception e) {
            log.error("Failed to fetch suppliers by city: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/state/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing suppliers by state")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findByStateIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching suppliers by state: {}", value);
        try {
            List<SupplierVo> list = supplierService.findByStateIgnoreCase(value);
            return successWithMetadata("Fetched by state", list);
        } catch (Exception e) {
            log.error("Failed to fetch suppliers by state: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/mobile/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing suppliers by mobile")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findByMobileIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching suppliers by mobile: {}", value);
        try {
            List<SupplierVo> list = supplierService.findBymobileIgnoreCase(value);
            return successWithMetadata("Fetched by mobile", list);
        } catch (Exception e) {
            log.error("Failed to fetch suppliers by mobile: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/email/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing suppliers by email")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findByEmailIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching suppliers by email: {}", value);
        try {
            List<SupplierVo> list = supplierService.findByemailIgnoreCase(value);
            return successWithMetadata("Fetched by email", list);
        } catch (Exception e) {
            log.error("Failed to fetch suppliers by email: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/gstno/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing suppliers by GST number")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findByGstnoIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching suppliers by GST number: {}", value);
        try {
            List<SupplierVo> list = supplierService.findByGstnoIgnoreCase(value);
            return successWithMetadata("Fetched by GST number", list);
        } catch (Exception e) {
            log.error("Failed to fetch suppliers by GST number: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/")
    @Operation(summary = "Find Operation", description = "Find all existing suppliers with optional filters")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String gstno)
            throws UnableToProcessException, ResourceNotFoundException {

        log.info("Fetching suppliers with filters - name: {}, city: {}, state: {}, mobile: {}, email: {}, gstno: {}",
                name, city, state, mobile, email, gstno);
        try {
            List<SupplierVo> list = supplierService
                    .findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
                            name, city, state, mobile, email, gstno);
            return successWithMetadata("Fetched with filters", list);
        } catch (Exception e) {
            log.error("Failed to fetch suppliers with filters", e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping
    @Operation(summary = "List Operation", description = "List all existing suppliers")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<SupplierVo>>> findAll()
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching all suppliers");
        try {
            List<SupplierVo> list = supplierService.list();
            return successWithMetadata("Fetched all suppliers", list);
        } catch (Exception e) {
            log.error("Failed to fetch all suppliers", e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete Operation", description = "Delete existing supplier")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<SupplierVo>> delete(@PathVariable Long id)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Soft deleting supplier with ID: {}", id);
        SupplierVo supplierVo = null;
        try {
            supplierVo = supplierService.delete(id);
            log.info("Supplier soft deleted: {}", supplierVo);
            return ResponseEntity.ok(ApiResponseWrapper.success("Supplier deleted", supplierVo, null));
        } catch (Exception e) {
            log.error("Failed to delete supplier with ID: {}", id, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), supplierVo, null));
        }
    }

    // Utility method for metadata
    private ResponseEntity<ApiResponseVo<List<SupplierVo>>> successWithMetadata(String message, List<SupplierVo> list) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("recordcount", String.valueOf(list.size()));
        log.info("{} - Records fetched: {}", message, list.size());
        return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
    }
}
