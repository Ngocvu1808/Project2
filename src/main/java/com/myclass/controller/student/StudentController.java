package com.myclass.controller.student;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Course;
import com.myclass.entity.User;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

@Controller
public class StudentController {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/student")
	public String studentView(Model model) {
		model.addAttribute("courses", courseRepository.findAll());
		return "_student/user-view";
	}
	
	@GetMapping("/studentGetCourse")
	public String courseGet(Model model, @RequestParam int id) {
		Optional<Course> course = courseRepository.findById(id);
		model.addAttribute("course", course);
		return "_student/course-single";

	}
}
