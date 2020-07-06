package com.myclass.controller.teacher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.UserRepository;

@Controller
public class TeacherController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/teacherView")
	public String viewTeacher(Model model) {
		model.addAttribute("courses", courseRepository.findAll());
		return "_teacher/teacher-view";
	}
	
	@GetMapping("/teacherGetCourse")
	public String courseGet(Model model, @RequestParam int id) {
		Optional<Course> course = courseRepository.findById(id);
		model.addAttribute("course", course);
		return "_teacher/course-single";
	}
	
}
