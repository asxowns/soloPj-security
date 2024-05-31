package com.green.uniform2.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.uniform2.config.CustomUserDetails;

@Controller
@RequestMapping("/members")
public class MemberController {

	@RequestMapping("/")
	public String welcome(Model model,
			Principal principal,
			@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("member welcome.......");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		model.addAttribute("username", userDetails.getUsername());
		
		return "members/welcome";
		
	}
	
	
}
