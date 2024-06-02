package com.green.uniform2.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.uniform2.config.CustomUserDetails;
import com.green.uniform2.dao.Iuniformdao;
import com.green.uniform2.dto.CartDto;
import com.green.uniform2.dto.MemberDto;
import com.green.uniform2.dto.OrderDto;
import com.green.uniform2.dto.ProductDto;

@Controller
public class MyController {

	@Autowired
	private Iuniformdao dao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/")
	public String root(Model model) {

		List<ProductDto> list = dao.productList();

		model.addAttribute("list", list);
		
		return "index";
	}
	

	@RequestMapping("/registform")
	public String registform() {

		
		return "registform";
	}

	@RequestMapping("/regist")
	public String regist(MemberDto member) {

		member.setRole("ROLE_MEMBER");
		
		String newPw = bCryptPasswordEncoder.encode(member.getPassword());
		member.setPassword(newPw);
		
		dao.regist(member);

		return "loginform";
	}
	
	

	@RequestMapping("/loginform")
	public String loginform() {

		return "loginform";
	}

//	@RequestMapping("/login")
//	public String login(HttpServletRequest request, @RequestParam("username") String username,
//			@RequestParam("userpw") String userpw, Model model) throws Exception {
//
//		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();
//
//		MemberDto member = new MemberDto();
//
//		member.setUsername(username);
//		member.setUserpw(userpw);
//
//		MemberDto loginMember = dao.login(member);
//
//		if (loginMember != null) {
//			session.setAttribute("username", member.getUsername());
//			session.setAttribute("role", loginMember.getRole());
//			session.setAttribute("mid", loginMember.getMid());
//			return "redirect:/";
//		} else {
//			model.addAttribute("error", "다시 로그인 해주세요.");
//			return "loginform";
//
//		}
//
//	}

	/* 제품 상세페이지 */
	@RequestMapping("/detail")
	public String detail(Model model, @RequestParam("pcode") int pcode,
			@AuthenticationPrincipal CustomUserDetails customUserDetails) {

		
		if(customUserDetails == null) {
			model.addAttribute("prod", dao.productGetPcode(pcode));
		}else {
			model.addAttribute("prod", dao.productGetPcode(pcode));
			model.addAttribute("username", customUserDetails.getUsername());
			model.addAttribute("name", customUserDetails.getName());
			model.addAttribute("role", customUserDetails.getRole());
		}

		return "detail";
	}

	
	/* 주문하기 페이지 */
	@RequestMapping("/orderPage")
	public String orderPage(@RequestParam("username") String username,
			Model model) {
		
		List<CartDto> orderlist = dao.orderPageList(username);
		
		model.addAttribute("orderlist", orderlist);
		
		return "orderPage";
	}
	
	
	@RequestMapping("/order")
	public String order(OrderDto orderDto,
			Model model) {
		
		boolean tf = dao.orderNow(orderDto);
		model.addAttribute("tf", tf);
		model.addAttribute("successMsg","주문에 성공하였습니다.");
		//dao.deleteCart(mid);
		
		return "orderSuccess";
	}
	
	

	
	
}
