package it.univaq.incipict.ems.shm.consumer.service;

import java.util.List;

import it.univaq.incipict.ems.shm.BuildingData;
import it.univaq.incipict.ems.shm.Checkpoint;
import it.univaq.incipict.ems.shm.Road;

public interface MessageConstructionService {

	List<String> getAreaCodes();

	List<BuildingData> getBuildingData();

	List<String> getDamagedBuildingCodes();

	List<Road> getHazardRoadList();

	List<Checkpoint> getCheckpointList();

	List<BuildingData> getBuildingDataUpdate();

	List<BuildingData> getMissingBuildingDataList();
}
