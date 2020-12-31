package it.univaq.incipict.ems.bsa.consumer.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.bsa.AlarmInfo;
import it.univaq.incipict.ems.bsa.BsaConsumerPT;
import it.univaq.incipict.ems.bsa.BuildingList;
import it.univaq.incipict.ems.bsa.EmptyResponse;
import it.univaq.incipict.ems.bsa.InstanceInfo;
import it.univaq.incipict.ems.bsa.consumer.service.MessageConstructionService;
import it.univaq.incipict.ems.bsa.consumer.service.MessageStoringService;
import it.univaq.incipict.ems.bsa.monitor.LoggerCollector;

@Component(value = "BsaConsumerPTImpl")
public class BsaConsumerPTImpl implements BsaConsumerPT {

	Logger logger = LoggerFactory.getLogger(BsaConsumerPTImpl.class);

	@Autowired
	private MessageStoringService messageStoringService;

	@Autowired
	private MessageConstructionService messageConstructionService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public EmptyResponse storeNotifyAlarmingRequest(AlarmInfo message) {

		logger.info("BSA (consumer): received notifyAlarmingRequest message to store (instanceId " + message.getAlarmCode() + ")");
		messageStoringService.storeNotifyAlarmingRequest(message);
		return new EmptyResponse();
	}

	@Override
	public EmptyResponse storeSecureBuildingsRequest(BuildingList message) {

		logger.info("BSA (consumer): received secureBuildingsRequest message to store (instanceId " + message.getAlarmCode() + ")");
		messageStoringService.storeSecureBuildingsRequest(message);
		return new EmptyResponse();
	}

	@Override
	public AlarmInfo getSignalStopElevatorsMessage(InstanceInfo request) {

		loggerCollector.collectStart("Signal Stop Elevators", request.getInstanceId());
		logger.info("BSA (consumer): received request to get signalStopElevatorsMessage (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getAlarmInfo(request.getInstanceId());
	}

	@Override
	public BuildingList getShutdownPlantsMessage(InstanceInfo request) {

		loggerCollector.collectStart("Shutdown Plants", request.getInstanceId());
		logger.info("BSA (consumer): received request to get shutdownPlantsMessage (instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getBuildingList(request.getInstanceId());
	}

}
