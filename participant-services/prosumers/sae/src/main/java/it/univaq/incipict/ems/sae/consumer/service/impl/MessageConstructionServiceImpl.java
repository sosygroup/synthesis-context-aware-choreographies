package it.univaq.incipict.ems.sae.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.sae.ActivationRequest;
import it.univaq.incipict.ems.sae.AlarmInfo;
import it.univaq.incipict.ems.sae.EarlyWarning;
import it.univaq.incipict.ems.sae.EmergencyType;
import it.univaq.incipict.ems.sae.SendPlanResponse;
import it.univaq.incipict.ems.sae.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.sae.consumer.service.MessageConstructionService;

@Service
public class MessageConstructionServiceImpl implements MessageConstructionService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);   

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public ActivationRequest getActivatePanelsRequest(String instanceId) {

		ActivationRequest response = new ActivationRequest();

		logger.info("SAE (consumer): retreiving alarmID");
		SendPlanResponse planResponse= messageRepository.get("alarmID", SendPlanResponse.class);
		logger.info("SAE (consumer): alarmID "+ response);

		response.setAlarmCode(planResponse.getAlarmCode());
		response.setEvacuationId(planResponse.getEvacuationId());
		return response;
	}

	@Override
	public AlarmInfo getAlarmInfo() {
		logger.info("SAE (consumer): retreiving earlyWarning");
		EarlyWarning earlyWarning = messageRepository.get("earlyWarning", EarlyWarning.class);

		logger.info("SAE (consumer): earlyWarning " + earlyWarning);

		AlarmInfo alarmInfo = new AlarmInfo();
		if (earlyWarning!=null) {
			alarmInfo.setAlarmCode(earlyWarning.getEventCode());
			alarmInfo.setEmergencyType(EmergencyType.EARTHQUAKE);
			alarmInfo.setTimestamp(earlyWarning.getTimestamp());
			alarmInfo.getAreaCode().add("AQ01");
		} else {
			alarmInfo.setEmergencyType(EmergencyType.GENERALEMERGENCY);
		}

		return alarmInfo;
	}

}
