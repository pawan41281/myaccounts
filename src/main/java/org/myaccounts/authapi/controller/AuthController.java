package org.myaccounts.authapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.myaccounts.authapi.service.AuthService;
import org.myaccounts.authapi.vo.LoginVo;
import org.myaccounts.authapi.vo.SignupRequestVo;
import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.security.JwtAuthResponse;
import org.myaccounts.common.wrapper.ApiResponseWrapper;
import org.myaccounts.userapi.vo.ApiResponseVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth Management", description = "Authority operations")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	@Operation(summary = "Login Operation", description = "Generate the access token")
	public ResponseEntity<ApiResponseVo<?>> login(@RequestBody LoginVo loginVo) throws ResourceNotFoundException {
		try {
			String token = authService.login(loginVo);
			JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
			jwtAuthResponse.setAccessToken(token);
			return ResponseEntity.ok(ApiResponseWrapper.success(null, jwtAuthResponse, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ApiResponseWrapper.failure(e.getMessage(), loginVo, null));
		}
	}

	@PostMapping("/signup")
	@Operation(summary = "Signup Operation", description = "Create new user")
	public ResponseEntity<ApiResponseVo<SignupRequestVo>> registerUser(
			@Valid @RequestBody SignupRequestVo signUpRequestVo)
			throws UnableToProcessException, ResourceAlreadyExistsException {

		try {
			authService.save(signUpRequestVo);
			return ResponseEntity.ok(ApiResponseWrapper.success("User created successfully", signUpRequestVo, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ApiResponseWrapper.failure(e.getMessage(), signUpRequestVo, null));
		}
	}

	@PostMapping("/validate")
	@Operation(summary = "Validate Operation", description = "Validate the access token")
	public ResponseEntity<ApiResponseVo<String>> validate(@RequestHeader("Authorization") String token) {
		System.out.println("token::"+token);
		try {
			boolean isvalidtoken = authService.validateToken(token.replace("Bearer ", ""));
			if (isvalidtoken)
				return ResponseEntity.ok(ApiResponseWrapper.success("Valid token", token, null));
			else
				return ResponseEntity.ok(ApiResponseWrapper.failure("Invalid token", token, null));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponseWrapper.failure("Invalid token", token, null));
		}
	}

}