package it.univaq.incipict.ems.smartevacuation.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.smartevacuation.ActivationRequest;
import it.univaq.incipict.ems.smartevacuation.AlarmInfo;
import it.univaq.incipict.ems.smartevacuation.SendPlanResponse;
import it.univaq.incipict.ems.smartevacuation.SmartevacuationPT;
import it.univaq.incipict.ems.smartevacuation.monitor.LoggerCollector;
import it.univaq.incipict.ems.smartevacuation.service.SmartEvacuationService;


@Component(value = "SmartEvacuationPTImpl")
public class SmartevacuationPTImpl implements SmartevacuationPT {

	private static Logger logger = LoggerFactory.getLogger(SmartevacuationPTImpl.class);

	@Autowired
	private SmartEvacuationService smartEvacuationService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void activatePanels(ActivationRequest parameters) {

		logger.info("SmartEvacuation: received activatePanels request message (InstanceId " + parameters.getAlarmCode() + ")");
		loggerCollector.collectEnd("Activate Panels", parameters.getAlarmCode());
	}

	@Override
	public SendPlanResponse sendPlan(AlarmInfo parameters) {

		logger.info("SmartEvacuation: received sendPlan request message (InstanceId " + parameters.getAlarmCode() + ")");

		SendPlanResponse response = new SendPlanResponse();
		response.setAlarmCode(parameters.getAlarmCode());
		response.setEvacuationId(smartEvacuationService.sendEvacuationPlan(parameters.getEmergencyType(), parameters.getAreaCode()));

		logger.info("SmartEvacuation: replied to sendPlan with evacuationID " + response.getEvacuationId());
//		loggerCollector.collectEnd("Send Plan", parameters.getAlarmCode());
		return response;
	}

}
