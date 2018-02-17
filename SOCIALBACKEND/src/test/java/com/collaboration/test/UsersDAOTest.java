package com.collaboration.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaboration.dao.UsersDAO;
import com.collaboration.model.Users;

public class UsersDAOTest {
	@Autowired
	public static UsersDAO userDAO;
		
		
		@BeforeClass
		public static void initialize()
		{
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			context.scan("com.collaboration");
			context.refresh();
			
			userDAO=(UsersDAO)context.getBean("userDAO");
		}
		
	@Ignore
		@Test
		public void getAllUsersTest()
		{
			ArrayList<Users> users= userDAO.getAllUser();
			for(Users u:users)
			{
				System.out.println(u.getFirstname());
				System.out.println(u.getLastname());
				System.out.println(u.getPassword());
			
			
		}
		}
	@Ignore
			@Test
			public void addUserTest()
			{
				Users users =new Users();
				users.setFirstname("plavin ");
				users.setLastname("paul");
				users.setEmail("palvin@gmail.com");
				users.setIsonline("N");
				users.setPassword("palvin1212");
				users.setRole("ROLE_USER");
				
				users.setUserid(1236);
				
				
			
				assertTrue("Problem in Inserting USer",userDAO.saveUser(users));
				
				
				
				
			}
			
			@Ignore
			@Test
			public void getUserTest()
			{
				
				Users users=(Users)userDAO.getUser(1);
				System.out.println(users.getEmail());
				
			}
			
		
			@Test
			public void getUserByEmailTest()
			{
				
				Users users=(Users)userDAO.getUserbyemail("j@gmail.com");
				System.out.println(users.getFirstname());
				
			}
			
			
			@Ignore
			@Test
			public void updateOnlineStatusTest()

			{
				Users users=userDAO.getUser(2);
				
				assertTrue("Problem in updating Online Status",userDAO.updateOnlineStatus(users));
				
				
			}
			@Ignore
			@Test
			public void checklogin()
			{
				Users user=(Users)userDAO.getUser(6);
				System.out.println(user.getEmail());
				
				assertTrue("Problem in login Status",userDAO.checkLogin(user));
				
			}
			@Ignore
			@Test
			public void getuserbyemail()
			{
				Users user=(Users)userDAO.getUserbyId(1);
				System.out.println(user.getEmail());
				
				
				
			}

	}

