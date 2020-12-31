package it.univaq.incipict.ems.ep.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.ep.BuildingDataList;
import it.univaq.incipict.ems.ep.CrowdingDataResponse;
import it.univaq.incipict.ems.ep.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.ep.consumer.service.MessageStoringService;


@Service
public class MessageStoringServiceImpl implements MessageStoringService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void storeSendBuildingDataRequest(BuildingDataList buildingData) {

		logger.info("EP (consumer): storing buildingData");
		messageRepository.store("buildingData", buildingData);
	}

	@Override
	public void storeGetCrowdingDataResponse(CrowdingDataResponse crowdingData) {

		logger.info("EP (consumer): storing crowdingData");
		messageRepository.store("crowdingData", crowdingData);
	}

	@Override
	public void storeUpdateBuildingDataRequest(BuildingDataList buildingData) {

		logger.info("EP (consumer): storing buildingDataUpdate");
		messageRepository.store("buildingDataUpdate", buildingData);
	}

	@Override
	public void storeSendMissingDataAcknowledgementRequest(BuildingDataList buildingData) {

		logger.info("EP (consumer): storing buildingData");
		messageRepository.store("missingData", buildingData);
	}

}
