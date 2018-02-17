package com.collaboration.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.FriendDAO;
import com.collaboration.dao.UsersDAO;
import com.collaboration.model.Friend;
import com.collaboration.model.Users;

@RestController
@RequestMapping("/friend")

public class FriendController {
	
	@Autowired 
	FriendDAO friendDAO;
	@Autowired 
	UsersDAO userDAO;
	
	
	@RequestMapping(value="/addFriend/{myid}/{friendid}",method=RequestMethod.GET)
	public ResponseEntity<String> addBlog(@PathVariable("myid") int myid,@PathVariable("friendid") int friendid){
	
Friend friend=new Friend();

friend.setU_ID(myid);
friend.setFRI_ID(friendid);
friend.setStatus("A");

		
		
		boolean isSaved=friendDAO.addFriend(friend);
		if(isSaved)
		{
			
		return new ResponseEntity<String>("Adding friend successfull",HttpStatus.OK);
		}
		else
		{	
			return new ResponseEntity<String>("Error in adding friend",HttpStatus.BAD_REQUEST);
		}

	}
	
	 @RequestMapping(value="/getMyFriends/{myid}",method=RequestMethod.GET)
	 public ArrayList<Users> getMyFriends(@PathVariable("myid") int myid)
	 {
		 
	ArrayList<Friend> myFriends=friendDAO.getAllMyFriend(myid) ;
	ArrayList<Users> user=new ArrayList<Users>();
	
	
	for(Friend s:myFriends)
	{
		if(s.getU_ID()==myid)
		{
			user.add(userDAO.getUserbyId(s.getFRI_ID()));
					}
		else if(s.getFRI_ID()==myid)
		{
		 user.add(userDAO.getUserbyId(s.getU_ID()));
		}
	}
	
	
	
	return user;
		 
	 }
	
	

	 @RequestMapping(value="/getAllOtherUsers/{myid}",method=RequestMethod.GET)
		public ArrayList<Users> getAllFriends(@PathVariable("myid") int myid)
	 {Users us=new Users();
		ArrayList<Users> searchFriends=new ArrayList<Users>();
		ArrayList<Friend> myFriends=friendDAO.getAllMyFriend(myid);
		ArrayList<String> myfriendsname=new ArrayList<String>(); 
		for(Friend s:myFriends)
		{
			if(s.getU_ID()==myid)
			{
				myfriendsname.add(userDAO.getUser(s.getFRI_ID()).getEmail());
			}
			else if(s.getFRI_ID()==myid)
			{
				myfriendsname.add(userDAO.getUser(s.getU_ID()).getEmail());
			}
		}
	
		
		ArrayList<Users> allUser=userDAO.getAllUser();
			
		
		for(Users u:allUser)
		{int count=0;
			if(u.getUserid()!=myid)
			{
			for(String s:myfriendsname)
			{
				if(u.getEmail()!=s)
				{
					count++;
				}
					
				
			}
			if(count==myfriendsname.size())
			{
				searchFriends.add(u);
			}
		}
			
		}
ArrayList<Friend> pend=friendDAO.getAllpendingentries(myid);
ArrayList<String> pendnames=new ArrayList<String>();


for(Friend s:pend)
{
	if(s.getU_ID()==myid)
	{
		pendnames.add(userDAO.getUser(s.getFRI_ID()).getEmail());
	}
	else if(s.getFRI_ID()==myid)
	{
		pendnames.add(userDAO.getUser(s.getU_ID()).getEmail());
	}
}


ArrayList<Users> newFriends=new ArrayList<Users>();
for(Users uu:searchFriends)
{ int count=0;
	
	for(String ff:pendnames)
	{
		if(uu.getEmail()!=ff)
		{
			count++;
		}
		
	}
	if(count==pendnames.size())
	{
		newFriends.add(uu);
	}
}
		return newFriends; 	
		}
	 
	 
	 
	 @RequestMapping(value="/getOnlineFriends/{myid}",method=RequestMethod.GET)
	 public ArrayList<Users> getOnlineFriends(@PathVariable("myid") int myid)
	 {
		 
	ArrayList<Friend> myFriends=friendDAO.getAllMyFriend(myid) ;
	ArrayList<Users> user=new ArrayList<Users>();
	for(Friend s:myFriends)
	{
		if(s.getU_ID()==myid)
		{
			user.add(userDAO.getUserbyId(s.getFRI_ID()));
		}
		else if(s.getFRI_ID()==myid)
		{
			user.add(userDAO.getUserbyId(s.getU_ID()));
		}
	}
for(Users us:user)
{
	System.err.println(us.getEmail());
}
	ArrayList<Users> onlineusers=new ArrayList<Users>();
	for(Users uu:user)
	{
		if(uu.getIsonline().equals("YES"))
		{
			onlineusers.add(uu);
		}
	}
	return onlineusers;
		 
	 }
	 
	 @RequestMapping(value="/getAllMyFriendRequests/{myid}",method=RequestMethod.GET)
	 public ArrayList<Users> getAllMyFriendRequests(@PathVariable("myid") int myid)
	 
	 {
		 ArrayList<Users> frequests =new ArrayList<Users>(); 
	 ArrayList<Friend> frireq=friendDAO.getAllFriendRequestsByUser(myid);
	 for(Friend f:frireq)
	 {
		 System.err.println(f.getU_ID());
	 }
	 
	 
	 for(Friend f:frireq)
	 {

			if(f.getU_ID()==myid)
			{
				frequests.add(userDAO.getUserbyId(f.getFRI_ID()));
			}
			else if(f.getFRI_ID()==myid)
			{
				frequests.add(userDAO.getUserbyId(f.getU_ID()));
			} 
	 }
	 return frequests; 
	 }
	 
@RequestMapping(value="/unfriend/{myid}/{friendid}",method=RequestMethod.GET)
public Users unfriend(@PathVariable("friendid") int friendid,@PathVariable("myid") int myid)
{
Friend fr=friendDAO.getfriendrequest(myid, friendid);
friendDAO.delete(fr);
return null;
}


@RequestMapping(value="/acceptfriend/{myid}/{friendid}",method=RequestMethod.GET)
public Users acceptfriend(@PathVariable("friendid") int friendid,@PathVariable("myid") int myid)
{
Friend fr=friendDAO.getfriendrequest(myid, friendid);
fr.setStatus("YES");
friendDAO.acceptfriendrequest(fr);
return null;
}




}
