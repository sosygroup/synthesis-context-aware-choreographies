package it.univaq.incipict.ems.drone.client.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.drone.client.DroneClient;
import it.univaq.incipict.ems.seismographicnetwork.providers.BuildingClass;
import it.univaq.incipict.ems.seismographicnetwork.providers.CdDronePT;
import it.univaq.incipict.ems.seismographicnetwork.providers.CdDroneService;
import it.univaq.incipict.ems.seismographicnetwork.providers.Checkpoint;
import it.univaq.incipict.ems.seismographicnetwork.providers.CheckpointDescription;
import it.univaq.incipict.ems.seismographicnetwork.providers.DroneConfiguration;
import it.univaq.incipict.ems.seismographicnetwork.providers.DroneFleetControllerPT;
import it.univaq.incipict.ems.seismographicnetwork.providers.DronefleetcontrollerService;
import it.univaq.incipict.ems.seismographicnetwork.providers.EventType;
import it.univaq.incipict.ems.seismographicnetwork.providers.LivePhotogrammetry;
import it.univaq.incipict.ems.seismographicnetwork.providers.Log;
import it.univaq.incipict.ems.seismographicnetwork.providers.Logs;
import it.univaq.incipict.ems.seismographicnetwork.providers.Photogrammetry;
import it.univaq.incipict.ems.seismographicnetwork.providers.Void;

@Service
@PropertySource("classpath:endpoints.properties")
public class DroneClientImpl implements DroneClient {

	Logger logger = LoggerFactory.getLogger(DroneClientImpl.class);

	private static List<Log> logs = new ArrayList<>();

	@Value("${dronefleetcontroller}")
	private String droneFleetControllerEndpoint;

	@Value("${targetservice}")
	private String cdDroneEndpoint;

	@Override
	public DroneConfiguration getDroneConfiguration() {
		
		DronefleetcontrollerService dronefleetcontrollerService = new DronefleetcontrollerService();
		DroneFleetControllerPT droneFleetControllerPT = dronefleetcontrollerService.getDroneFleetControllerPort();
		((BindingProvider)droneFleetControllerPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, droneFleetControllerEndpoint);

		return droneFleetControllerPT.getDroneConfiguration(new Void());

	}

	@Override
	public void sendLivePhotogrammetry(String flightId, List<Checkpoint> checkpoints) {

		CdDroneService cdDroneService = new CdDroneService();
		CdDronePT cdDronePT = cdDroneService.getCdDronePort();
		((BindingProvider)cdDronePT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, cdDroneEndpoint);

		Iterator<Checkpoint> iterator = checkpoints.iterator();

		int sequence = 0;
		while (iterator.hasNext()) {
			sequence++;

			Log log = new Log();
			log.setEventType(EventType.START);
			log.setInstanceId(flightId);
			log.setOperation("Send Live Photogrammetry " + sequence);
			log.setTimestamp(System.currentTimeMillis());
			logs.add(log);

			Checkpoint checkpoint = iterator.next();

			logger.info("Drone: sending photogrammetry for checkpoint " + checkpoint.getId());

			Photogrammetry photogrammetry = new Photogrammetry();
			photogrammetry.setCheckpointId(checkpoint.getId());
			photogrammetry.setMetadata("");
			photogrammetry.setPhotogrammetry(new byte[0]);

			LivePhotogrammetry livePhotogrammetry = new LivePhotogrammetry();
			livePhotogrammetry.setPhotogrammetry(photogrammetry);
			livePhotogrammetry.setFlightId(flightId);
			livePhotogrammetry.setSequence(sequence);
			livePhotogrammetry.setContinue(iterator.hasNext());

			cdDronePT.sendLivePhotogrammetry(livePhotogrammetry);

			// Send data each 3 seconds
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void sendCheckpointDescription(String flightId, List<Checkpoint> checkpoints) {

		CdDroneService cdDroneService = new CdDroneService();
		CdDronePT cdDronePT = cdDroneService.getCdDronePort();
		((BindingProvider)cdDronePT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, cdDroneEndpoint);

		Iterator<Checkpoint> iterator = checkpoints.iterator();

		int sequence = 0;
		while (iterator.hasNext()) {
			sequence++;

			Log log = new Log();
			log.setEventType(EventType.START);
			log.setInstanceId(flightId);
			log.setOperation("Send Checkpoint Description " + sequence);
			log.setTimestamp(System.currentTimeMillis());
			logs.add(log);

			Checkpoint checkpoint = iterator.next();

			logger.info("Drone: sending description for checkpoint " + checkpoint.getId());

			CheckpointDescription checkpointDescription = new CheckpointDescription();
			checkpointDescription.setFlightId(flightId);
			checkpointDescription.setCheckpointId(checkpoint.getId());
			checkpointDescription.setStatus(BuildingClass.A);
			checkpointDescription.setSequence(sequence);
			checkpointDescription.setContinue(iterator.hasNext());

			cdDronePT.sendCheckpointDescription(checkpointDescription);

			// Send data each 3 seconds
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void sendLogs() {

		DronefleetcontrollerService dronefleetcontrollerService = new DronefleetcontrollerService();
		DroneFleetControllerPT droneFleetControllerPT = dronefleetcontrollerService.getDroneFleetControllerPort();
		((BindingProvider)droneFleetControllerPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, droneFleetControllerEndpoint);

		Logs logs = new Logs();
		logs.getLogs().addAll(DroneClientImpl.logs);

		logger.info("Drone: sending logs to fleet controller");

		droneFleetControllerPT.sendLogs(logs);
		
	}

}
