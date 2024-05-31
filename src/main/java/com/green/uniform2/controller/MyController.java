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
	
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		
		
		
		return "members/welcome";
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
	
	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("username") String username,
			Model model) {
		
		model.addAttribute("member", dao.findByUsername(username));
		
		return "registform";
	}
	
	@RequestMapping("/update")
	public String update(MemberDto memberDto,
			Model model) {
		
		dao.update(memberDto);
		
		return "myPage";
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



	@RequestMapping("/myPage")
	public String mypage(HttpServletRequest request) {

		
		
		return "/members/myPage";
	}

	@RequestMapping("/admin")
	public String admin() {

		return "/admin/admin";
	}

	@RequestMapping("/productRegistForm")
	public String productRegistForm() {

		return "productRegistForm";
	}

	@RequestMapping("/productRegist")
	public String productRegist(ProductDto product) {

		MultipartFile file = product.getImgName();

		if (!file.isEmpty()) {
			try {
				// 파일 저장 경로 설정
				String uploadDir = "C:/dev/solo_project/uniform/src/main/webapp/resource/img/";

				// 파일 저장
				File uploadFile = new File(uploadDir + file.getOriginalFilename());
				file.transferTo(uploadFile);

				// 파일 이름을 DTO의 imgurl 필드에 설정
				product.setImgurl(file.getOriginalFilename());

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		dao.productRegist(product);

		return "redirect:productList";
	}

	@RequestMapping("/productList")
	public String productList(Model model) {

		List<ProductDto> list = dao.productList();

		model.addAttribute("list", list);

		return "productList";
	}

	@RequestMapping("/productUpdateForm")
	public String productUpdateForm(@RequestParam("pcode") int pcode, Model model) {

		model.addAttribute("prod", dao.productGetPcode(pcode));

		return "productRegistForm";
	}

	@RequestMapping("/productUpdate")
	public String productUpdate(ProductDto product) {

		MultipartFile file = product.getImgName();

		if (!file.isEmpty()) {
			try {
				// 파일 저장 경로 설정
				String uploadDir = "C:/dev/solo_project/uniform/src/main/webapp/resource/img/";

				// 파일 저장
				File uploadFile = new File(uploadDir + file.getOriginalFilename());
				file.transferTo(uploadFile);

				// 파일 이름을 DTO의 imgurl 필드에 설정
				product.setImgurl(file.getOriginalFilename());

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		dao.productUpdate(product);

		return "redirect:productList";
	}

	@RequestMapping("/productDelete")
	public String productDelete(@RequestParam("pcode") int pcode) {

		dao.productDelete(pcode);

		return "redirect:productList";
	}

	/* 제품 상세페이지 */
	@RequestMapping("/detail")
	public String detail(Model model, @RequestParam("pcode") int pcode,
			@AuthenticationPrincipal CustomUserDetails customUserDetails) {

		model.addAttribute("prod", dao.productGetPcode(pcode));
		
		model.addAttribute("username", customUserDetails.getUsername());
		model.addAttribute("name", customUserDetails.getName());
		model.addAttribute("role", customUserDetails.getRole());

		return "detail";
	}

	/* 장바구니 */
	@RequestMapping("/cartAdd")
	public String cartAdd(@RequestParam("pcode") int pcode, @RequestParam("mid") int mid,
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

	/* 장바구니 페이지 */
	@RequestMapping("/cartPage")
	public String cartPage(@RequestParam("username") String username,
			Model model) {

		List<CartDto> cartlist = dao.cartList(username);

		model.addAttribute("cartlist", cartlist);

		return "cartPage";
	}
	
	/* 주문하기 페이지 */
	@RequestMapping("/orderPage")
	public String orderPage(@RequestParam("username") String username,
			Model model) {
		
		List<CartDto> orderlist = dao.orderList(username);
		
		model.addAttribute("orderlist", orderlist);
		
		return "orderPage";
	}
	
	@RequestMapping("/order")
	public String order(
			@RequestParam("mid") int mid,
			@RequestParam("pcode") int pcode,
			@RequestParam("amount") int amount,
			OrderDto orderDto,
			Model model) {
		
		boolean tf = dao.orderNow(orderDto);
		model.addAttribute("tf", tf);
		model.addAttribute("successMsg","주문에 성공하였습니다.");
		//dao.deleteCart(mid);
		
		return "orderSuccess";
	}
	
	/* 장바구니 제품삭제 */
	@RequestMapping("/deleteCart")
	public String deleteCart(@RequestParam("mid") int mid,
			@RequestParam("pcode") int pcode) {
		
		dao.deleteCart(mid,pcode);
		
		return "redirect:cartPage?mid=" + mid;
	}

	
	
}
