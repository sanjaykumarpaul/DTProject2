package com.collaboration.daoimpl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.dao.ForumDAO;
import com.collaboration.model.Forum;
import com.collaboration.model.ForumComments;
import com.collaboration.model.ForumRequests;

@Repository("forumDAO")

public class ForumDAOImpl implements ForumDAO{
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	public ForumDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	@Transactional

	public boolean addForum(Forum forum) {
		try
		{
		sessionFactory.getCurrentSession().save(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
	
		return false;
	}
	}
	@Transactional

	public boolean updateForum(Forum forum) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);

		return false;
	}
	}
	@Transactional

	public boolean deleteForum(Forum forum) {
		try
		{
		sessionFactory.getCurrentSession().delete(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
	
		return false;
	}
	}
	@Transactional

	public Forum getForum(int forumId) {
		Session session=sessionFactory.openSession();
		Forum forum = (Forum) session.get(Forum.class, forumId);
		session.close();
		return forum;
	
	}
	@Transactional

	public ArrayList<Forum> getAllForum() {
		Session session = sessionFactory.openSession();
		ArrayList<Forum> forumList=(ArrayList<Forum>)session.createQuery("from Forum").list();
		session.close();
		return forumList;
	
	}
	@Transactional

	public ArrayList<ForumRequests> getAllMyForum(int myid) {
		Session session = sessionFactory.openSession();
		ArrayList<ForumRequests> myforums=(ArrayList<ForumRequests>)session.createQuery("from ForumRequests where userid="+myid+" and status='YES'").list();
		session.close();
		return myforums;
		
	}
	@Transactional

	public boolean addForumComment(ForumComments forumcomment) {
try
		{
		sessionFactory.getCurrentSession().save(forumcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		
		return false;
		}
	}
	@Transactional

	public boolean updateForumComment(ForumComments forumcomment) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(forumcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
	
		return false;
		}
	}
	@Transactional

	public boolean deleteForumComment(ForumComments forumcomment) {
		
			try
		{
		sessionFactory.getCurrentSession().delete(forumcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);

		return false;
		}
		
	}
	@Transactional

	public ForumComments getForumComment(int commentId) {
		Session session=sessionFactory.openSession();
		ForumComments forumcomment = (ForumComments) session.get(ForumComments.class, commentId);
		session.close();
		return forumcomment;

	}
	@Transactional

	public ArrayList<ForumComments> getAllForumCommentsById(int forumid) {
Session ssn=sessionFactory.openSession();
		
		
		org.hibernate.Query q= ssn.createQuery("from ForumComments where forumid="+forumid);
		ArrayList<ForumComments> l=(ArrayList<ForumComments>) q.list();
		
        
        ssn.close();


		
		return l;
		
	}
	@Transactional

	public boolean addForumRequest(ForumRequests forumrequest) {
		try
		{
			sessionFactory.getCurrentSession().save(forumrequest);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		
		return false;
		}
	}
	@Transactional

	public boolean acceptForumRequest(ForumRequests forumrequest) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(forumrequest);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		
		return false;
	}
	}
	@Transactional

	public boolean blockUser(ForumRequests forumrequest) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(forumrequest);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);

		return false;
		}
	}
	@Transactional

	public ArrayList<ForumRequests> getAllForumRequest() {
		Session session = sessionFactory.openSession();
		ArrayList<ForumRequests> forumReqList=(ArrayList<ForumRequests>)session.createQuery("from ForumRequests where status='A'").list();
		session.close();
		return forumReqList;

	}
	@Transactional

	public ForumRequests getForumRequest(int ForumReqId) {
		Session session=sessionFactory.openSession();
		ForumRequests forumReq = (ForumRequests) session.get(ForumRequests.class, ForumReqId);
		session.close();
		return forumReq;
		
	}
	@Transactional

	public ArrayList<ForumRequests> checkIfMyForum(int ForumId, int myid) {
		Session session = sessionFactory.openSession();
		ArrayList<ForumRequests> myforums=(ArrayList<ForumRequests>)session.createQuery("from ForumRequests where userid="+myid+" and forumid="+ForumId).list();
		session.close();
		return myforums;

	}
	@Transactional

	public ArrayList<ForumRequests> forreqbyforid(int forumid) {
		Session session = sessionFactory.openSession();
		ArrayList<ForumRequests> forumsbyforid=(ArrayList<ForumRequests>)session.createQuery("from ForumRequests where forumid="+forumid+" and status='YES'").list();
		session.close();
		return forumsbyforid;
		
	
	}
	@Transactional

	public ArrayList<ForumRequests> getAllForumRequestAll(int forumid) {
		Session session = sessionFactory.openSession();
		ArrayList<ForumRequests> forumReqList=(ArrayList<ForumRequests>)session.createQuery("from ForumRequests where forumid="+forumid).list();
		session.close();
		return forumReqList;

	}
	@Transactional

	public boolean deleteForumRequest(ForumRequests forumreq) {
		
			try
		{
		sessionFactory.getCurrentSession().delete(forumreq);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		
		return false;
	}

	
}
	@Transactional
	public boolean rejectForumRequest(ForumRequests forumrequest) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(forumrequest);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);

		return false;
		}
	}
	public ForumRequests myforreq(String email, int forumid) {
		Session session = sessionFactory.openSession();
		ForumRequests forumsreme=(ForumRequests)session.createQuery("from ForumRequests where forumid="+forumid+" and username='"+email+"'").list().get(0);
		return forumsreme;
	}
}