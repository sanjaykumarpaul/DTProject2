package com.collaboration.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaboration.dao.BlogCommentDAO;

@Repository("blogCommentDAO")

public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	public BlogCommentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

}
