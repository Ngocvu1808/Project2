package com.myclass.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller


public class AdminHomeController {

	@RequestMapping(value = "/home")
	   public String homes() {
		return "home/index";
	}
//	@RequestMapping(value = "/login")
//	   public String index() {
//		return "login/index";
//	}
//	
	
}
