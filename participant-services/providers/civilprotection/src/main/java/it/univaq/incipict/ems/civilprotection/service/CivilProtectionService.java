package it.univaq.incipict.ems.civilprotection.service;

import java.util.List;

import it.univaq.incipict.ems.civilprotection.BuildingDataList;
import it.univaq.incipict.ems.civilprotection.InterventionPlan;
import it.univaq.incipict.ems.civilprotection.Notification;
import it.univaq.incipict.ems.civilprotection.domain.Building;
import it.univaq.incipict.ems.civilprotection.domain.BuildingInfo;

public interface CivilProtectionService {

	public void storeBuildingData(BuildingDataList buildingDataList);

	public void storeInterventionPlan(InterventionPlan interventionPlan);

	public void updateInterventionPlan(InterventionPlan interventionPlanUpdate);

	public void storeNotification(Notification notification);

	public InterventionPlan getInterventionPlan();

	public List<BuildingInfo> getBuildingData();

	public Building getBuilding(String buildingId);

	public List<Notification> getNotifications();

}
