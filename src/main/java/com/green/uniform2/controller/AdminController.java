package com.green.uniform2.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.uniform2.config.CustomUserDetails;
import com.green.uniform2.dao.Iuniformdao;
import com.green.uniform2.dto.ProductDto;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private Iuniformdao dao;

	@RequestMapping("/")
	public String welcome(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		model.addAttribute("username", userDetails.getUsername());
		
		return"admin/welcome";
	}
	
	@RequestMapping("/admin")
	public String admin() {

		return "admin/admin";
	}
	
	/* 상품 등록폼 */
	@RequestMapping("/productRegistForm")
	public String productRegistForm() {

		return "admin/productRegistForm";
	}
	
	/* 상품 등록하기 */
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
	
	/* 상품 리스트 */
	@RequestMapping("/productList")
	public String productList(Model model) {

		List<ProductDto> list = dao.productList();

		model.addAttribute("list", list);

		return "admin/productList";
	}
	
	/* 상품 수정하기 폼*/
	@RequestMapping("/productUpdateForm")
	public String productUpdateForm(@RequestParam("pcode") int pcode, Model model) {

		model.addAttribute("prod", dao.productGetPcode(pcode));

		return "admin/productRegistForm";
	}

	/* 상품 수정하기 */
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

	/* 상품 삭제하기 */
	@RequestMapping("/productDelete")
	public String productDelete(@RequestParam("pcode") int pcode) {

		dao.productDelete(pcode);

		return "redirect:productList";
	}
	
}
