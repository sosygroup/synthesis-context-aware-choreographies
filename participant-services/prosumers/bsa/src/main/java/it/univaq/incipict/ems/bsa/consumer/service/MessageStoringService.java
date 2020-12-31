package it.univaq.incipict.ems.bsa.consumer.service;

import it.univaq.incipict.ems.bsa.AlarmInfo;
import it.univaq.incipict.ems.bsa.BuildingList;

public interface MessageStoringService {

	public void storeNotifyAlarmingRequest(AlarmInfo alarmInfo);

	public void storeSecureBuildingsRequest(BuildingList buildingList);
}
