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
@Table(name="Friend")
public class Friend implements Serializable{
private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue 
    @Column(name="FriendReqId")
	private int friendreqid;	
    private int U_ID;
    private int FRI_ID;
	@Column(name="Status")
	private  String status;
	public int getFriendreqid() {
		return friendreqid;
	}
	public void setFriendreqid(int friendreqid) {
		this.friendreqid = friendreqid;
	}
	public int getU_ID() {
		return U_ID;
	}
	public void setU_ID(int u_ID) {
		U_ID = u_ID;
	}
	public int getFRI_ID() {
		return FRI_ID;
	}
	public void setFRI_ID(int fRI_ID) {
		FRI_ID = fRI_ID;
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
