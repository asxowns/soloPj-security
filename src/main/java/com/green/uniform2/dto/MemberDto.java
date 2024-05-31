package com.green.uniform2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

	private String username;
	private String name;
	private String password;
	private String phone;
	private String addr;
	private String role;
	
}
