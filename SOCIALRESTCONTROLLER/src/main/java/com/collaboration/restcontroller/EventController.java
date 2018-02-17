package com.collaboration.restcontroller;

import java.util.ArrayList;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.EventDAO;
import com.collaboration.dao.NotificationsDAO;
import com.collaboration.dao.UsersDAO;
import com.collaboration.model.EventParticipants;
import com.collaboration.model.Events;
import com.collaboration.model.Notifications;
import com.collaboration.model.Users;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired 
	EventDAO eventDAO;
	
	@Autowired 
	NotificationsDAO notificationsDAO;

@Autowired
UsersDAO userDAO;
	@RequestMapping(value="/addEvent",method=RequestMethod.POST)
	public ResponseEntity<String> addEvent(@RequestBody Events events,HttpSession session){
		 Users user = (Users)session.getAttribute("currentuser");
		 System.out.println(user.getEmail());
		if(user.getRole().equals("ROLE_ADMIN"))
		{
			System.out.println("admin event");;
			events.setStatus("A");
		}
		Events eve =eventDAO.addEvent(events);
		
		
		EventParticipants eventparticipants=new EventParticipants();
		eventparticipants.setEventid(eve.getEventid());
		eventparticipants.setUserid(user.getUserid());
		boolean isSaved=eventDAO.applyevent(eventparticipants);
		
			return new ResponseEntity<String>("events added applied  successfully",HttpStatus.OK);
		
	
		
	}
	
	
	@RequestMapping(value="/getEvent/{eventid}",method=RequestMethod.GET,headers = "Accept=application/json")
	public ResponseEntity<Events> getEvent(@PathVariable("eventid") int eventid){

	System.out.println("In get Event controller"+eventid);
	if(eventDAO.getevent(eventid)==null){
		return new ResponseEntity<Events>(eventDAO.getevent(eventid),HttpStatus.BAD_REQUEST);	
		
	}
	else
	{
		return new ResponseEntity<Events>(eventDAO.getevent(eventid),HttpStatus.OK);	
	}


	}
	@RequestMapping(value="/getAllEvents",method=RequestMethod.GET,headers = "Accept=application/json")
	public ArrayList<Events> getAllEvents(){
		ArrayList<Events> events=(ArrayList<Events>)eventDAO.getAllevents();
		if(events.isEmpty()){
			return null;
		}
		else
		{
			return events;	
		}
		
				
	}
	
	
	@RequestMapping(value="/getAlleventreq",method=RequestMethod.GET)
	public ArrayList<Events> getAllEventreq(){
	 System.out.println("in rest controller getalleventsreq");
		ArrayList<Events> eventreq=(ArrayList<Events>)eventDAO.eventrequests();
		System.out.println("in rest controller getalleventsreq");

	return eventreq;		
	} 
 
 @RequestMapping(value="/approveevents/{eventid}",method=RequestMethod.GET)
 public void approveusers(@PathVariable("eventid") int eventid)
 {
	 Events event=eventDAO.getevent(eventid);
	 event.setStatus("A");
	
	 eventDAO.approveevent(event);
	 
	 
	 String noti="your event:"+event.eventname+" is approved";
	 Notifications not=new Notifications();
	 not.setName(noti);
	 not.setUsername(event.getUsername());
	 
	 notificationsDAO.addNotifications(not);
	 
 }
 
 
 @RequestMapping(value="/rejectevents/{eventid}",method=RequestMethod.GET)
 public void rejectusers(@PathVariable("eventid") int eventid)
 {
	 Events event=eventDAO.getevent(eventid);
	 event.setStatus("R");
	
	 eventDAO.rejectevent(event);
	 
	 String noti="your event:"+event.eventname+" is rejected";
	 Notifications not=new Notifications();
	 not.setName(noti);
	 not.setUsername(event.getUsername());
	 
	 notificationsDAO.addNotifications(not);
	 
 }
 
 	
	
	
	
	@RequestMapping(value="/deleteEvent/{eventid}",method=RequestMethod.GET)
	public ResponseEntity<String> deleteBlog(@PathVariable("eventid") int eventid){

	Events events=eventDAO.getevent(eventid);
	if(eventDAO.deleteEvent(events))
	{ArrayList<EventParticipants> evp=eventDAO.eventpars(eventid);
	for(EventParticipants evpp:evp)
	{
		eventDAO.deleteEventpars(evpp);
	}
		return new ResponseEntity<String>("Event deleted successfully",HttpStatus.OK);	
	}
	return new ResponseEntity<String>("Event deletion error",HttpStatus.BAD_REQUEST);	
			



	}
	@RequestMapping(value="/updateEvent/{eventid}/{eventname}/{eventesc}/{eventvenue}",method=RequestMethod.GET)
	public ResponseEntity<String> updateEvents(HttpSession session,@PathVariable("eventid") int eventid,@PathVariable("eventname") String eventname,@PathVariable("eventesc") String eventesc,@PathVariable("eventvenue") String eventvenue){
	
		Events event=eventDAO.getevent(eventid);
		
		event.setStatus("P");
		event.setEventname(eventname);
		
	
		event.setEventdesc(eventesc);
		event.setEventvenue(eventvenue);
	
		boolean isSaved=eventDAO.updateEvent(event);
		if(isSaved)
		return new ResponseEntity<String>("event update succcess",HttpStatus.OK);
		else
			return new ResponseEntity<String>("event update failure",HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@RequestMapping(value="/applyEvent/{Eventid}/{myid}",method=RequestMethod.GET)
	public ResponseEntity<String> applyJob(@PathVariable("Eventid") int eventid,@PathVariable("myid") int myid)
	{
		EventParticipants eventparticipants=new EventParticipants();
		eventparticipants.setEventid(eventid);
		eventparticipants.setUserid(myid);
		boolean isSaved=eventDAO.applyevent(eventparticipants);
		if(isSaved)
		{
			return new ResponseEntity<String>("events applied successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("event apply failed",HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="/myEvents/{myid}",method=RequestMethod.GET)
	public ArrayList<Events> myevents(@PathVariable("myid") int myid)
	{
		ArrayList<Events> myevents=new ArrayList<Events>();
		ArrayList<EventParticipants> evenpars =eventDAO.myevents(myid);
		for(EventParticipants eventpa:evenpars)
		{
			
			myevents.add(eventDAO.getevent(eventpa.getEventid()));
			
		}
		for(Events e:myevents)
		{
			System.out.println(e.getEventname());
		}
		return myevents;
	}
	
	
	@RequestMapping(value="/checkifappliedevent/{eventid}/{myid}",method=RequestMethod.GET)
	public ArrayList<EventParticipants> checkifapplied(@PathVariable("eventid") int eventid,@PathVariable("myid") int myid)
	{
		return eventDAO.checkIfeveAppliied(eventid, myid);
	}

	@RequestMapping(value="/eventparts/{eventid}",method=RequestMethod.GET)
	public ArrayList<Users> jobapps(@PathVariable("eventid") int eventid)
	{ArrayList<Users> u=new ArrayList<Users>();
		ArrayList<EventParticipants> evepars=eventDAO.eventpars(eventid);
		for(EventParticipants e:evepars)
		{
			Users us=userDAO.getUser(e.getUserid());
			u.add(us);
		}
		return u;
	}



}