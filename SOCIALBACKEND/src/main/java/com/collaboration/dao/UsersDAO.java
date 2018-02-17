package com.collaboration.dao;

import java.util.ArrayList;

import com.collaboration.model.Friend;
import com.collaboration.model.Users;

public interface UsersDAO {
	 ArrayList<Users> getAllUser();
	 public boolean saveUser(Users user);
	 public boolean updateOnlineStatus(Users user);
		public Users getUser(int userid);
		public boolean checkLogin(Users user);
		public Users getUserbyId(int uderid);
		public Users getUserbyemail(String email);
		public ArrayList<Friend> checkismyfriend(int userid,int myid);
		public ArrayList<Users> userrequests();
		public boolean approveusers(Users users);
		public boolean checkLoginsimp(Users user);
		public boolean checkLoginsemail(Users user);
		public boolean rejectusers(Users users);
		
	
}
