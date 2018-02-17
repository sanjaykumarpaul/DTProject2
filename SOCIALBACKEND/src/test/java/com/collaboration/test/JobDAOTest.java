package com.collaboration.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaboration.dao.JobDAO;
import com.collaboration.model.Job;

public class JobDAOTest {
	@Autowired
	private static  JobDAO jobDAO;
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.collaboration");
		context.refresh();
		
		jobDAO=(JobDAO)context.getBean("jobDAO");
	}
	@Ignore
	@Test
	public void addjob()
	{
		Job job=new Job();
		job.setJobdesc("Devops");
		job.setJobid(8523);
		job.setJobprofile("Web App developer");
		
		assertTrue("Problem in inserting job",jobDAO.addjob(job));		
	}
@Ignore
	@Test
	public void getJob()
	{
		Job job=jobDAO.getjob(67);
		System.out.println(job.getJobdesc());
		
		
	}
	
@Ignore
	@Test
	public void getAllJobs()
	{
		
		ArrayList<Job> job=(ArrayList<Job>)jobDAO.getAlljobs();
		for(Job j:job)
		{
			System.out.println(j.getJobdesc());
			
		}
		
	}
@Ignore
@Test
public void updateJob()
{

	Job job=jobDAO.getjob(67);
	job.setJobdesc("software developer");
	assertTrue("problem in updating Job",jobDAO.updatejob(job));
	
}
@Ignore
	@Test
	public void deletejob()
	{
		
		Job job=jobDAO.getjob(67);
		assertTrue("problem in deleting Job",jobDAO.deletejob(job));
	}
}

