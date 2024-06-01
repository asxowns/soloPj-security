package com.green.uniform2.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.uniform2.config.CustomUserDetails;
import com.green.uniform2.dao.Iuniformdao;
import com.green.uniform2.dto.CartDto;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private Iuniformdao dao;

	@RequestMapping("/")
	public String welcome(Model model,
			@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("member welcome.......");
		
		model.addAttribute("username", customUserDetails.getUsername());
		
		return "index";
		
	}
	
	/* 마이 페이지 */
	@RequestMapping("/myPage")
	public String mypage(HttpServletRequest request,
			@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model) {

		model.addAttribute("username", customUserDetails.getUsername());
		
		
		return "members/myPage";
	}
	
	/* 장바구니 페이지 */
	@RequestMapping("/cartPage")
	public String cartPage(@RequestParam("username") String username,
			Model model) {

		List<CartDto> cartlist = dao.cartList(username);

		model.addAttribute("cartlist", cartlist);

		return "members/cartPage";
	}
	
	
	/* 장바구니 */
	@RequestMapping("/cartAdd")
	public String cartAdd(@RequestParam("pcode") int pcode, @RequestParam("username") int username,
			@RequestParam("amount") int amount, CartDto cart, Model model) {

		int result = dao.cartAdd(cart);
		
		String msg = "장바구니에 담겼습니다.";
		String msg2 = "장바구니 담기 실패!";
		
		if(result == 1) {
			model.addAttribute("msg", msg);
		}else {
			model.addAttribute("msg2", msg2);
		}
		
		return "redirect:detail?pcode=" + pcode;
	}

	
	
	
}
