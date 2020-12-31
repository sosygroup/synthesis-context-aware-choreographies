package it.univaq.incipict.ems.earlywarningsystem.client.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.earlywarningsystem.client.EarlyWarningSystemClient;
import it.univaq.incipict.ems.earlywarningsystem.client.properties.EarlyWarningSystemProperties;
import it.univaq.incipict.ems.earlywarningsystem.providers.CdEarlywarningsystemPT;
import it.univaq.incipict.ems.earlywarningsystem.providers.CdEarlywarningsystemService;
import it.univaq.incipict.ems.earlywarningsystem.providers.Coordinates;
import it.univaq.incipict.ems.earlywarningsystem.providers.EarlyWarning;
import it.univaq.incipict.ems.earlywarningsystem.providers.EndEvent;

@Service
public class EarlyWarningSystemClientImpl implements EarlyWarningSystemClient {

	Logger logger = LoggerFactory.getLogger(EarlyWarningSystemClientImpl.class);

	@Autowired
	private EarlyWarningSystemProperties properties;

	@Override
	public void signalEvent(String eventCode) {

		CdEarlywarningsystemService service  = new CdEarlywarningsystemService();
		CdEarlywarningsystemPT earlyWarningSystemPT = service.getCdEarlywarningsystemPort();
		((BindingProvider)earlyWarningSystemPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.cdEarlyWarningSystemURL);

		EarlyWarning request = new EarlyWarning();
		request.setEventCode(eventCode);
		request.setSensorID("SensorID");
		request.setPredictedMagnitude(1.1f);
		request.setTimestamp(new Timestamp(new Date().getTime()));

		Coordinates coordinates = new Coordinates();
		coordinates.setLatitude(42f);
		coordinates.setLongitude(14f);

		request.setCoordinates(coordinates);

		logger.info("EarlyWarningSystem: signaling event (code " + eventCode + ")");
		earlyWarningSystemPT.signalEvent(request);
	}

	@Override
	public void signalEndEvent(String eventCode) {

		CdEarlywarningsystemService service  = new CdEarlywarningsystemService();
		CdEarlywarningsystemPT earlyWarningSystemPT = service.getCdEarlywarningsystemPort();
		((BindingProvider)earlyWarningSystemPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.cdEarlyWarningSystemURL);

		EndEvent request = new EndEvent();
		request.setEventCode(eventCode);
		request.setEstimatedMagnitude(0.9f);
		request.setTimestamp(new Timestamp(new Date().getTime()));

		logger.info("EarlyWarningSystem: signaling end event (code " + eventCode + ")");
		earlyWarningSystemPT.signalEndEvent(request);
	}

}
