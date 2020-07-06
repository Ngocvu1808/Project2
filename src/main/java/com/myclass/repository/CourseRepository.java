package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query("SELECT r from Course r WHERE discount > 0 ")
	List<Course> findCourseDiscount();
	
	
	@Query("SELECT r from Course r WHERE discount = null or discount =0 ")
	List<Course> findCourseNotDiscount();
	
	@Query("SELECT r from Course r WHERE title  LIKE %:keyword% ")
	List<Course> findByDes(@Param("keyword")String name);
	
	Course findBycategoryId (int id);
	
	
}
