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
@Table(name="JobApplications")
public class JobApplications implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue 
    @Column(name="JobapplyId")
	private int jobapplyid;
	
	@Column(name="Jobid")
	private int jobid;
	
	


	@Column(name="UserId")
	private int userid;




	public int getJobapplyid() {
		return jobapplyid;
	}




	public void setJobapplyid(int jobapplyid) {
		this.jobapplyid = jobapplyid;
	}




	public int getJobid() {
		return jobid;
	}




	public void setJobid(int jobid) {
		this.jobid = jobid;
	}




	public int getUserid() {
		return userid;
	}




	public void setUserid(int userid) {
		this.userid = userid;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
