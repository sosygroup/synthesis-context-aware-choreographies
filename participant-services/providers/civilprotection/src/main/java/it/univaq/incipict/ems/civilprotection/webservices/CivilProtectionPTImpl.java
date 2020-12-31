package it.univaq.incipict.ems.civilprotection.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.civilprotection.BuildingDataList;
import it.univaq.incipict.ems.civilprotection.CivilProtectionPT;
import it.univaq.incipict.ems.civilprotection.InterventionPlan;
import it.univaq.incipict.ems.civilprotection.Notification;
import it.univaq.incipict.ems.civilprotection.monitor.LoggerCollector;
import it.univaq.incipict.ems.civilprotection.service.CivilProtectionService;


@Component(value = "CivilProtectionPTImpl")
public class CivilProtectionPTImpl implements CivilProtectionPT {

	private static Logger logger = LoggerFactory.getLogger(CivilProtectionPTImpl.class);

	@Autowired
	private CivilProtectionService service;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void sendBuildingData(BuildingDataList request) {

		logger.info("CivilProtection: received sendBuildingData request message (instanceId " + request.getAlarmCode() + ")");
		service.storeBuildingData(request);
		loggerCollector.collectEnd("Send Building Data", request.getAlarmCode());
	}

	@Override
	public void sendInterventionPlan(InterventionPlan request) {

		logger.info("CivilProtection: received sendInterventionPlan request message (instanceId " + request.getAlarmCode() + ")");
		service.storeInterventionPlan(request);
		loggerCollector.collectEnd("Send Intervention Plan", request.getAlarmCode());
	}

	@Override
	public void updateInterventionPlan(InterventionPlan request) {

		logger.info("CivilProtection: received updateInterventionPlan request message  (instanceId " + request.getAlarmCode() + ")");
		service.storeInterventionPlan(request);
		loggerCollector.collectEnd("Update Intervention Plan", request.getAlarmCode());
	}

	@Override
	public void sendNotification(Notification request) {

		logger.info("CivilProtection: received sendNotification request message  (instanceId " + request.getAlarmCode() + ")");
		service.storeNotification(request);
		loggerCollector.collectEnd("Send Notification", request.getAlarmCode());
	}

}
