package com.spring5.mypro00.common.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrMsgController {
	
	@GetMapping("/accessForbiddenError")
	public String callAccessForbiddenPage(
					String myAccessDeniedMsg,
					HttpServletRequest request,
					Authentication authentication, Model model) {
		
		System.out.println("전달된 authentication: " + authentication);
		model.addAttribute("msg", "접근이 금지됨");
		model.addAttribute("myAccessDeniedMsg", myAccessDeniedMsg);
		
		return "common/err_msg/myAccessForbiddenMsg";
	}
	
}
