package org.myaccounts.common.wrapper;

import org.myaccounts.userapi.vo.ApiResponseVo;

public class ApiResponseWrapper {
	public static <T> ApiResponseVo<T> success(String message, T data, Object metadata) {
		return new ApiResponseVo<>("success", message, data, metadata);
	}
	
	public static <T> ApiResponseVo<T> failure(String message, T data, Object metadata) {
		return new ApiResponseVo<>("failure", message, data, metadata);
	}

	public static <T> ApiResponseVo<T> error(String message, T data) {
		return new ApiResponseVo<>("error", message, data, null);
	}

}
