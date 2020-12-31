package it.univaq.incipict.ems.sae.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.sae.EarlyWarning;
import it.univaq.incipict.ems.sae.SendPlanResponse;
import it.univaq.incipict.ems.sae.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.sae.consumer.service.MessageStoringService;

@Service
public class MessageStoringServiceImpl implements MessageStoringService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;


	@Override
	public void storeSignalEventRequest(EarlyWarning signalEventRequest) {

		messageRepository.store("earlyWarning", signalEventRequest);
		
	}

	@Override
	public void storeSendPlanResponse(SendPlanResponse sendPlanResponse) {

		messageRepository.store("alarmID", sendPlanResponse);
	}

}
