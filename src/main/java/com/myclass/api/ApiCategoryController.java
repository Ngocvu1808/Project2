package com.myclass.api;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;


@RestController
@RequestMapping("api/category")
@CrossOrigin
public class ApiCategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("")
	public Object get() {
		
		List<Category> category = categoryRepository.findAll();
		return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
	}
	@PostMapping("")
	public Object post(@RequestBody Category category) {
		Category Category = categoryRepository.save(category);
		return new ResponseEntity<Category>(Category, HttpStatus.CREATED);
		
	}
	@GetMapping("search")
	public Object search(@RequestParam String keyword) {
		List<Category> category = categoryRepository.findByDes(keyword);
		return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
				
	}
	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		categoryRepository.deleteById(id);
		return new ResponseEntity<String>("Xóa thành công!", HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Category Category) {		
		try {
			if (categoryRepository.findById(id)== null || id!=Category.getId()) {				
				return new ResponseEntity<String>("ID không hợp lệ!", HttpStatus.BAD_REQUEST);
			}
			categoryRepository.saveAndFlush(Category);			
			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable int id) {
		Optional<Category> categorys = categoryRepository.findById(id);
		Category category = categorys.get();
		return new ResponseEntity<Category>(category, HttpStatus.OK);
				
	}
}
