package it.univaq.incipict.ems.sensornetwork.webservices;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.sensornetwork.GetBuildingDataRequest;
import it.univaq.incipict.ems.sensornetwork.RawBuildingData;
import it.univaq.incipict.ems.sensornetwork.RawBuildingDataResponse;
import it.univaq.incipict.ems.sensornetwork.SensorData;
import it.univaq.incipict.ems.sensornetwork.SensorNetworkPT;
import it.univaq.incipict.ems.sensornetwork.model.SensorLog;
import it.univaq.incipict.ems.sensornetwork.service.SensorNetworkService;


@Component(value = "SensorNetworkPTImpl")
public class SensorNetworkPTImpl implements SensorNetworkPT {

	private static Logger logger = LoggerFactory.getLogger(SensorNetworkPTImpl.class);

	@Autowired
	private SensorNetworkService sensorNetworkService;

	@Override
	public RawBuildingDataResponse getBuildingData(GetBuildingDataRequest request) {
		logger.info("SensorNetwork: received getBuildingData request");

		List<SensorLog> sensorLogs = sensorNetworkService.getSensorLogs(request.getAreaCode());

		Map<String, List<SensorData>> logMap = new HashMap<>();

		sensorLogs.forEach(sensorLog -> {
			List<SensorData> logList = logMap.get(sensorLog.getSensor().getBuildingId());
			if (logList == null) {
				logList = new LinkedList<>();
				logMap.put(sensorLog.getSensor().getBuildingId(), logList);
			}
			SensorData sensorData = new SensorData();
			sensorData.setSensorID(sensorLog.getSensor().getId());
			sensorData.setTimestamp(sensorLog.getTimestamp());
			sensorData.setX(sensorLog.getAccelerometricalData().getX());
			sensorData.setY(sensorLog.getAccelerometricalData().getY());
			sensorData.setZ(sensorLog.getAccelerometricalData().getZ());
			logList.add(sensorData);
		});

		RawBuildingDataResponse response = new RawBuildingDataResponse();
		response.setAlarmCode(request.getAlarmCode());
		for (Map.Entry<String, List<SensorData>> entry : logMap.entrySet()) {
			RawBuildingData data = new RawBuildingData();
			data.setBuildingID(entry.getKey());
			data.getSensorData().addAll(entry.getValue());
			response.getBuildingData().add(data);
		}

		logger.info("SensorNetwork: replied with building data");
		return response;
	}

}
