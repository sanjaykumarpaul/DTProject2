package com.collaboration.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaboration.dao.ForumDAO;
import com.collaboration.dao.UsersDAO;
import com.collaboration.model.Forum;
import com.collaboration.model.ForumComments;
import com.collaboration.model.ForumRequests;

public class ForumDAOTest {
	@Autowired
	private static ForumDAO forumDAO;
	@Autowired
	private static UsersDAO userDAO;
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.collaboration");
		context.refresh();
		
		forumDAO=(ForumDAO)context.getBean("forumDAO");
		userDAO=(UsersDAO)context.getBean("userDAO");
	}
@Ignore
	@Test
	public void addForumTest()
	{
		Forum forum=new Forum();
		
		
		forum.setFormcontent("Ruby on raisl");
		forum.setFormname("fgvfdhb");
		
		
		
		assertTrue("Problem in adding Forum  ",forumDAO.addForum(forum));
	
	
	}
@Ignore
	@Test
	public void getForumTest()
	{
		
		Forum forum=forumDAO.getForum(1);
		System.out.println(forum.getFormcontent());	
		System.out.println(forum.getFormname());
	}


@Ignore
	@Test
	public void updateForumTest()
	{
		
	Forum forum=forumDAO.getForum(1);
	forum.setFormcontent("Kotlin or java");
	forum.setFormname("Android tech");
		assertTrue("Problem in Updading forum",forumDAO.updateForum(forum));
		
		
	}
	
@Ignore
	@Test
	public void deleteForumTest()
	{
		Forum forum=forumDAO.getForum(1);
		assertTrue("Problem in deleting forum",forumDAO.deleteForum(forum));
	}
@Ignore
	@Test
	public void getAllForumTest()
	{
		
	
		ArrayList<Forum> forum=(ArrayList<Forum>)forumDAO.getAllForum();
	for(Forum f:forum)
	{
		System.out.println(f.getFormname());
	}
	
	}
	
@Ignore
	@Test
	public void addForumComment()
	{
		
		ForumComments forumcomment =new ForumComments();
		forumcomment.setForumcomm("nicely written");
		forumcomment.setForumid(2);
		
		
		assertTrue("Problem in adding Forumcomment ",forumDAO.addForumComment(forumcomment));
		
	}
	
	
	
@Ignore
	@Test
	public void getForumCommentTest()
	{
		
		ForumComments forumcomment=forumDAO.getForumComment(2);
		System.out.println(forumcomment.getForumcomm());
		
	}
	
@Ignore
@Test
public void getForumCommentByForumidTest()
{
	
	ArrayList<ForumComments> forumcomment=(ArrayList<ForumComments>)forumDAO.getAllForumCommentsById(2);
	for(ForumComments f:forumcomment)
		
	{
		System.out.println(f.getForumcomm());
	}
	
}
@Ignore
	
	@Test
	public void updateForumCommentTest()
	{
		ForumComments forumcomment =forumDAO.getForumComment(2);
		forumcomment.setForumcomm("Too good");
		assertTrue("Problem in updating Forumcomment ",forumDAO.updateForumComment(forumcomment));
		
		
	}
	
	
@Ignore
	@Test
	public void deleteForumCommentTest()
	{
		ForumComments forumcomment=forumDAO.getForumComment(4);
		assertTrue("Problem in deleting Forumcomment ",forumDAO.deleteForumComment(forumcomment));
		
	}

@Ignore
@Test
public void addForumRequestTest()
{
	ForumRequests forumrequest=new ForumRequests();


forumrequest.setForumid(1);
forumrequest.setUserid(1);
assertTrue("Problem in inserting forumreq ",forumDAO.addForumRequest(forumrequest));
	
	
}


@Ignore
@Test
public void acceptForumRequestTest()
{
ForumRequests forumrequest=forumDAO.getForumRequest(1);
forumrequest.setStatus("YES");
assertTrue("Problem in  forumreq ",forumDAO.acceptForumRequest(forumrequest));
}

@Ignore
@Test
public void blockUserTest()
{
	ForumRequests forumrequest=forumDAO.getForumRequest(1);
	forumrequest.setStatus("NO");
	assertTrue("Problem in  forumreq ",forumDAO.blockUser(forumrequest));	
	
}


@Ignore
@Test
public void getForumRequestTest()
{
	ForumRequests forumrequest=forumDAO.getForumRequest(1);
	System.out.println(forumrequest.getForumid());
	
}

@Ignore
@Test
public void getAllForumRequestTest()
{
	ArrayList<ForumRequests> forumrequests=forumDAO.getAllForumRequest();
	for(ForumRequests f:forumrequests)
	{
		System.out.println(f.getForreqid());
	}
}
}
