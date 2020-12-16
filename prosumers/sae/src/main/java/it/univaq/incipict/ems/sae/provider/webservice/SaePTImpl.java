package it.univaq.incipict.ems.sae.provider.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.sae.EarlyWarning;
import it.univaq.incipict.ems.sae.SaePT;
import it.univaq.incipict.ems.sae.monitor.EventLog;
import it.univaq.incipict.ems.sae.monitor.EventType;
import it.univaq.incipict.ems.sae.monitor.LoggerCollector;

@Component(value = "SaePTImpl")
public class SaePTImpl implements SaePT {

	private static Logger logger = LoggerFactory.getLogger(SaePTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void signalEvent(EarlyWarning request) {

		logger.info("SAE: received signalEvent request message (InstanceID " + request.getEventCode() + ")");

		// Add the log for the start time of the event
		loggerCollector.getLogs().add(new EventLog(EventType.START, "Signal Event", request.getEventCode(), request.getTimestamp().getTime()));

		loggerCollector.collectEnd("Signal Event", request.getEventCode());
	}

}
