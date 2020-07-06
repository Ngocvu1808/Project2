package com.myclass.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

@RestController
@RequestMapping("api/role")
@EnableAutoConfiguration
public class ApiRoleController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("")
	public Object get() {
		
		List<Role> roles = roleRepository.findAll();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	@PostMapping("")
	public Object post(@RequestBody Role role) {
		Role roles = roleRepository.save(role);
		return new ResponseEntity<Role>(roles, HttpStatus.CREATED);
		
	}
	@GetMapping("search")
	public Object search(@RequestParam String keyword) {
		List<Role> roles = roleRepository.findByDes(keyword);
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
				
	}
	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		roleRepository.deleteById(id);
		return new ResponseEntity<String>("Xóa thành công!", HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable int id) {
		Optional<Role> roles = roleRepository.findById(id);
		Role role = roles.get();
		return new ResponseEntity<Role>(role, HttpStatus.OK);
				
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Role role) {		
		try {
			if ((roleRepository.findById(id)==null)|| id!=role.getId()) {		
				return new ResponseEntity<String>("ID không hợp lệ!", HttpStatus.BAD_REQUEST);
			}
			roleRepository.save(role);		
			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
			
		} catch (Exception e) {			
			return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	
}
