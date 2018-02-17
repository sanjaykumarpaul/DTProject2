package com.collaboration.restcontroller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.collaboration.dao.FriendDAO;
import com.collaboration.dao.JobDAO;
import com.collaboration.dao.UsersDAO;
import com.collaboration.model.Friend;
import com.collaboration.model.Job;
import com.collaboration.model.Users;

@RestController

@RequestMapping("/user")
public class UserController {
@Autowired
UsersDAO userDAO;
@Autowired
JobDAO jobDAO;	
@Autowired
FriendDAO friendDAO;	
@Autowired
private MailSender sendmail;

	 @RequestMapping(value="/getAllUsers",method=RequestMethod.GET)
		public ArrayList<Users> getAllUser(){
		 System.out.println("in rest controller getallusers");
			ArrayList<Users> user=(ArrayList<Users>)userDAO.getAllUser();
			System.out.println("in rest controller getallusers");

		return user;		
		}
	 
	 @RequestMapping(value="/getAllUsersreq",method=RequestMethod.GET)
		public ArrayList<Users> getAllUserreq(){
		 System.out.println("in rest controller getallusersreq");
			ArrayList<Users> userreq=(ArrayList<Users>)userDAO.userrequests();
			System.out.println("in rest controller getallusersreq");

		return userreq;		
		} 
	 
	 @RequestMapping(value="/approveusers/{username}",method=RequestMethod.GET)
	 public void approveusers(@PathVariable("username") String username)
	 {String email=username+".com";
		 Users users =userDAO.getUserbyemail(email);
		 users.setStatus("A");
		 userDAO.approveusers(users);
		 
		 SimpleMailMessage emaile = new SimpleMailMessage();
	        emaile.setTo(users.getEmail());
	        emaile.setSubject("Thanks for registering to SOCIAL");
	        emaile.setText("Make your blogs !! Express you rideas through forums!!! Participate in the events, apply jobs  and build your carrier!!! Make friends And chat with them an d improve your social life .Warm greetings from SOCIAL Team/n Any queries please contact "
	        		+ "sanjaykumarpaul/n"
	        		+ "NIIT LTD/n"
	        		+ "8129925706/n"
	        		+ "sanjaykumarpaul2@gmail.com");
	         
	        // sends the e-mail
	        sendmail.send(emaile);
		 
		 
	 }
	 
	 @RequestMapping(value="/rejectusers/{username}",method=RequestMethod.GET)
	 public void rejectusers(@PathVariable("username") String username)
	 {String email=username+".com";
		 Users users =userDAO.getUserbyemail(email);
		 users.setStatus("R");
		 userDAO.rejectusers(users);
		 
	 }
	 
	 
	 
	 @RequestMapping(value="/getUser/{userid}",method=RequestMethod.GET)
		public ResponseEntity<Users> getUser(@PathVariable("userid") int userId){
			
		
			return new ResponseEntity<Users>(userDAO.getUser(userId),HttpStatus.OK);
					
		}
	
	
	 
		@RequestMapping(value="/getUserByEmail/{useremail}",method=RequestMethod.GET)
		public ResponseEntity<Users> userbyemail(@PathVariable("useremail") String useremail){
		
			 String useremaill=useremail+".com";
				System.out.println(useremaill); 
				Users tempuser=userDAO.getUserbyemail(useremaill);
				System.out.println(tempuser.getFirstname());
				System.out.println(tempuser.getLastname());
		if(tempuser==null){
			return new ResponseEntity<Users>(tempuser,HttpStatus.BAD_REQUEST);		
		}
		else
		{
		return new ResponseEntity<Users>(tempuser,HttpStatus.OK);	
		}		
		
		
		
		}
	 
	
	 
