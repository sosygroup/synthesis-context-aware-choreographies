package it.univaq.incipict.ems.shm.provider.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.shm.CheckpointDescription;
import it.univaq.incipict.ems.shm.EndEvent;
import it.univaq.incipict.ems.shm.LivePhotogrammetry;
import it.univaq.incipict.ems.shm.ShmPT;
import it.univaq.incipict.ems.shm.monitor.EventLog;
import it.univaq.incipict.ems.shm.monitor.EventType;
import it.univaq.incipict.ems.shm.monitor.LoggerCollector;

@Component(value = "ShmPTImpl")
public class ShmPTImpl implements ShmPT {

	Logger logger = LoggerFactory.getLogger(ShmPTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void signalEndEvent(EndEvent request) {

		logger.info("SHM: received signalEndEvent request message (InstanceId " + request.getEventCode() + ")");

		// Add the log for the start time of the event
		loggerCollector.getLogs().add(new EventLog(EventType.START, "Signal End Event", request.getEventCode(), request.getTimestamp().getTime()));

		loggerCollector.collectEnd("Signal End Event", request.getEventCode());
	}

	@Override
	public void sendLivePhotogrammetry(LivePhotogrammetry request) {

		logger.info("SHM: received livePhotogrammetry request message (InstanceId" + request.getFlightId() + ")");

		loggerCollector.collectEnd("Send Live Photogrammetry " + request.getSequence(), request.getFlightId());
	}

	@Override
	public void sendCheckpointDescription(CheckpointDescription response) {

		logger.info("SHM: received checkpointDescription request message (InstanceId" + response.getFlightId() + ")");

		loggerCollector.collectEnd("Send Checkpoint Description " + response.getSequence(), response.getFlightId());
	}

}
