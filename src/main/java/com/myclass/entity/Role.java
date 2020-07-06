package com.myclass.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "Vui lòng nhập tên!")
	private String name;
	@NotBlank(message = "Vui lòng nhập mô tả!")
	private String description;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	@JsonIgnore()
	private List<User> users;
	
	 @OneToMany(mappedBy = "roleId")
	 Set <UserCourse> usercourse;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role() {

	}

	public Role(int id, String name, String description) {

		this.id = id;
		this.name = name;
		this.description = description;
	}

}
