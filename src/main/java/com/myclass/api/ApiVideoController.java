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

import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;

@RestController
@RequestMapping("api/video")
@EnableAutoConfiguration
public class ApiVideoController {

	@Autowired
	VideoRepository videoRepository;
	
	
	@GetMapping("")
	public Object get() {
		
		List<Video> Uvideo = videoRepository.findAll();
		return new ResponseEntity<List<Video>>(Uvideo, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody Video Video) {
		Video Videos = videoRepository.save(Video);
		return new ResponseEntity<Video>(Videos, HttpStatus.CREATED);
		
	}

	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		videoRepository.deleteById(id);
		return new ResponseEntity<String>("Xóa thành công!", HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable int id) {
		Optional<Video> Videos = videoRepository.findById(id);
		Video Video = Videos.get();
		return new ResponseEntity<Video>(Video, HttpStatus.OK);
				
	}
	
	@CrossOrigin
	@GetMapping("search/{course_id}")
	public Object findByCourseId(@PathVariable int course_id) {
		List<Video> video = videoRepository.findBycourseId(course_id);
		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
				
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Video Video) {		
		try {
			if (videoRepository.findById(id)==null||id!=Video.getId()) {		
				return new ResponseEntity<String>("ID không hợp lệ!", HttpStatus.BAD_REQUEST);
			}
			videoRepository.saveAndFlush(Video);		
			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
			
		} catch (Exception e) {			
			return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
