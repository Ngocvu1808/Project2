package com.myclass.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.CheckinTable;
import com.myclass.repository.CheckinTableRepository;
import com.myclass.repository.UserRepository;

@Controller
public class CheckinController {
	
	@Autowired
	CheckinTableRepository checkin;
	
	@Autowired
	UserRepository userRepository;
	
//	@GetMapping("/getQR")
//	public String getQR() {
//		return "_student/checkin";
//	}
	
	@RequestMapping("/getQR")
	public String checkin(Model model) {
		model.addAttribute("checkin", new CheckinTable());
		return "_student/checkin";
	}
}
