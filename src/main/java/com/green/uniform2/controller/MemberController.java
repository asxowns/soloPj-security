package com.green.uniform2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.uniform2.config.CustomUserDetails;
import com.green.uniform2.dao.Iuniformdao;
import com.green.uniform2.dto.CartDto;
import com.green.uniform2.dto.MemberDto;
import com.green.uniform2.dto.OrderDto;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private Iuniformdao dao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/")
	public String welcome(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("member welcome.......");

		model.addAttribute("username", customUserDetails.getUsername());

		return "index";

	}
	
	/* 내정보 수정폼 */
	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("username") String username,
			Model model) {
		
		model.addAttribute("member", dao.findByUsername(username));
		
		return "./registform";
	}
	
	/* 내정보 수정 */
	@RequestMapping("/update")
	public String update(MemberDto memberDto,
			Model model) {
		
		String newPw = bCryptPasswordEncoder.encode(memberDto.getPassword());
		memberDto.setPassword(newPw);
		
		dao.update(memberDto);
		
		return "members/myPage";
	}

	/* 마이 페이지 */
	@RequestMapping("/myPage")
	public String mypage(HttpServletRequest request,
			@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model,
			@RequestParam("username") String username) {

		model.addAttribute("username", customUserDetails.getUsername());
		
		List<OrderDto> list = dao.orderList(username);

		model.addAttribute("list", list);
		

		return "members/myPage";
	}

	/* 장바구니 페이지 */
	@RequestMapping("/cartPage")
	public String cartPage(@RequestParam("username") String username, Model model) {

		List<CartDto> cartlist = dao.cartList(username);

		model.addAttribute("cartlist", cartlist);
		model.addAttribute("username", username);

		return "members/cartPage";
	}

	/* 장바구니 */
	@RequestMapping("/cartAdd")
	public String cartAdd(@RequestParam("pcode") int pcode,
			@RequestParam("username") String username,
			CartDto cart, Model model) {

		Integer getUsername = dao.getUsername(username);

		if (getUsername == 0 || getUsername == null) {
			return "../loginform";
		} else {
			int result = dao.cartAdd(cart);
			System.out.println("result : " + result);

			model.addAttribute("result", result);
			return "redirect:../detail?pcode=" + pcode;
		}

	}

	/* 장바구니 제품삭제 */
	@RequestMapping("/deleteCart")
	public String deleteCart(@RequestParam("username") String username, @RequestParam("pcode") int pcode) {

		dao.deleteCart(username, pcode);

		return "redirect:cartPage?username=" + username;
	}

	/* 주문 목록 */
	@RequestMapping("/orderList")
	public String orderList(@RequestParam("username") String username, Model model) {

		List<OrderDto> list = dao.orderList(username);

		model.addAttribute("list", list);

		return "members/orderList";
	}
}
