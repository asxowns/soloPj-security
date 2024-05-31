package com.green.uniform2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class reviewDto {
	
	private int rid;
	private int pcode;
	private String username;
	private String content;

}
