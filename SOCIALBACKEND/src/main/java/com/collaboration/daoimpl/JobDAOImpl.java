package com.collaboration.daoimpl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.dao.JobDAO;
import com.collaboration.model.Job;
import com.collaboration.model.JobApplications;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO{
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	public JobDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean addjob(Job job) {
		try
		{
		sessionFactory.getCurrentSession().save(job);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
	
		return false;
		}
	}
	@Transactional
	public boolean updatejob(Job job) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(job);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
	
		return false;
		}
	}
	@Transactional
	public boolean deletejob(Job job) {
		try
		{
		sessionFactory.getCurrentSession().delete(job);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
	
		return false;
		}
	}
	@Transactional
	public Job getjob(int jobId) {
		Session session=sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, jobId);
		session.close();
		return job;
		
		
	}
	@Transactional
	public ArrayList<Job> getAlljobs() {
		Session session = sessionFactory.openSession();
		ArrayList<Job> jobList=(ArrayList<Job>)session.createQuery("from Job").list();
		session.close();
		return jobList;
		
		
	}
	@Transactional
	public boolean applyJob(JobApplications jobapplications) {
		try
		{
		sessionFactory.getCurrentSession().save(jobapplications);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
	
		return false;
		}
	}
	@Transactional
	public ArrayList<JobApplications> myjobs(int myid) {
		System.err.println(myid);
		Session session = sessionFactory.openSession();
		ArrayList<JobApplications> jobapllicationlist=(ArrayList<JobApplications>)session.createQuery("from JobApplications where userid="+myid).list();
		session.close();
		return jobapllicationlist;

	
	}
	@Transactional
	public ArrayList<JobApplications> checkIfApplied(int jobid, int myid) {
		Session session = sessionFactory.openSession();
		ArrayList<JobApplications> checkifapplied=(ArrayList<JobApplications>)session.createQuery("from JobApplications where userid="+myid+" and jobid="+jobid).list();
	session.close();
	return checkifapplied;

		
	}
	@Transactional
	public ArrayList<JobApplications> jobapps(int jobid) {
		
		Session session = sessionFactory.openSession();
		ArrayList<JobApplications> jobapps=(ArrayList<JobApplications>)session.createQuery("from JobApplications where  jobid="+jobid).list();
		session.close();
		return jobapps;
		
		
	}
	@Transactional
	public boolean deletejobApp(JobApplications jobapplication) {

		try
		{
		sessionFactory.getCurrentSession().delete(jobapplication);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		
		return false;
	}
	}
	

}
