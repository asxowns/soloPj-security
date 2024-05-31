package com.green.uniform2.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.green.uniform2.dto.MemberDto;

public class CustomUserDetails implements UserDetails {
	
	private MemberDto memberDto;
	
	public CustomUserDetails(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return memberDto.getRole();
			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {
		return memberDto.getPassword();
	}

	@Override
	public String getUsername() {
		return memberDto.getUsername();
	}
	
	
	public String getName() {
		return memberDto.getName();
	}
	
	public String getPhone() {
		return memberDto.getPhone();
	}

	public String getAddr() {
		return memberDto.getAddr();
	}
	
	public String getRole() {
		return memberDto.getRole();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	

}
