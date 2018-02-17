package com.collaboration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="ForumRequests")

public class ForumRequests implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ForReqID",nullable = false)
	int forreqid;
	
	
	@Column(name="UserId",nullable = false)
	int userid;
	
	@Column(name="Forumid",nullable = false)
	int forumid;
	
	@Column(name="Forumname",nullable = false)
	String forumname;
	@Column(name="Username",nullable = false)
	String username;
	@Column(name="status")
	private String status;
	public int getForreqid() {
		return forreqid;
	}
	public void setForreqid(int forreqid) {
		this.forreqid = forreqid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public String getForumname() {
		return forumname;
	}
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
