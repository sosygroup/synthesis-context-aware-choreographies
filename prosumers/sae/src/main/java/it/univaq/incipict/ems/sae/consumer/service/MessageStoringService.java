package it.univaq.incipict.ems.sae.consumer.service;

import it.univaq.incipict.ems.sae.EarlyWarning;
import it.univaq.incipict.ems.sae.SendPlanResponse;

public interface MessageStoringService {

	public void storeSignalEventRequest(EarlyWarning signalEventRequest);

	public void storeSendPlanResponse(SendPlanResponse sendPlanResponse);
}
