package com.myclass.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.myclass.dto.LoginUserDto;
import com.myclass.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@GetMapping("/login")
//	public String loginPage(@RequestParam(required = false) String error, Model model) {
//		System.out.println(error);
//		if (error!=null && !error.isEmpty()) {
//			model.addAttribute("message", "Sai email hoặc mật khẩu!");
//
//		}
//		System.out.println("Tao form dang nhap");
//		model.addAttribute("loginUserDto", new LoginUserDto());
//		System.out.println("da tao");
//		return "test/login";
//	}
	@GetMapping("/login")
	public String login( Model model) {
		model.addAttribute("loginUserDto", new LoginUserDto());
		return "test/login";
	}
	@SuppressWarnings("unused")
	@PostMapping("/login")
	public String saveLogin(Model model, @Valid @ModelAttribute("user") LoginUserDto loginUserDto,
			HttpServletRequest response, BindingResult error) {
		final String JWT_SECRET="chuoi_bi_mat";
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(),
						loginUserDto.getPassword());
		if (error.hasErrors()) {
			return "test/login";
		}
		if (authenticationToken!= null) {
			return "test/login";
		}
		else {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			//create Token
			UserDetails userDetails =(UserDetails) authentication.getPrincipal();
			Date now = new Date();
			
			String token = Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now)
					.setExpiration(new Date(now.getTime()+864000000L))
					.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
			
			System.out.println("Token: "+token);
			
			return "test/contact";
			
		}
	}
	
}
