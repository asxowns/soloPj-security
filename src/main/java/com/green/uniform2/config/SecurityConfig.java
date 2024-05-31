package com.green.uniform2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((auth) -> auth
					.requestMatchers(
							new AntPathRequestMatcher("/members/**")
							).authenticated()
					.requestMatchers(
							new AntPathRequestMatcher("/admin/**")
							).hasRole("ADMIN")
					.anyRequest().permitAll()
				);
		
//		http.authorizeHttpRequests((auth) -> auth
//				.requestMatchers(
//						new AntPathRequestMatcher("/"),
//						new AntPathRequestMatcher("/registform"),
//						new AntPathRequestMatcher("/regist")).permitAll()
//				.requestMatchers(
//						new AntPathRequestMatcher("/members/**")).hasAnyRole("ADMIN", "MEMBER") // hasAnyRole - 여러개
//				.requestMatchers(
//						new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN") // hasRole - 한개
//				.anyRequest().authenticated()
//				);
		
		http.formLogin((auth) -> auth
				.loginPage("/loginform") //로그인폼 지정 - 쓰지않으면 Spring Security가 제공하는 로그인 폼 사용
				.loginProcessingUrl("/loginProc")//로그인폼 지정 후 폼 파라미터 보내는 경로지정 - 컨트롤러에 직접 만들지 않는다.(Spring Security가 알아서 처리함)
				.defaultSuccessUrl("/")
				.permitAll()
				);
		
		
		http.csrf(AbstractHttpConfigurer::disable);
		
		return http.build();
	}

}
