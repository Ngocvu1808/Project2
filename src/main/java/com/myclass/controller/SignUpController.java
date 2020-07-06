package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

@Controller
public class SignUpController {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/register")
	public String UserAddGet(Model model) {
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("user", new User());
		return "test/register";
	}


	@PostMapping("/register")
	public String UserAddPost(Model model,@Valid @ModelAttribute("user") User user, BindingResult errors) {
		User users = userRepository.findByEmail(user.getEmail());

		if(errors.hasErrors()) {
			model.addAttribute("roles", roleRepository.findAll());
			return "test/register";
		}
		if(users !=null) {
			model.addAttribute("message","Vui lòng chọn email khác!");
			model.addAttribute("roles", roleRepository.findAll());
			return "test/register";
		}
		else {
			String hashsed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashsed);
			user.setConfirmpassword(hashsed);
			userRepository.save(user);
			model.addAttribute("message", "Đăng ký thành công!");
			return "test/courses";
		}
	}
}
