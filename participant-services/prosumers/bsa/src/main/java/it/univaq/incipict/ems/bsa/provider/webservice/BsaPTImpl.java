package it.univaq.incipict.ems.bsa.provider.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.bsa.AlarmInfo;
import it.univaq.incipict.ems.bsa.BsaPT;
import it.univaq.incipict.ems.bsa.BuildingList;
import it.univaq.incipict.ems.bsa.consumer.service.MessageStoringService;
import it.univaq.incipict.ems.bsa.monitor.LoggerCollector;

@Component(value = "BsaPTImpl")
public class BsaPTImpl implements BsaPT {

	Logger logger = LoggerFactory.getLogger(BsaPTImpl.class);

	@Autowired
	private MessageStoringService service;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void secureBuildings(BuildingList request) {

		logger.info("BSA: received secureBuildings request message (InstanceId: " + request.getAlarmCode() + ")");
		service.storeSecureBuildingsRequest(request);
		loggerCollector.collectEnd("Secure Buildings", request.getAlarmCode());
	}

	@Override
	public void notifyAlarming(AlarmInfo request) {

		logger.info("BSA: received notifyAlarming request message (InstanceId: " + request.getAlarmCode() + ")");
		service.storeNotifyAlarmingRequest(request);
		loggerCollector.collectEnd("Notify Alarming", request.getAlarmCode());
	}

}
