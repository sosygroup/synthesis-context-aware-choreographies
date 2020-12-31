package it.univaq.incipict.ems.shm.consumer.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.shm.BuildingDataList;
import it.univaq.incipict.ems.shm.BuildingList;
import it.univaq.incipict.ems.shm.CheckpointDescription;
import it.univaq.incipict.ems.shm.CloseRoadsList;
import it.univaq.incipict.ems.shm.EmptyResponse;
import it.univaq.incipict.ems.shm.EndEvent;
import it.univaq.incipict.ems.shm.FlightDetails;
import it.univaq.incipict.ems.shm.GetBuildingDataRequest;
import it.univaq.incipict.ems.shm.InstanceInfo;
import it.univaq.incipict.ems.shm.LivePhotogrammetry;
import it.univaq.incipict.ems.shm.RawBuildingDataResponse;
import it.univaq.incipict.ems.shm.ShmConsumerPT;
import it.univaq.incipict.ems.shm.consumer.service.MessageConstructionService;
import it.univaq.incipict.ems.shm.consumer.service.MessageStoringService;
import it.univaq.incipict.ems.shm.monitor.LoggerCollector;

@Component("ShmConsumerPTImpl")
public class ShmConsumerPTImpl implements ShmConsumerPT {

	Logger logger = LoggerFactory.getLogger(ShmConsumerPTImpl.class);

	@Autowired
	private MessageConstructionService messageConstructionService;

	@Autowired
	private MessageStoringService messageStoringService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public EmptyResponse storeGetBuildingDataResponse(RawBuildingDataResponse message) {

		logger.info("SHM (consumer): received rawBuildingDataResponse message to store (instanceId " + message.getAlarmCode() + ")");
		messageStoringService.storeGetBuildingDataResponse(message);
		loggerCollector.collectEnd("Request Building Data", message.getAlarmCode());
		return new EmptyResponse();
	}

	@Override
	public BuildingDataList getSendBuildingDataMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Building Data", request.getInstanceId());
		logger.info("SHM (consumer): received request to get sendBuildingData message (instanceId " + request.getInstanceId() + ")");
		BuildingDataList response = new BuildingDataList();
		response.getBuildingData().addAll(messageConstructionService.getBuildingData());
		response.setAlarmCode(request.getInstanceId());
		return response;
	}

	@Override
	public GetBuildingDataRequest getGetBuildingDataMessage(InstanceInfo request) {

		loggerCollector.collectStart("Request Building Data", request.getInstanceId());
		logger.info("SHM (consumer): received request to get getBuildingData message (instanceId " + request.getInstanceId() + ")");
		GetBuildingDataRequest response = new GetBuildingDataRequest();
		response.getAreaCode().addAll(messageConstructionService.getAreaCodes());
		response.setAlarmCode(request.getInstanceId());
		return response;
	}

	@Override
	public BuildingList getSecureBuildingsMessage(InstanceInfo request) {

		loggerCollector.collectStart("Secure Buildings", request.getInstanceId());
		logger.info("SHM (consumer): received request to get secureBuildings message (instanceId " + request.getInstanceId() + ")");
		BuildingList response = new BuildingList();
		response.getBuildingCode().addAll(messageConstructionService.getDamagedBuildingCodes());
		response.setAlarmCode(request.getInstanceId());
		return response;
	}

	@Override
	public EmptyResponse storeEndEventRequest(EndEvent message) {

		logger.info("SHM (consumer): received endEventRequest message to store");
		messageStoringService.storeEndEventRequest(message);
		return new EmptyResponse();

	}

	@Override
	public CloseRoadsList getCloseRoadsMessage(InstanceInfo request) {

		loggerCollector.collectStart("Close Roads", request.getInstanceId());
		logger.info("SHM (consumer): received request to get closeRoads message (instanceId " + request.getInstanceId() + ")");
		CloseRoadsList response = new CloseRoadsList();
		response.getRoad().addAll(messageConstructionService.getHazardRoadList());
		response.setAlarmCode(request.getInstanceId());
		return response;
	}

	@Override
	public EmptyResponse storeCheckpointDescriptionRequest(CheckpointDescription message) {

		logger.info("SHM (consumer): received endEventRequest message to store");
		messageStoringService.storeCheckpointDescription(message);
		return new EmptyResponse();
	}

	@Override
	public FlightDetails getSendLiveFlightDetailsMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Live Flight Details", request.getInstanceId());
		logger.info("SHM (consumer): received request to get sendLiveFlightDetails message (instanceId " + request.getInstanceId() + ")");
		FlightDetails  response = new FlightDetails();
		response.setFlightId(request.getInstanceId());
		response.getCheckpoints().addAll(messageConstructionService.getCheckpointList());
		return response;
	}

	@Override
	public EmptyResponse storeLivePhotogrammetryRequest(LivePhotogrammetry message) {

		logger.info("SHM (consumer): received endEventRequest message to store");
		messageStoringService.storeLivePhotogrammetry(message);;
		return new EmptyResponse();
	}

	@Override
	public FlightDetails getSendSimpleFlightDetailsMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Simple Flight Details", request.getInstanceId());
		logger.info("SHM (consumer): received request to get sendSimpleFlightDetails message (instanceId " + request.getInstanceId() + ")");
		FlightDetails  response = new FlightDetails();
		response.setFlightId(request.getInstanceId());
		response.getCheckpoints().addAll(messageConstructionService.getCheckpointList());
		return response;

	}

	@Override
	public BuildingDataList getUpdateBuildingDataMessage(InstanceInfo request) {

		loggerCollector.collectStart("Update Building Data", request.getInstanceId());
		logger.info("SHM (consumer): received request to get updateBuildingData message (instanceId " + request.getInstanceId() + ")");
		BuildingDataList response = new BuildingDataList();
		response.getBuildingData().addAll(messageConstructionService.getBuildingDataUpdate());
		response.setAlarmCode(request.getInstanceId());
		return response;
	}

	@Override
	public BuildingDataList getSendMissingDataAcknowledgementMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Missing Data Acknowledgement", request.getInstanceId());
		logger.info("SHM (consumer): received request to get missingDataAcknowledgement message (instanceId " + request.getInstanceId() + ")");
		BuildingDataList response = new BuildingDataList();
		response.getBuildingData().addAll(messageConstructionService.getMissingBuildingDataList());
		response.setAlarmCode(request.getInstanceId());
		return response;
	}

}
