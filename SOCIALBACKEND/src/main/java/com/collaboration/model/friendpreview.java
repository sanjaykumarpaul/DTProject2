package com.collaboration.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component

public class friendpreview {
	String userid;
	String fname;
	String lname;
	String status;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
