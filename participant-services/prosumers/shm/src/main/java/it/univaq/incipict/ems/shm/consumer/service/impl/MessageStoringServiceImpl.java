package it.univaq.incipict.ems.shm.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.shm.CheckpointDescription;
import it.univaq.incipict.ems.shm.EndEvent;
import it.univaq.incipict.ems.shm.LivePhotogrammetry;
import it.univaq.incipict.ems.shm.RawBuildingDataResponse;
import it.univaq.incipict.ems.shm.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.shm.consumer.service.MessageStoringService;


@Service
public class MessageStoringServiceImpl implements MessageStoringService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void storeGetBuildingDataResponse(RawBuildingDataResponse rawBuildingData) {

		logger.info("SHM (consumer): storing rawBuildingDataResponse");
		messageRepository.store("rawBuildingData", rawBuildingData);
	}

	@Override
	public void storeEndEventRequest(EndEvent endEvent) {

		logger.info("SHM (consumer): storing endEventRequest");
		messageRepository.store("endEvent", endEvent);
		
	}

	@Override
	public void storeLivePhotogrammetry(LivePhotogrammetry livePhotogrammetry) {

		logger.info("SHM (consumer): storing livePhotogrammetryRequest");
		messageRepository.store("livePhotogrammetry", livePhotogrammetry);
	}

	@Override
	public void storeCheckpointDescription(CheckpointDescription checkpointDescription) {

		logger.info("SHM (consumer): storing checkpointDescriptionRequest");
		messageRepository.store("checkpointDescription", checkpointDescription);
	}

}
