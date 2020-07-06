package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query("SELECT r from Category r WHERE title  LIKE %:keyword% ")
	List<Category> findByDes(@Param("keyword")String name);
}
