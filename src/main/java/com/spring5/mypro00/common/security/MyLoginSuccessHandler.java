package com.spring5.mypro00.common.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication authentication) 
												throws IOException, ServletException {
		
		System.out.println("Authentication: " + authentication);
		
		//로그인 사용자의 권한을 확인해서
		//ROLE_MEMBER 권한(O) --> /myboard/list로 이동
		//ROLE_MEMBER 권한(X) --> / 이동
		
//		List<String> authNameList = new ArrayList<String>();
//		
//		authentication.getAuthorities()
//					  .forEach(grantedAuthority -> {
//						  authNameList.add(grantedAuthority.getAuthority());
//					  });
		
		Set<String> authNameList = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		
		if (authNameList.contains("ROLE_MEMBER")) {
			response.sendRedirect("/mypro00/myboard/list");
		} else {
			response.sendRedirect("/mypro00/");
		}
		
		
		
	}


}
