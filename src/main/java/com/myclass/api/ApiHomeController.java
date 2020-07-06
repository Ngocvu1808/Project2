package com.myclass.api;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/home")
@EnableJpaRepositories("com.myclass.repository")
public class ApiHomeController {
	@GetMapping("")
	public Object get() {
		return new ResponseEntity<String>("Hello Spring Boot", HttpStatus.OK);
	}
}
