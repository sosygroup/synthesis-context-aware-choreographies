package it.univaq.incipict.ems.dronefleetcontroller.service.impl;

import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.dronefleetcontroller.DroneConfiguration;
import it.univaq.incipict.ems.dronefleetcontroller.FlightDetails;
import it.univaq.incipict.ems.dronefleetcontroller.service.DroneFleetControllerService;

@Service
public class DroneFleetControllerServiceImpl implements DroneFleetControllerService {

	private static FlightDetails flightDetails;
	private static boolean live;

	@Override
	public void sendFlightDetails(FlightDetails flightDetails, boolean live) {
		DroneFleetControllerServiceImpl.flightDetails = flightDetails;
		DroneFleetControllerServiceImpl.live = live;
	}

	public DroneConfiguration getDroneConfiguration() {
		DroneConfiguration configuration = new DroneConfiguration();
		configuration.setLiveImages(live);
		configuration.setFlightId(flightDetails.getFlightId());
		configuration.getCheckpoints().addAll(flightDetails.getCheckpoints());
		return configuration;
	}

}
