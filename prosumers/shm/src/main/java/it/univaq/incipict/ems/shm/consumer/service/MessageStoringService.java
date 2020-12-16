package it.univaq.incipict.ems.shm.consumer.service;

import it.univaq.incipict.ems.shm.CheckpointDescription;
import it.univaq.incipict.ems.shm.EndEvent;
import it.univaq.incipict.ems.shm.LivePhotogrammetry;
import it.univaq.incipict.ems.shm.RawBuildingDataResponse;

public interface MessageStoringService {

	public void storeGetBuildingDataResponse(RawBuildingDataResponse rawBuildingData);

	public void storeEndEventRequest(EndEvent endEvent);

	public void storeLivePhotogrammetry(LivePhotogrammetry livePhotogrammetry);

	public void storeCheckpointDescription(CheckpointDescription checkpointDescription);
}
