package com.collaboration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="BlogComments")
public class BlogComments {
	@Id
    @GeneratedValue 
    @Column(name = "BlogcomId", nullable = false)
	private int blogcomid;
	
	@Column(name = "BlogComm", nullable = false)
    private String blogcomm;

	@Column(name = "Blogid", nullable = false)
	private int blogid;
	@Column(name = "Username", nullable = false)
	private String username;
	public int getBlogcomid() {
		return blogcomid;
	}
	public void setBlogcomid(int blogcomid) {
		this.blogcomid = blogcomid;
	}
	public String getBlogcomm() {
		return blogcomm;
	}
	public void setBlogcomm(String blogcomm) {
		this.blogcomm = blogcomm;
	}
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
