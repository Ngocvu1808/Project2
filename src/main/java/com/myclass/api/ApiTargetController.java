package com.myclass.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;

@RestController
@RequestMapping("api/target")
@EnableAutoConfiguration
public class ApiTargetController {

	@Autowired
	TargetRepository targetRepository;
	
	
	@GetMapping("")
	public Object get() {
		
		List<Target> UTarget = targetRepository.findAll();
		return new ResponseEntity<List<Target>>(UTarget, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody Target Target) {
		Target Targets = targetRepository.save(Target);
		return new ResponseEntity<Target>(Targets, HttpStatus.CREATED);
		
	}

	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		targetRepository.deleteById(id);
		return new ResponseEntity<String>("Xóa thành công!", HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable int id) {
		Optional<Target> Targets = targetRepository.findById(id);
		Target Target = Targets.get();
		return new ResponseEntity<Target>(Target, HttpStatus.OK);
				
	}
	
	@CrossOrigin
	@GetMapping("{course_id}/{order_index}")
	public Object findByCourseIdAndOrderIndex(@PathVariable int course_id,@PathVariable int order_index) {
		List<Target> target= targetRepository.findByCourseIdAndOrderIndex(course_id, order_index);				
		return new ResponseEntity<List<Target>>(target, HttpStatus.OK);
				
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Target Target) {		
		try {
			if (targetRepository.findById(id)==null||id!=Target.getId()) {		
				return new ResponseEntity<String>("ID không hợp lệ!", HttpStatus.BAD_REQUEST);
			}
			targetRepository.saveAndFlush(Target);		
			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
			
		} catch (Exception e) {			
			return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
