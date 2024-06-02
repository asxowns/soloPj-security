package com.green.uniform2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.uniform2.dto.CartDto;
import com.green.uniform2.dto.MemberDto;
import com.green.uniform2.dto.OrderDto;
import com.green.uniform2.dto.ProductDto;

@Mapper
public interface Iuniformdao {

	//회원가입
	public void regist(MemberDto memberDto);
	
	//로그인
	public MemberDto login(MemberDto member);
	
	//회원 아이디(username)로 조회하기
	public MemberDto findByUsername(@Param("username") String username);
	
	//회원 정보 개별조회
	public MemberDto memberGetMid(@Param("username") String username);
	
	//회원 정보수정
	public void update(MemberDto memberDto);
	
	//상품등록
	public void productRegist(ProductDto product);
	
	//상품조회
	public List<ProductDto> productList();
	
	//상품개별조회
	public ProductDto productGetPcode(@Param("pcode") int pcode);
	
	//상품수정
	public void productUpdate(ProductDto product);
	
	//상품삭제
	public void productDelete(@Param("pcode") int pcode);
	
	//장바구니 담기(등록)
	public int cartAdd(CartDto cartDto);
	
	//장바구니 목록(리스트)
	public List<CartDto> cartList(@Param("username") String username);

	//주문하기 페이지 목록(리스트)
	public List<CartDto> orderPageList(@Param("username") String username);
	
	//주문하기 (주문테이블 등록)
	public boolean orderNow(OrderDto orderDto);
	
	//장바구니 개별 조회
	public CartDto cartinfo(@Param("username") String username);
	
	//장바구니 전부삭제(mid)
	public void deleteCart(@Param("username") String username, @Param("pcode") int pcode);
	
	//주문목록 리스트
	public List<OrderDto> orderList(@Param("username") String username);
	
	//username이 있는지 확인
	public Integer getUsername(@Param("username") String username);
	
}
