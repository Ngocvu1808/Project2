package com.myclass.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.LoginUserDto;
import com.myclass.repository.UserRepository;

@Controller
public class AdminLoginController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/admin/login")
	public String loginPage(@RequestParam(required = false) String error, Model model) {
		System.out.println(error);
		if (error!=null && !error.isEmpty()) {
			model.addAttribute("message", "Sai email hoặc mật khẩu!");

		}
		model.addAttribute("loginUserDto", new LoginUserDto());
		return "login/index";
	}
	

	
}
