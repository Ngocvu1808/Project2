package com.myclass.admin;

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

import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;

@Controller
public class AdminCategoryController {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	@GetMapping("/category/category-list")
	public String category(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "/category/category-list";
	}
	
	@GetMapping("/category/category-add")
	public String categoryAddGet(Model model) {
		model.addAttribute("category", new Category());
		return "/category/category-add";
	}
	
	@PostMapping("/category/category-add")
	public String categoryAddPost(Model model, @Valid @ModelAttribute("category") Category category, BindingResult errors) {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {
			return "/category/category-add";
		}

		category.setIcon("fa fa-laptop");
		categoryRepository.save(category);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/category/category-list";
	}
	
	@GetMapping("/category/category-edit")
	public String categoryEditGet(Model model, @RequestParam int id) {
		Optional<Category> category = categoryRepository.findById(id);
		model.addAttribute("category",category);
		return "/category/category-edit";
	}
	
	@PostMapping("/category/category-edit")
	   public String categoryEditPost(@Valid @ModelAttribute("category") Category category, BindingResult errors) {		
		if (errors.hasErrors()) {
			return "/category/category-edit";
		}
		category.setIcon("fa fa-laptop");
		categoryRepository.save(category);
		return "redirect:/category/category-list";
	}
	
	@GetMapping("category/category-delete")
	public String categoryDelete(@RequestParam("id") int id) {
		// Xóa Category theo id
		categoryRepository.deleteById(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/category/category-list";
	}
}
