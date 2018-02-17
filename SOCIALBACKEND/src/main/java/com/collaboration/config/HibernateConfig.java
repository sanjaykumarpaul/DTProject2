package com.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.collaboration.dao.BlogCommentDAO;
import com.collaboration.dao.BlogDAO;
import com.collaboration.dao.EventDAO;
import com.collaboration.dao.ForumCommentDAO;
import com.collaboration.dao.ForumDAO;
import com.collaboration.dao.FriendDAO;
import com.collaboration.dao.JobDAO;
import com.collaboration.dao.NotificationsDAO;
import com.collaboration.dao.UsersDAO;
import com.collaboration.daoimpl.BlogCommentDAOImpl;
import com.collaboration.daoimpl.BlogDAOImpl;
import com.collaboration.daoimpl.EventDAOImpl;
import com.collaboration.daoimpl.ForumCommentDAOImpl;
import com.collaboration.daoimpl.ForumDAOImpl;
import com.collaboration.daoimpl.FriendDAOImpl;
import com.collaboration.daoimpl.JobDAOImpl;
import com.collaboration.daoimpl.NotificationsDAOImpl;
import com.collaboration.daoimpl.UsersDAOImpl;
import com.collaboration.model.Blog;
import com.collaboration.model.BlogComments;
import com.collaboration.model.EventParticipants;
import com.collaboration.model.Events;
import com.collaboration.model.Forum;
import com.collaboration.model.ForumComments;
import com.collaboration.model.ForumRequests;
import com.collaboration.model.Friend;
import com.collaboration.model.Job;
import com.collaboration.model.JobApplications;
import com.collaboration.model.Notifications;
import com.collaboration.model.Users;

@Configuration
@ComponentScan("com.collaboration")
@EnableTransactionManagement
public class HibernateConfig 
{
	@Autowired
	    @Bean(name="sessionFactory")
	    public SessionFactory sessionFactory(DataSource dataSource)  {
	        LocalSessionFactoryBuilder sessionBuilder  = new LocalSessionFactoryBuilder(dataSource);

	        /*sessionBuilder.setProperty("hibernate.show_sql", "true");*/
	        
	        sessionBuilder.addProperties(getHibernateProperties());
	     
	       sessionBuilder.addAnnotatedClass(Users.class);
	        sessionBuilder.addAnnotatedClass(Blog.class);
	       sessionBuilder.addAnnotatedClass(BlogComments.class);
	        sessionBuilder.addAnnotatedClass(Forum.class);
	        sessionBuilder.addAnnotatedClass(ForumComments.class);
	        sessionBuilder.addAnnotatedClass(Friend.class);
	        sessionBuilder.addAnnotatedClass(Job.class);
	        sessionBuilder.addAnnotatedClass(ForumRequests.class);
	        sessionBuilder.addAnnotatedClass(JobApplications.class);
	        sessionBuilder.addAnnotatedClass(Events.class); 
	        sessionBuilder.addAnnotatedClass(EventParticipants.class);
	        sessionBuilder.addAnnotatedClass(Notifications.class);
	        
	       
	        
	       
	        
	        return sessionBuilder.buildSessionFactory();
	    }
	 @Autowired
	    @Bean(name = "datasource") 
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.h2.Driver");
	        dataSource.setUrl("jdbc:h2:tcp://localhost/~/ecomdb");

	        dataSource.setUsername("sa");
	        dataSource.setPassword("");
	        System.out.println("Data Source Created.....");
	        return dataSource;

	       
	        }

	    private Properties getHibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.show_sql", "true");
	        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	  
	        properties.put("hibernate.format_sql", "true");
	        properties.put("hibernate.hbm2ddl.auto", "update");
	        properties.put("hibernate.connection.autocommit", true);
	        return properties;
	    }

	    
	    
	    @Autowired
	    @Bean(name="transactionManager")
	     
	        public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
	                return new HibernateTransactionManager(sessionFactory);
	        }
	        
	   @Autowired    
	    @Bean(name="blogDAO")
		public BlogDAO getBlogDAO(SessionFactory sessionFactory)
		{
			System.out.println("Blog DAO object Created");
			return new BlogDAOImpl(sessionFactory);
		}
	    
	    @Autowired    
	    @Bean(name="userDAO")
		public UsersDAO getUserDAO(SessionFactory sessionFactory)
		{
			System.out.println("User DAO object Created");
			return new UsersDAOImpl(sessionFactory);
		}
	    @Autowired    
	    @Bean(name="jobDAO")
		public JobDAO getJobDAO(SessionFactory sessionFactory)
		{
			System.out.println("Job DAO object Created");
			return new JobDAOImpl(sessionFactory);
		}
	    @Autowired    
	    @Bean(name="forumDAO")
		public ForumDAO getForumDAO(SessionFactory sessionFactory)
		{
			System.out.println("Forum DAO object Created");
			return new ForumDAOImpl(sessionFactory);
		}
	    
	    @Autowired    
	    @Bean(name="forumCommentDAO")
		public ForumCommentDAO getForumCommentDAO(SessionFactory sessionFactory)
		{
			System.out.println("ForumComment DAO object Created");
			return new ForumCommentDAOImpl(sessionFactory);
		}
	    
	    @Autowired    
	    @Bean(name="blogCommentDAO")
	    public BlogCommentDAO getBlogCommentDAO(SessionFactory sessionFactory)
		{
			System.out.println("BlogComment DAO object Created");
			return new BlogCommentDAOImpl(sessionFactory);
		}
	    
	    
	    @Autowired    
	    @Bean(name="friendDAO")
	    public FriendDAO getFriendDAO(SessionFactory sessionFactory)
		{
			System.out.println("BlogComment DAO object Created");
			return new FriendDAOImpl(sessionFactory);
		}
	    
	    @Autowired    
	    @Bean(name="eventDAO")
	    public EventDAO getEventDAO(SessionFactory sessionFactory)
		{
			System.out.println("Events DAO object Created");
			return new EventDAOImpl(sessionFactory);
		}
	    
	    @Autowired    
	    @Bean(name="NotificationsDAO")
	    public NotificationsDAO getNotificationsDAO(SessionFactory sessionFactory)
		{
			System.out.println("Events DAO object Created");
			return new NotificationsDAOImpl(sessionFactory);
		}
	 	 
	 	 
	 

}