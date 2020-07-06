package com.myclass.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name = "user_courses")
@IdClass(UserCoursePK.class)
public class UserCourse{

	
	@Valid
	/* @Column(name = "role_id") */
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private int roleId;
	
	
	@Id
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id")
	//@JoinColumn(name = "role_id", referencedColumnName = "id")
	private User user;
	
	@Id
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "course_id")
	//@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Course course;


	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public UserCourse() {
		
	}
	public UserCourse(@Valid int roleId, User user, Course course) {
		super();
		this.roleId = roleId;
		this.user = user;
		this.course = course;
	}



	
	
}
