package com.green.uniform2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	private int oid;
	private String username;
	private int pcode;
	private int amount;
	
}
