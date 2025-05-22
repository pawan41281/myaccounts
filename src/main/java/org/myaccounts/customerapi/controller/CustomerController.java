package org.myaccounts.customerapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.customerapi.service.CustomerService;
import org.myaccounts.customerapi.vo.CustomerVo;
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
@RequestMapping("/v1/customers")
@AllArgsConstructor
@Tag(name = "Customer Management", description = "Customer operations")
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create Operation", description = "Create new customer")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<CustomerVo>> save(@RequestBody CustomerVo customerVo)
            throws UnableToProcessException, ResourceAlreadyExistsException {
        log.info("Creating customer: {}", customerVo);
        try {
            customerVo = customerService.save(customerVo);
            log.info("Customer created with ID: {}", customerVo.getId());
            return ResponseEntity.ok(ApiResponseWrapper.success("Customer created successfully", customerVo, null));
        } catch (Exception e) {
            log.error("Customer creation failed for input: {}", customerVo, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), customerVo, null));
        }
    }

    @PatchMapping
    @Operation(summary = "Update Operation", description = "Update existing customer")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<CustomerVo>> update(@Valid @RequestBody CustomerVo customerVo)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Updating customer: {}", customerVo);
        try {
            customerVo = customerService.update(customerVo);
            log.info("Customer updated with ID: {}", customerVo.getId());
            return ResponseEntity.ok(ApiResponseWrapper.success("Customer updated successfully", customerVo, null));
        } catch (Exception e) {
            log.error("Customer update failed for input: {}", customerVo, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), customerVo, null));
        }
    }

    @GetMapping("/name/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing customers by name")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findByNameIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching customers by name: {}", value);
        try {
            List<CustomerVo> list = customerService.findByNameIgnoreCase(value);
            return successWithMetadata("Fetched by name", list);
        } catch (Exception e) {
            log.error("Failed to fetch customers by name: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/city/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing customers by city")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findByCityIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching customers by city: {}", value);
        try {
            List<CustomerVo> list = customerService.findByCityIgnoreCase(value);
            return successWithMetadata("Fetched by city", list);
        } catch (Exception e) {
            log.error("Failed to fetch customers by city: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/state/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing customers by state")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findByStateIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching customers by state: {}", value);
        try {
            List<CustomerVo> list = customerService.findByStateIgnoreCase(value);
            return successWithMetadata("Fetched by state", list);
        } catch (Exception e) {
            log.error("Failed to fetch customers by state: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/mobile/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing customers by mobile")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findByMobileIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching customers by mobile: {}", value);
        try {
            List<CustomerVo> list = customerService.findBymobileIgnoreCase(value);
            return successWithMetadata("Fetched by mobile", list);
        } catch (Exception e) {
            log.error("Failed to fetch customers by mobile: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/email/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing customers by email")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findByEmailIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching customers by email: {}", value);
        try {
            List<CustomerVo> list = customerService.findByemailIgnoreCase(value);
            return successWithMetadata("Fetched by email", list);
        } catch (Exception e) {
            log.error("Failed to fetch customers by email: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/gstno/{value}")
    @Operation(summary = "Find Operation", description = "Find all existing customers by GST number")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findByGstnoIgnoreCase(@PathVariable String value)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching customers by GST number: {}", value);
        try {
            List<CustomerVo> list = customerService.findByGstnoIgnoreCase(value);
            return successWithMetadata("Fetched by GST number", list);
        } catch (Exception e) {
            log.error("Failed to fetch customers by GST number: {}", value, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping("/")
    @Operation(summary = "Find Operation", description = "Find all existing customers with optional filters")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String gstno)
            throws UnableToProcessException, ResourceNotFoundException {

        log.info("Fetching customers with filters - name: {}, city: {}, state: {}, mobile: {}, email: {}, gstno: {}",
                name, city, state, mobile, email, gstno);
        try {
            List<CustomerVo> list = customerService
                    .findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
                            name, city, state, mobile, email, gstno);
            return successWithMetadata("Fetched with filters", list);
        } catch (Exception e) {
            log.error("Failed to fetch customers with filters", e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @GetMapping
    @Operation(summary = "List Operation", description = "List all existing customers")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<List<CustomerVo>>> findAll()
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Fetching all customers");
        try {
            List<CustomerVo> list = customerService.list();
            return successWithMetadata("Fetched all customers", list);
        } catch (Exception e) {
            log.error("Failed to fetch all customers", e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete Operation", description = "Delete existing customer")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseVo<CustomerVo>> delete(@PathVariable Long id)
            throws UnableToProcessException, ResourceNotFoundException {
        log.info("Soft deleting customer with ID: {}", id);
        CustomerVo customerVo = null;
        try {
            customerVo = customerService.delete(id);
            log.info("Customer soft deleted: {}", customerVo);
            return ResponseEntity.ok(ApiResponseWrapper.success("Customer deleted", customerVo, null));
        } catch (Exception e) {
            log.error("Failed to delete customer with ID: {}", id, e);
            return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), customerVo, null));
        }
    }

    // Utility method for metadata
    private ResponseEntity<ApiResponseVo<List<CustomerVo>>> successWithMetadata(String message, List<CustomerVo> list) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("recordcount", String.valueOf(list.size()));
        log.info("{} - Records fetched: {}", message, list.size());
        return ResponseEntity.ok(ApiResponseWrapper.success(null, list, metadata));
    }
}
