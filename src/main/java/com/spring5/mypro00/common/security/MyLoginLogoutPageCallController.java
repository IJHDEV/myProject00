package com.spring5.mypro00.common.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyLoginLogoutPageCallController {
	
//	@GetMapping("/myLogin")
	@GetMapping("/login")
	public String callLoginPage (String error, String logout, Model model) {
		
		if (error != null) {
			System.out.println("==========:error.length(): " + error.length());
			System.out.println("==========:error.hashCode(): " + error.hashCode());
			model.addAttribute("error", "로그인 오류. 계정이나 암호를 확인하세요");
			
		} else if (logout != null) {
			System.out.println("==========:logout.length(): " + logout.length());
			System.out.println("==========:logout.hashCode(): " + logout.hashCode());
			model.addAttribute("logout", "정상적으로 로그아웃됨");
			
		} else {
			model.addAttribute("normal", "정상적인 로그인 페이지 호출");
			
		}
		
		return "common/myLogin";
	}
	
	@GetMapping("/myLogout")
	public String callLogoutPage () {
		
		return "common/myLogout";
	}
	
	
	
	
}
