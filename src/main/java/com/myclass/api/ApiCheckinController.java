package com.myclass.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.CheckinTable;
import com.myclass.entity.User;
import com.myclass.repository.CheckinTableRepository;

@RestController
@RequestMapping("api/checkin")
@CrossOrigin
public class ApiCheckinController{
	
	@Autowired
	CheckinTableRepository checkinTableRepository;
	
	@GetMapping("")
	public Object get() {
		List<CheckinTable> checkinTables = checkinTableRepository.findAll();
		return new ResponseEntity<List<CheckinTable>>(checkinTables, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody CheckinTable checkinTable) {
		User user = checkinTableRepository.findByEmail(checkinTable.getEmail());
		if (user!= null) {
			return new ResponseEntity<String>("New Email!", HttpStatus.BAD_REQUEST);
		}else {
			CheckinTable checkin = checkinTableRepository.save(checkinTable);
			return new ResponseEntity<CheckinTable>(checkin, HttpStatus.CREATED);
		}
	}
}
