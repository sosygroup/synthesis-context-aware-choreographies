package it.univaq.incipict.ems.sae.consumer.service;

import it.univaq.incipict.ems.sae.ActivationRequest;
import it.univaq.incipict.ems.sae.AlarmInfo;

public interface MessageConstructionService {

	public ActivationRequest getActivatePanelsRequest(String instanceId);

	public AlarmInfo getAlarmInfo();
}
