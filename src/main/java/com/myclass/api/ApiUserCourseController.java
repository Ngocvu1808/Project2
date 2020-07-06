package com.myclass.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.UserCourse;
import com.myclass.repository.UserCourseRepository;

@RestController
@RequestMapping("api/userCourse")
@CrossOrigin
public class ApiUserCourseController {
	
	@Autowired
	UserCourseRepository userCourseRepository;
	
	@GetMapping("")
	public Object get() {
		
		List<UserCourse> UserCourse = userCourseRepository.findAll();
		return new ResponseEntity<List<UserCourse>>(UserCourse, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody UserCourse UserCourse) {
		UserCourse UserCourses = userCourseRepository.save(UserCourse);
		return new ResponseEntity<UserCourse>(UserCourses, HttpStatus.CREATED);
		
	}

	@DeleteMapping("delete/{course_id}/{user_id}")
	public Object delete(@PathVariable int course_id ,@PathVariable int user_id) {
		userCourseRepository.deleteByCourseIdAndUserID(course_id,user_id);
		return new ResponseEntity<String>("Xóa thành công!", HttpStatus.OK);
	}
	
	@PutMapping("{course_id}/{user_id}")
	public Object put(@PathVariable int course_id ,@PathVariable int user_id, @RequestBody UserCourse UserCourse) {		
		try {
			if (userCourseRepository.findByCourseIdAndUserID(course_id, user_id) == null) {
				return new ResponseEntity<String>("ID không hợp lệ!", HttpStatus.BAD_REQUEST);
			}
			userCourseRepository.saveAndFlush(UserCourse);			
			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{course_id}/{user_id}")
	public Object findById(@PathVariable int course_id ,@PathVariable int user_id) {
		UserCourse UserCourses = userCourseRepository.findByCourseIdAndUserID(course_id, user_id);
		return new ResponseEntity<UserCourse>(UserCourses, HttpStatus.OK);
				
	}
	@GetMapping("/{user_id}")
	public Object findByUserId(@PathVariable int user_id) {
		List<UserCourse> userCourses = userCourseRepository.findByUserId(user_id);
		return new ResponseEntity <List<UserCourse>>(userCourses,HttpStatus.OK);
	}
	
}
