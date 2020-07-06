package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer>{

	
	@Query("FROM Video  WHERE course_id=:course_id order by order_index asc ")
	List<Video> findBycourseId(int course_id);
}
