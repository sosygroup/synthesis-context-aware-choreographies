package it.univaq.incipict.ems.sensornetwork.service;

import java.util.List;

import it.univaq.incipict.ems.sensornetwork.model.SensorLog;

public interface SensorNetworkService {

	public List<SensorLog> getSensorLogs(List<String> areaCodes);
}
