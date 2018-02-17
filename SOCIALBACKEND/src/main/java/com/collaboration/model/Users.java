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
@Table(name="USERS")
public class Users  extends Errormessages implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	 @Column(name = "USERID", nullable = false)
	private int userid;
	
	 @Column(name = "Firstname", nullable = false)
	    private String firstname;
	 
	 @Column(name = "Lastname", nullable = false)
	    private String lastname;
	 


	@Column(name = "Email", nullable = false)
    private String email;
	
    @Column(name = "Password", nullable = false)
    private String password;
    
    @Column(name = "Role", nullable = false)
    private String role;
    
   
    
    @Column(name = "Status", nullable = false)
    private String status;
    @Column(name = "Isonline", nullable = false)
    private String isonline;
    
	@Column(name = "image")
    private String image;

	@Column(name = "cover")
    private String cover;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}