package com.myclass.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name ="checkin")
public class CheckinTable implements Serializable{
	
	private static final long serialVersionUID = -1756737329828637945L;

	@Id
	@GeneratedValue
	private int id;
	
	
	@Column(name="email")
	private String email;

	public CheckinTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CheckinTable(int id, String email) {
		super();
		this.id = id;
		this.email = email;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return "CheckinTable [id="+ id + ", email= "+email +"]";
		
	}
}
