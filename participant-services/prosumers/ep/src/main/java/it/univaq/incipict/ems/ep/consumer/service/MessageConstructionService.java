package it.univaq.incipict.ems.ep.consumer.service;

import java.util.List;

import it.univaq.incipict.ems.ep.BuildingDataList;
import it.univaq.incipict.ems.ep.InterventionPlan;
import it.univaq.incipict.ems.ep.Notification;

public interface MessageConstructionService {

	List<String> getAreaCodes();

	InterventionPlan getInterventionPlan();

	BuildingDataList getBuildingDataList();

	Notification getNotificationMessage();

	InterventionPlan getInterventionPlanUpdate();
}
