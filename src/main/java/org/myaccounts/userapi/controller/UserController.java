package org.myaccounts.userapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.userapi.service.UserService;
import org.myaccounts.userapi.vo.ApiResponseVo;
import org.myaccounts.userapi.vo.UserVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User Management", description = "User operations")
public class UserController {

	private final UserService userService;

	// Create New User
	@PostMapping
	@Operation(summary = "Create User Operation", description = "Create new user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponseVo<UserVo>> save(@Valid @RequestBody UserVo userVo)
			throws UnableToProcessException, ResourceAlreadyExistsException {

		try {
			userVo = userService.save(userVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("User created successfully", userVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), userVo, null));
		}
	}

	// Update Existing User
	@PatchMapping
	@Operation(summary = "Update Operation", description = "Update existing user")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ApiResponseVo<UserVo>> update(@Valid @RequestBody UserVo userVo)
			throws ResourceNotFoundException {

		try {
			userVo = userService.update(userVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("User updated successfully", userVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), userVo, null));
		}

	}

	// Find Existing User
	@GetMapping("/username/")
	@Operation(summary = "Find Operation", description = "Find existing user by username")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponseVo<UserVo>> findByUsername(@RequestParam String username)
			throws ResourceNotFoundException {

		try {
			UserVo userVo = userService.findByUsername(username);
			String message = userVo!=null?"User exist":"User not exists";
			return ResponseEntity.ok(ApiResponseWrapper.success(message, userVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}

	}

	// Find Existing User
	@GetMapping("/email/")
	@Operation(summary = "Find Operation", description = "Find existing user by email")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponseVo<UserVo>> findByEmail(@RequestParam String email)
			throws ResourceNotFoundException {

		try {
			UserVo userVo = userService.findByEmail(email);
			String message = userVo!=null?"User exist":"User not exists";
			return ResponseEntity.ok(ApiResponseWrapper.success(message, userVo, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), null, null));
		}

	}

	// List of Existing Users
//	@GetMapping
//	@Operation(summary = "List Operation", description = "Find all existing usesr")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<ApiResponseVo<List<UserVo>>> list() throws UnableToProcessException {
//
//		List<UserVo> list = null;
//		try {
//			list = userService.findAll();
//			String message = list!=null&&list.size()>0?"Users exist":"Users not exists";
//			Map<String, String> metadata = new HashMap<>();
//			metadata.put("recordcount", String.valueOf(list.size()));
//			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
//		} catch (Exception e) {
//			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), list, null));
//		}
//	}

	// Find Existing Users based on multiple parameters
	@GetMapping
	@Operation(summary = "Find Operation", description = "Find all existing usesr based on multiple parameters")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponseVo<List<UserVo>>> find(
			@RequestParam(required = false) String username, 
			@RequestParam(required = false) String email, 
			@RequestParam(required = false) String mobile) throws UnableToProcessException {

		List<UserVo> list = null;
		try {
			list = userService.findByUsernameorEmailorMobile(username, email, mobile);
			String message = list!=null&&list.size()>0?"Users exist":"Users not exists";
			Map<String, String> metadata = new HashMap<>();
			metadata.put("recordcount", String.valueOf(list.size()));
			return ResponseEntity.ok(ApiResponseWrapper.success(message, list, metadata));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure(e.getMessage(), list, null));
		}

	}

}