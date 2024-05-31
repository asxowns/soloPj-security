package com.green.uniform2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.uniform2.config.CustomUserDetails;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/")
	public String welcome(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		model.addAttribute("username", userDetails.getUsername());
		
		return"admin/welcome";
	}
	
}
