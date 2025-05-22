package org.myaccounts.userapi.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {

	@JsonIgnore
	private Long id;

	private String name;
}