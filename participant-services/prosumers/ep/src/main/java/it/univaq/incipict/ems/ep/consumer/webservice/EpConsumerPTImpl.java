package it.univaq.incipict.ems.ep.consumer.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.ep.BuildingDataList;
import it.univaq.incipict.ems.ep.CrowdingDataRequest;
import it.univaq.incipict.ems.ep.CrowdingDataResponse;
import it.univaq.incipict.ems.ep.EmptyResponse;
import it.univaq.incipict.ems.ep.EpConsumerPT;
import it.univaq.incipict.ems.ep.InstanceInfo;
import it.univaq.incipict.ems.ep.InterventionPlan;
import it.univaq.incipict.ems.ep.Notification;
import it.univaq.incipict.ems.ep.consumer.service.MessageConstructionService;
import it.univaq.incipict.ems.ep.consumer.service.MessageStoringService;
import it.univaq.incipict.ems.ep.monitor.LoggerCollector;

@Component(value = "EpConsumerPTImpl")
public class EpConsumerPTImpl implements EpConsumerPT {

	Logger logger = LoggerFactory.getLogger(EpConsumerPTImpl.class);

	@Autowired
	private MessageConstructionService messageConstructionService;

	@Autowired
	private MessageStoringService messageStoringService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public EmptyResponse storeSendBuildingDataRequest(BuildingDataList message) {

		logger.info("EP (consumer): received sendBuildingDataRequest message to store (instanceId " + message.getAlarmCode() + ")");
		messageStoringService.storeSendBuildingDataRequest(message);
		return new EmptyResponse();
	}

	@Override
	public CrowdingDataRequest getGetCrowdingDataMessage(InstanceInfo request) {

		loggerCollector.collectStart("Get Crowding Data", request.getInstanceId());
		logger.info("EP (consumer): received request to get getCrowdingData message (instanceId " + request.getInstanceId() + ")");
		CrowdingDataRequest response = new CrowdingDataRequest();
		response.getAreaCode().addAll(messageConstructionService.getAreaCodes());
		response.setRequestId(request.getInstanceId());
		return response;
	}

	@Override
	public InterventionPlan getSendInterventionPlanMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Intervention Plan", request.getInstanceId());
		logger.info("EP (consumer): received request to get sendInterventionPlan message (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getInterventionPlan();
	}

	@Override
	public EmptyResponse storeGetCrowdingDataResponse(CrowdingDataResponse message) {

		logger.info("EP (consumer): received getCrowdingDataResponse message to store (instanceId " + message.getRequestId() + ")");
		messageStoringService.storeGetCrowdingDataResponse(message);
		loggerCollector.collectEnd("Get Crowding Data", message.getRequestId());
		return new EmptyResponse();
	}

	@Override
	public BuildingDataList getSendBuildingDataMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Building Data", request.getInstanceId());
		logger.info("EP (consumer): received request to get sendBuildingData message (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getBuildingDataList();
	}

	@Override
	public Notification getSendNotificationMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Notification", request.getInstanceId());
		logger.info("EP (consumer): received request to get sendNotification message (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getNotificationMessage();
	}

	@Override
	public EmptyResponse storeSendMissingDataAcknowledgementRequest(BuildingDataList message) {

		logger.info("EP (consumer): received sendBuildingDataRequest message to store (instanceId " + message.getAlarmCode() + ")");
		messageStoringService.storeSendMissingDataAcknowledgementRequest(message);
		return new EmptyResponse();
	}

	@Override
	public EmptyResponse storeUpdateBuildingDataRequest(BuildingDataList message) {

		logger.info("EP (consumer): received sendBuildingDataRequest message to store (instanceId " + message.getAlarmCode() + ")");
		messageStoringService.storeUpdateBuildingDataRequest(message);
		return new EmptyResponse();
	}

	@Override
	public InterventionPlan getUpdateInterventionPlanMessage(InstanceInfo request) {

		loggerCollector.collectStart("Update Intervention Plan", request.getInstanceId());
		logger.info("EP (consumer): received updateInterventionPlan message to store (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getInterventionPlanUpdate();
	}

}