	 @RequestMapping(value="/logout/{email}",method=RequestMethod.GET)
		public ResponseEntity<String> logout(@PathVariable("email") String email){
		 System.out.println(email);
		 
	 String emaill=email+".com";
System.out.println(emaill);
	 
Users tempuser=userDAO.getUserbyemail(emaill);
		 tempuser.setIsonline("NO");
		userDAO.updateOnlineStatus(tempuser);
		return new ResponseEntity<String>("Lgout success",HttpStatus.OK);		 
}
	 
	 
	 @RequestMapping(value="/register",method=RequestMethod.POST)
		public ResponseEntity<Users> createUser(@RequestBody Users user){
			user.setFirstname(user.getFirstname().toUpperCase());
			user.setLastname(user.getLastname().toUpperCase());
			boolean isSaved=userDAO.saveUser(user);
			if(isSaved) {
			return new ResponseEntity<Users>(user,HttpStatus.OK);
			}
			else
				return new ResponseEntity<Users>(user,HttpStatus.BAD_REQUEST);
			
		}
	
	 @RequestMapping(value="/login",method=RequestMethod.POST)
		public ResponseEntity<Users> login(@RequestBody Users user,HttpSession http)
	 
	 {
		


		 
			if(userDAO.checkLogin(user))
			{
				 Users tempuser=userDAO.getUserbyemail(user.getEmail());
			
			tempuser.setIsonline("YES");
				userDAO.updateOnlineStatus(tempuser);
	tempuser.setErrorcode(200);
	tempuser.setErrormessage("login success");
			http.setAttribute("currentuser",tempuser);	
			return new ResponseEntity<Users>(tempuser,HttpStatus.OK);
				
				
			}
			else
			{
			Users tempuser1=new Users();
			
			if(userDAO.checkLoginsimp(user))
			{
				Users tempuser=userDAO.getUserbyemail(user.getEmail());
				
				if(tempuser.getStatus().equals("P"))
				{
			tempuser1.setErrorcode(200);
			tempuser1.setErrormessage("You are not yet approved by admin");
			return new ResponseEntity<Users>(tempuser1,HttpStatus.OK);
			}
				else
				{
					tempuser1.setErrorcode(200);
					tempuser1.setErrormessage("You rejected please contact admin");
					return new ResponseEntity<Users>(tempuser1,HttpStatus.OK);
				}
				
				
				
			}
			
			else
			{
				if(userDAO.checkLoginsemail(user))
				
				{
						tempuser1.setErrorcode(200);
						tempuser1.setErrormessage("email id or password incorrect");
						return new ResponseEntity<Users>(tempuser1,HttpStatus.OK);
					}
					else
					{
						tempuser1.setErrorcode(200);
						tempuser1.setErrormessage("You are not registered yet");
						return new ResponseEntity<Users>(tempuser1,HttpStatus.OK);
					}
			
				
				
				
			}
			
			
					
				}
			
				}
				
				
			
			
		
	
	 @RequestMapping(value="/job",method=RequestMethod.POST)
		public ResponseEntity<Job> getJob(){
		
			
				return new ResponseEntity<Job>(jobDAO.getjob(33),HttpStatus.BAD_REQUEST);
			
		}
	 
	 
	 
