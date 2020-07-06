package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myclass.entity.CheckinTable;
import com.myclass.entity.User;


public interface CheckinTableRepository extends JpaRepository<CheckinTable, Integer>{

	User findByEmail (String email);
	
	@Query("FROM CheckinTable WHERE id=:id")
	List<CheckinTable> findByCheckinId(@Param("id") int id);
	
}
