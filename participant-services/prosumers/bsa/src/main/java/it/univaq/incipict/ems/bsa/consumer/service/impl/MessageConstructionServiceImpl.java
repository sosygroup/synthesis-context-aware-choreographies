package it.univaq.incipict.ems.bsa.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.bsa.AlarmInfo;
import it.univaq.incipict.ems.bsa.BuildingList;
import it.univaq.incipict.ems.bsa.EmergencyType;
import it.univaq.incipict.ems.bsa.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.bsa.consumer.service.MessageConstructionService;

@Service
public class MessageConstructionServiceImpl implements MessageConstructionService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);   

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public AlarmInfo getAlarmInfo(String instanceId) {

		logger.info("BSA (consumer): retreiving alarmInfo");
		 AlarmInfo alarmInfo = messageRepository.get("alarmInfo", AlarmInfo.class);

		logger.info("BSA (consumer): alarmInfo " + alarmInfo);

		if (alarmInfo==null) {
			alarmInfo = new AlarmInfo(); 
			alarmInfo.setEmergencyType(EmergencyType.GENERALEMERGENCY);
		}

		return alarmInfo;

	}

	@Override
	public BuildingList getBuildingList(String instanceId) {

		logger.info("BSA (consumer): retreiving buildingList");
		 BuildingList buildingList = messageRepository.get("buildingList", BuildingList.class);

		logger.info("BSA (consumer): buildingList " + buildingList);

		if (buildingList==null) {
			buildingList = new BuildingList(); 
		}

		return buildingList;
	}

	
}
