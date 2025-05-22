package org.myaccounts.userapi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseVo<T> {
	private String status;
	private String message;
	private T data;
	private Object metadata;
}