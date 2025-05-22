package org.myaccounts.userapi.vo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

	@JsonIgnore
	private Long id;

	private String name;
	private String username;
	private String email;
	private String mobile;
	private String password;

	private boolean locked;

	private Set<RoleVo> roles;

	public UserVo(String name, String username, String email, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public UserVo(String name, String username, String email, String mobile, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
	}

	public UserVo(String name, String username, String email, String password, boolean locked) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.locked = locked;
	}

	public UserVo(String name, String username, String email, String mobile, String password, boolean locked) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.locked = locked;
	}

}