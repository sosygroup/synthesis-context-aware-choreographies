package it.univaq.incipict.ems.sae.consumer.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.sae.ActivationRequest;
import it.univaq.incipict.ems.sae.AlarmInfo;
import it.univaq.incipict.ems.sae.EarlyWarning;
import it.univaq.incipict.ems.sae.EmptyResponse;
import it.univaq.incipict.ems.sae.InstanceInfo;
import it.univaq.incipict.ems.sae.SaeConsumerPT;
import it.univaq.incipict.ems.sae.SendPlanResponse;
import it.univaq.incipict.ems.sae.consumer.service.MessageConstructionService;
import it.univaq.incipict.ems.sae.consumer.service.MessageStoringService;
import it.univaq.incipict.ems.sae.monitor.LoggerCollector;

@Component(value = "SaeConsumerPTImpl")
public class SaeConsumerPTImpl implements SaeConsumerPT {

	private static Logger logger = LoggerFactory.getLogger(SaeConsumerPTImpl.class);

	@Autowired
	private MessageStoringService messageStoringService;

	@Autowired
	private MessageConstructionService messageConstructionService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public AlarmInfo getActivateAlarmMessage(InstanceInfo request) {

		loggerCollector.collectStart("Activate Alarm", request.getInstanceId());
		logger.info("SAE (consumer): received request to get activateAlarm message (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getAlarmInfo();
	}

	@Override
	public EmptyResponse storeSignalEventRequest(EarlyWarning message) {

		logger.info("SAE (consumer): received signalEvent request message to store (instanceId " + message.getEventCode() + ")");
		messageStoringService.storeSignalEventRequest(message);
		return new EmptyResponse();
	}

	@Override
	public AlarmInfo getNotifyAlarmingMessage(InstanceInfo request) {

		loggerCollector.collectStart("Notify Alarming", request.getInstanceId());
		logger.info("SAE (consumer): received request to get notifyAlarming message (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getAlarmInfo();
	}

	@Override
	public AlarmInfo getSendPlanMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Plan", request.getInstanceId());
		logger.info("SAE (consumer): received request to get sendPlan message (instanceId " + request.getInstanceId() + ")");

		return messageConstructionService.getAlarmInfo();
	}

	@Override
	public EmptyResponse storeSendPlanResponse(SendPlanResponse message) {

		logger.info("SAE (consumer): received sendPlan response message to store (instanceId " + message.getAlarmCode() + ")");
		messageStoringService.storeSendPlanResponse(message);
		loggerCollector.collectEnd("Send Plan", message.getAlarmCode());
		return new EmptyResponse();
	}

	@Override
	public ActivationRequest getActivatePanelsMessage(InstanceInfo request) {

		loggerCollector.collectStart("Activate Panels", request.getInstanceId());
		logger.info("SAE (consumer): received request to get activatePanels message (instanceId " + request.getInstanceId() + ")");

		return messageConstructionService.getActivatePanelsRequest(request.getInstanceId());
	}
}
