package it.univaq.incipict.ems.dronefleetcontroller.service;

import it.univaq.incipict.ems.dronefleetcontroller.DroneConfiguration;
import it.univaq.incipict.ems.dronefleetcontroller.FlightDetails;

public interface DroneFleetControllerService {

	public void sendFlightDetails(FlightDetails flyDetails, boolean live);
	public DroneConfiguration getDroneConfiguration();
}
