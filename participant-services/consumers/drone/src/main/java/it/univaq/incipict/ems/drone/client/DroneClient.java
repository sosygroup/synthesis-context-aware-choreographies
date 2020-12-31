package it.univaq.incipict.ems.drone.client;

import java.util.List;

import it.univaq.incipict.ems.seismographicnetwork.providers.Checkpoint;
import it.univaq.incipict.ems.seismographicnetwork.providers.DroneConfiguration;

public interface DroneClient {

	public DroneConfiguration getDroneConfiguration();

	public void sendLivePhotogrammetry(String flightId, List<Checkpoint> checkpoints);

	public void sendCheckpointDescription(String flightId, List<Checkpoint> checkpoints);

	public void sendLogs();
}
