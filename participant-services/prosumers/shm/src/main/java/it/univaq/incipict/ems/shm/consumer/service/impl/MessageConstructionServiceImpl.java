package it.univaq.incipict.ems.shm.consumer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.shm.BuildingClass;
import it.univaq.incipict.ems.shm.BuildingData;
import it.univaq.incipict.ems.shm.Checkpoint;
import it.univaq.incipict.ems.shm.RawBuildingDataResponse;
import it.univaq.incipict.ems.shm.Road;
import it.univaq.incipict.ems.shm.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.shm.consumer.service.MessageConstructionService;

@Service
public class MessageConstructionServiceImpl implements MessageConstructionService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public List<String> getAreaCodes() {

		List<String> result = new ArrayList<>();
		result.add("AQ01");
		return result;
	}

	@Override
	public List<BuildingData> getBuildingData() {

		logger.info("SHM (consumer): retreiving rawBuildingData");
		RawBuildingDataResponse rawBuildingDataList = messageRepository.get("rawBuildingData", RawBuildingDataResponse.class);

		List<BuildingData> response = new ArrayList<>();

		rawBuildingDataList.getBuildingData().forEach(rawBuildingData -> {

			BuildingData buildingData = new BuildingData();
			buildingData.setBuildingID(rawBuildingData.getBuildingID());

			// Randomically selecting damaged buildings from the list
			double damageValue = Math.random();
			BuildingClass classification;

			if (damageValue < 0.3) {
				classification = BuildingClass.A;
			} else if (damageValue < 0.5) {
				classification = BuildingClass.B;
			} else if (damageValue < 0.6) {
				classification = BuildingClass.C;
			} else if (damageValue < 0.8) {
				classification = BuildingClass.D;
			} else if (damageValue < 0.9) {
				classification = BuildingClass.E;
			} else {
				classification = BuildingClass.F;
			}

			buildingData.setStatus(classification);
			response.add(buildingData);
		});
		return response;
	}

	@Override
	public List<String> getDamagedBuildingCodes() {

		logger.info("SHM (consumer): retreiving rawBuildingData");
		RawBuildingDataResponse rawBuildingDataList = messageRepository.get("rawBuildingData", RawBuildingDataResponse.class);

		List<String> response = new ArrayList<>();

		rawBuildingDataList.getBuildingData().forEach(rawBuildingData -> {

			// Randomically selecting damaged buildings from the list
			double damageValue = Math.random();

			if (damageValue > 0.7) {
				response.add(rawBuildingData.getBuildingID());
			}
		});
		return response;
	}

	@Override
	public List<Road> getHazardRoadList() {

		List<Road> result = new ArrayList<>();
		Road road = new Road();
		road.setRoadCode("R001");
		road.setFrom(0.5);
		road.setTo(2);
		result.add(road);

		road = new Road();
		road.setRoadCode("R002");
		road.setFrom(3);
		road.setTo(4.5);
		result.add(road);
		
		return result;
	}

	@Override
	public List<Checkpoint> getCheckpointList() {

//		AQ01	0014	42.353756, 13.405073	Forte Spagnolo
//		AQ01	0015	42.342807, 13.404320	Basilica di Santa Maria di Collemaggio

		List<Checkpoint> checkpoints = new ArrayList<>();
		Checkpoint checkpoint = new Checkpoint();
		checkpoint.setId("0014");
		checkpoints.add(checkpoint);

		checkpoint = new Checkpoint();
		checkpoint.setId("0015");
		checkpoints.add(checkpoint);

		return checkpoints;
	}

	@Override
	public List<BuildingData> getBuildingDataUpdate() {

		List<BuildingData> buildingDataList = new ArrayList<>();
		BuildingData buildingData = new BuildingData();
		buildingData.setBuildingID("0014");
		buildingData.setStatus(BuildingClass.B);
		buildingDataList.add(buildingData);
		return buildingDataList;
	}

	@Override
	public List<BuildingData> getMissingBuildingDataList() {

		List<BuildingData> buildingDataList = new ArrayList<>();
		BuildingData buildingData = new BuildingData();
		buildingData.setBuildingID("0013");
		buildingDataList.add(buildingData);
		return buildingDataList;
	}

}
