package it.univaq.incipict.ems.dronefleetcontroller.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.dronefleetcontroller.DroneConfiguration;
import it.univaq.incipict.ems.dronefleetcontroller.DroneFleetControllerPT;
import it.univaq.incipict.ems.dronefleetcontroller.FlightDetails;
import it.univaq.incipict.ems.dronefleetcontroller.Logs;
import it.univaq.incipict.ems.dronefleetcontroller.Void;
import it.univaq.incipict.ems.dronefleetcontroller.monitor.EventLog;
import it.univaq.incipict.ems.dronefleetcontroller.monitor.EventType;
import it.univaq.incipict.ems.dronefleetcontroller.monitor.LoggerCollector;
import it.univaq.incipict.ems.dronefleetcontroller.service.DroneFleetControllerService;


@Component(value = "DroneFleetControllerPTImpl")
public class DroneFleetControllerPTImpl implements DroneFleetControllerPT {

	private static Logger logger = LoggerFactory.getLogger(DroneFleetControllerPTImpl.class);

	@Autowired
	private DroneFleetControllerService droneFleetControllerService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void sendLiveFlightDetails(FlightDetails request) {
		logger.info("DroneFleetController: received received sendLiveFlightDetails request (InstanceId " + request.getFlightId() + ")");
		droneFleetControllerService.sendFlightDetails(request, true);
		loggerCollector.collectEnd("Send Live Flight Details", request.getFlightId());
	}

	@Override
	public void sendSimpleFlightDetails(FlightDetails request) {
		logger.info("DroneFleetController: received sendSimpleFlightDetails request (InstanceId " + request.getFlightId() + ")");
		droneFleetControllerService.sendFlightDetails(request, false);
		loggerCollector.collectEnd("Send Simple Flight Details", request.getFlightId());
	}

	@Override
	public DroneConfiguration getDroneConfiguration(Void request) {
		logger.info("DroneFleetController: sending drone configuration");
		return droneFleetControllerService.getDroneConfiguration();
	}

	@Override
	public void sendLogs(Logs request) {
		logger.info("DroneFleetController: received logs from drones");

		request.getLogs().forEach(log -> {
			EventType eventType = EventType.valueOf(log.getEventType().value());
			loggerCollector.getLogs().add(new EventLog(eventType, log.getOperation(), log.getInstanceId(), log.getTimestamp()));
		});
	}

}
