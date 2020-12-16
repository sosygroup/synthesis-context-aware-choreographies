package it.univaq.incipict.ems.bsa.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.bsa.AlarmInfo;
import it.univaq.incipict.ems.bsa.BuildingList;
import it.univaq.incipict.ems.bsa.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.bsa.consumer.service.MessageStoringService;

@Service
public class MessageStoringServiceImpl implements MessageStoringService {

	Logger logger = LoggerFactory.getLogger(MessageStoringServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void storeNotifyAlarmingRequest(AlarmInfo alarmInfo) {

		messageRepository.store("alarmInfo", alarmInfo);

	}

	@Override
	public void storeSecureBuildingsRequest(BuildingList buildingList) {

		messageRepository.store("buildingList", buildingList);

	}

}
