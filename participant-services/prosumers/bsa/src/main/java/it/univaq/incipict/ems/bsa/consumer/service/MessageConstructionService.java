package it.univaq.incipict.ems.bsa.consumer.service;

import it.univaq.incipict.ems.bsa.AlarmInfo;
import it.univaq.incipict.ems.bsa.BuildingList;

public interface MessageConstructionService {

	AlarmInfo getAlarmInfo(String instanceId);

	BuildingList getBuildingList(String instanceId);
}
