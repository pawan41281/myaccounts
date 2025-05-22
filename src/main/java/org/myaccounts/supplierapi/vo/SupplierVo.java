package org.myaccounts.supplierapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierVo {

	private Long id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String mobile;
	private String email;
	private String gstno;
	private boolean deleteflag;
	
}