	 @RequestMapping(value="/up",method = RequestMethod.POST)
	 public ModelAndView  upload(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	  /* String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		*/
		 
		 Users user = (Users)session.getAttribute("currentuser");
		 	System.out.println(user.getEmail());
		 		user.setImage(user.getEmail()+".jpg");
		 	userDAO.updateOnlineStatus(user); 
		 	
		 	
	 	    String filepath ="D:/DTProject2/SOCIALFRONTEND/WebContent/resources/images/" + user.getEmail()+".jpg";
	 		
	 		
	 		System.out.println(filepath);
	 		try {
	 			byte imagebyte[] = file.getBytes();
	 			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
	 			fos.write(imagebyte);
	 			fos.close();
	 			} catch (IOException e) {
	 			e.printStackTrace();
	 			} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 			}
	 		
	 	
	 	ModelAndView mv = new ModelAndView("backhome");
		return mv;
	 }
	 
	 
	 @RequestMapping(value="/upcover",method = RequestMethod.POST)
	 public ModelAndView uploadcover(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	  /* String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		*/
		 
		 Users user = (Users)session.getAttribute("currentuser");
		 	
		 		user.setCover(user.getEmail()+"cover.jpg");
		 	userDAO.updateOnlineStatus(user);
	 	    String filepath ="D:/DTProject2/SOCIALFRONTEND/WebContent/resources/images/" +user.getEmail()+"cover.jpg";
	 		String img=file.getOriginalFilename();
	 		System.out.println(img);
	 		System.out.println(filepath);
	 		try {
	 			byte imagebyte[] = file.getBytes();
	 			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
	 			fos.write(imagebyte);
	 			fos.close();
	 			} catch (IOException e) {
	 			e.printStackTrace();
	 			} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 			}
	 		
	 	
	 	ModelAndView mv = new ModelAndView("backhome");
		return mv;
	 }
	 
	 
	 @RequestMapping(value="/ismyfriend/{userid}/{myid}",method = RequestMethod.GET)
	 public ArrayList<Users> ismyfriend(@PathVariable("userid") int userid,@PathVariable("myid") int myid)
	 {
		 System.out.println("in is my friend controller");
		 ArrayList<Friend> friends = (ArrayList<Friend>)userDAO.checkismyfriend(userid, myid);
		ArrayList<Users> users= new  ArrayList<Users>();
		for(Friend f:friends)
		{
			if(f.getU_ID()==myid)
			{
				users.add(userDAO.getUserbyId(f.getFRI_ID()));
			}
			else if(f.getFRI_ID()==myid)
			{
				users.add(userDAO.getUserbyId(f.getU_ID()));
			} 
			
		}
	 return users;
	
}
	 
	 
	 @RequestMapping(value="/friendsfriends/{userid}/{myid}",method = RequestMethod.GET)
	 public ArrayList<Users> friendsfriends(@PathVariable("userid") int userid,@PathVariable("myid") int myid )
	 { 
		 System.out.println(userid+" "+myid);
	 ArrayList<Users> fp=new ArrayList<Users>(); 
	 ArrayList<Friend> myfriends=(ArrayList<Friend>)friendDAO.getAllMyFriendpend(myid);
	 ArrayList<String> myfriendsemail=new ArrayList<String>();
	 for(Friend s:myfriends)
	 {
	 	if(s.getU_ID()==myid)
	 	{
	 	 myfriendsemail.add(userDAO.getUser(s.getFRI_ID()).getEmail());
	 	}
	 	else if(s.getFRI_ID()==myid)
	 	{
	 		System.out.println(userDAO.getUser(s.getU_ID()).getEmail());
	 		
	 		myfriendsemail.add(userDAO.getUser(s.getU_ID()).getEmail());
	 	}
	 }

	 
	 ArrayList<Friend> hisfriends=(ArrayList<Friend>)friendDAO.getAllMyFriend(userid);
	 ArrayList<String> hisfriendsemail=new ArrayList<String>();
	 for(Friend s:hisfriends)
	 {
	 	if(s.getU_ID()==userid)
	 	{
	 		hisfriendsemail.add(userDAO.getUser(s.getFRI_ID()).getEmail());
	 	}
	 	else if(s.getFRI_ID()==userid)
	 	{
	 		hisfriendsemail.add(userDAO.getUser(s.getU_ID()).getEmail());
	 	}
	 }

	 for(String hs:hisfriendsemail)
	 {
		 Users u=userDAO.getUserbyId(myid);
		if(hs==u.getEmail())
		{
			
		}
		else
		{
		 int count=0;
		 
		 for(String mf:myfriendsemail)
		 {
			 
			 if(mf!=hs)
			 {
				 count++;
			 }
			 
		 }
		 
		 if(count==myfriendsemail.size())
		 {
			 Users us=userDAO.getUserbyemail(hs);
			 fp.add(us);
					 
		 }
		}
		 
		 
	 }
	
	 return fp;
	 
}

}