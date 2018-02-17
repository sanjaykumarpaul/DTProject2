package com.collaboration.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.ForumDAO;
import com.collaboration.dao.NotificationsDAO;
import com.collaboration.dao.UsersDAO;
import com.collaboration.model.Forum;
import com.collaboration.model.ForumComments;
import com.collaboration.model.ForumRequests;
import com.collaboration.model.Notifications;
import com.collaboration.model.Users;

@RestController
@RequestMapping("/forums")
public class ForumController {
	@Autowired 
	ForumDAO forumDAO;
	@Autowired 
	UsersDAO usersDAO;
	@Autowired 
	NotificationsDAO notificationsDAO;
	
	@RequestMapping(value="/getAllForums",method=RequestMethod.GET)
	public  ArrayList<Forum> getAllForums(){
		
		ArrayList<Forum> forum=forumDAO.getAllForum();
		
		return  forum;
				
	}
	
	
	@RequestMapping(value="/addForum",method=RequestMethod.POST)
	public ResponseEntity<Forum> addForum(@RequestBody Forum forum){
		
		
		boolean isSaved=forumDAO.addForum(forum);
		if(isSaved)
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		else
			return new ResponseEntity<Forum>(forum,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@RequestMapping(value="/getForumById/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<Forum> getForum(@PathVariable("forumid") int forumId){
	
	
	if(forumDAO.getForum(forumId)==null){
		
	}
	return new ResponseEntity<Forum>(forumDAO.getForum(forumId),HttpStatus.OK);	
			
	
	
	
	}
	
	
	

	
	
	
	
	@RequestMapping(value="/deleteForum/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<Forum> deleteForum(@PathVariable("forumid") int forumId){

	Forum forum=forumDAO.getForum(forumId);
	forumDAO.deleteForum(forum);
	ArrayList<ForumRequests> fr=forumDAO.getAllForumRequestAll(forumId);
	for(ForumRequests f:fr)
	{
		forumDAO.deleteForumRequest(f);
	}
	
	ArrayList<ForumComments> fc=forumDAO.getAllForumCommentsById(forumId);
	for(ForumComments fcc:fc)
	{
		forumDAO.deleteForumComment(fcc);
	}
	if(forumDAO.getForum(forumId)==null){
		
	}
	return new ResponseEntity<Forum>(forum,HttpStatus.OK);	
			
	
	
	
	}

	@RequestMapping(value="/updateForum/{forumid}/{forumname}/{forumcontent}",method=RequestMethod.POST)
	public ResponseEntity<Forum> updateForum(@PathVariable("forumid") int forumid,@PathVariable("forumname") String forumname,@PathVariable("forumcontent") String forumcontent){
		System.err.println(forumid+"  "+forumname+"  "+forumcontent);
		Forum tempforum=forumDAO.getForum(forumid);
		tempforum.setFormname(forumname);
		tempforum.setFormcontent(forumcontent);
		boolean isSaved=forumDAO.updateForum(tempforum);
		if(isSaved)
		return new ResponseEntity<Forum>(tempforum,HttpStatus.OK);
		else
			return new ResponseEntity<Forum>(tempforum,HttpStatus.BAD_REQUEST);
		
	}
	
	

	

	
	@RequestMapping(value="/addForumComments/{forumid}/{username}/{forumcomm}",method=RequestMethod.GET)
	public ResponseEntity<ForumComments> addForumcomments(@PathVariable("forumid") int forumid,@PathVariable("username") String username,@PathVariable("forumcomm") String forumcomm){

		
		ForumComments forumcomments=new ForumComments();
		forumcomments.setForumcomm(forumcomm);
		forumcomments.setForumid(forumid);
		forumcomments.setUsername(username);

		boolean isSaved=forumDAO.addForumComment(forumcomments);
		if(isSaved)
		return new ResponseEntity<ForumComments>(forumcomments,HttpStatus.OK);
		else
			return new ResponseEntity<ForumComments>(forumcomments,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@RequestMapping(value="/updateForumComments",method=RequestMethod.PUT)
	public ResponseEntity<ForumComments> updateBlogComments(@RequestBody ForumComments forumcomments){
		
		boolean isSaved=forumDAO.updateForumComment(forumcomments);
		if(isSaved)
		return new ResponseEntity<ForumComments>(forumcomments,HttpStatus.OK);
		else
			return new ResponseEntity<ForumComments>(forumcomments,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@RequestMapping(value="/deleteForumComment/{forumcommentid}",method=RequestMethod.DELETE)
	public ResponseEntity<ForumComments> deleteForumComment(@PathVariable("forumcommentid") int forumcommentId){
	
	ForumComments forumComments=forumDAO.getForumComment(forumcommentId);
forumDAO.deleteForumComment(forumComments);
	if(forumDAO.getForumComment(forumcommentId)==null)
	{
		
	}
	return new ResponseEntity<ForumComments>(forumDAO.getForumComment(forumcommentId),HttpStatus.OK);	
			
	}
	
	
	@RequestMapping(value="/getForumComment/{forumcommentId}",method=RequestMethod.GET)
	public ResponseEntity<ForumComments> getforumComment(@PathVariable("forumcomentid") int forumcommentId){
	
	
	if(forumDAO.getForumComment(forumcommentId)==null){
		
	}
	return new ResponseEntity<ForumComments>(forumDAO.getForumComment(forumcommentId),HttpStatus.OK);	
			
	
	
	
	}
	
	@RequestMapping(value="/getAllForumComments/{forumId}",method=RequestMethod.GET)
	public ArrayList<ForumComments> getAllForumComment(@PathVariable("forumId") int forumId){
		System.err.println(forumId);
	
	ArrayList<ForumComments> forumcomments=forumDAO.getAllForumCommentsById(forumId);
			if(forumcomments.isEmpty()){
				return null;
			}
			else
			{
			return forumcomments;
					
			}
	
	}
	
	@RequestMapping(value="/myforums/{myid}",method=RequestMethod.GET)
	public ArrayList<Forum> getmyforums(@PathVariable("myid") int myid)
	{ArrayList<Forum> myforums=new ArrayList<Forum>();  
		ArrayList<ForumRequests> freq=forumDAO.getAllMyForum(myid);
		for(ForumRequests f:freq)
		{
			
			Forum fo=forumDAO.getForum(f.getForumid());
			myforums.add(fo);
		}
		return myforums;
	}
	
	
	@RequestMapping(value="/leaveforum/{forumid}/{myid}",method=RequestMethod.GET)
	public void leaveforum(@PathVariable("myid") int myid,@PathVariable("forumid") int forumid)
	{
		Users user=(Users)usersDAO.getUser(myid);
		ForumRequests fr=forumDAO.myforreq(user.getEmail(), forumid);
		forumDAO.deleteForumRequest(fr);
		
		
	}
	
	
	
	@RequestMapping(value="/checkIfMyForum/{forumid}/{myid}",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<ForumRequests>> getcheckifmyforum(@PathVariable("forumid") int forumId,@PathVariable("myid") int myid){
		
		System.err.println(forumId+"  "+myid);
		ForumRequests f=new ForumRequests();
	ArrayList<ForumRequests> foru=forumDAO.checkIfMyForum(forumId, myid);
	return new ResponseEntity<ArrayList<ForumRequests>>(foru,HttpStatus.OK);	
			
	
	
	
	}
	
	
	@RequestMapping(value="/addForumReq/{forumid}/{myid}",method=RequestMethod.GET)
	public ResponseEntity<ForumRequests> addForumReq(@PathVariable("forumid") int forumId,@PathVariable("myid") int myid){
	
	Users u=usersDAO.getUser(myid);
	Forum f=forumDAO.getForum(forumId);
	ForumRequests fr=new ForumRequests();
	fr.setForumid(forumId);
	fr.setUserid(myid);
	fr.setStatus("A");
	fr.setUsername(u.getEmail());
	fr.setForumname(f.getFormname());
	
	
	
		boolean isSaved=forumDAO.addForumRequest(fr);
		if(isSaved)
		{
			return new ResponseEntity<ForumRequests>(fr,HttpStatus.OK);	
		}
		else
		{
			return new ResponseEntity<ForumRequests>(fr,HttpStatus.BAD_REQUEST);
		}
				
	
	
	
	}
	
	
	@RequestMapping(value="/getForumRequests",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<ForumRequests>> getForumrequests()
    {
		ArrayList<ForumRequests> ff=forumDAO.getAllForumRequest();
		for(ForumRequests fff:ff)
		{
			System.err.println(fff.getForumname());
			System.err.println(fff.getUsername());
			
		}
	return new ResponseEntity<ArrayList<ForumRequests>>(forumDAO.getAllForumRequest(),HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/approveForumRequests/{forumReqId}",method=RequestMethod.GET)
	public void approveForumRequets(@PathVariable("forumReqId") int forumreqid)
	{
		ForumRequests fr=forumDAO.getForumRequest(forumreqid);
		fr.setStatus("YES");
boolean IsSaved=forumDAO.acceptForumRequest(fr);

String noti="your forumrequest for forum:"+fr.getForumname()+" is approved";
Notifications not=new Notifications();
not.setName(noti);
not.setUsername(fr.getUsername());

notificationsDAO.addNotifications(not);

	}
	
	
	@RequestMapping(value="/rejectForumRequests/{forumReqId}",method=RequestMethod.GET)
	public void rejectForumRequets(@PathVariable("forumReqId") int forumreqid)
	{
		ForumRequests fr=forumDAO.getForumRequest(forumreqid);
		fr.setStatus("R");
boolean IsSaved=forumDAO.rejectForumRequest(fr);
String noti="your forumrequest for forum:"+fr.getForumname()+" is approved";
Notifications not=new Notifications();
not.setName(noti);
not.setUsername(fr.getUsername());

notificationsDAO.addNotifications(not);
	}
	
	
	
	
	@RequestMapping(value="/forumreqbyforumid/{forumid}",method=RequestMethod.GET)
	public ArrayList<Users> getforumusers(@PathVariable("forumid") int forumid)
	{ArrayList<Users> users=new ArrayList<Users>(); 
		ArrayList<ForumRequests> f=forumDAO.forreqbyforid(forumid);
		for(ForumRequests ff:f)
		{
			Users u=usersDAO.getUser(ff.getUserid());
			users.add(u);
		}
		return users;
	}

}
