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
@Table(name="Job")

public class Job implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue 
    @Column(name="Job")
	private int jobid;
	
	@Column(name="JobProfile")
		private String jobprofile;
	 @Column(name="JobDesc")
		private String jobdesc;
	 @Column(name="salary")
	 private int salary;
	 @Column(name="Qualifications")
	 private String qualification;
	 
	 @Column(name="Company")
	 private String company;
	 
	 @Column(name="CompanyDesc")
	 private String companydesc;

	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	public String getJobprofile() {
		return jobprofile;
	}

	public void setJobprofile(String jobprofile) {
		this.jobprofile = jobprofile;
	}

	public String getJobdesc() {
		return jobdesc;
	}

	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanydesc() {
		return companydesc;
	}

	public void setCompanydesc(String companydesc) {
		this.companydesc = companydesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
