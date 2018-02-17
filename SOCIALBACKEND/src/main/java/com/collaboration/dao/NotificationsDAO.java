package com.collaboration.dao;

import java.util.ArrayList;

import com.collaboration.model.Notifications;

public interface NotificationsDAO {
	public boolean addNotifications(Notifications notification) ;
	public ArrayList<Notifications> getAllNotifications( String username) ;
	public boolean deleteNotifications(Notifications notification);
	public Notifications getNotifications(int notifid);
}
