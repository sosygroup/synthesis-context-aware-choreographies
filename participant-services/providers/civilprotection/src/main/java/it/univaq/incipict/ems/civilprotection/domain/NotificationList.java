package it.univaq.incipict.ems.civilprotection.domain;

import java.util.ArrayList;
import java.util.List;

import it.univaq.incipict.ems.civilprotection.Notification;

public class NotificationList {

	private List<Notification> notifications;

	public List<Notification> getNotifications() {

		if (notifications == null) {
			notifications = new ArrayList<>();
		}
		return notifications;
	}

}
