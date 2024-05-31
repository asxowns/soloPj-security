package com.green.uniform2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.uniform2.dao.Iuniformdao;
import com.green.uniform2.dto.MemberDto;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private Iuniformdao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberDto memberDto = dao.findByUsername(username);
		
		if(memberDto != null) {
			return new CustomUserDetails(memberDto);
		}
		
		return null;
	}
}
