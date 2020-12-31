package it.univaq.incipict.ems.sensornetwork.service.impl.dummy;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.sensornetwork.model.AccelerometricalData;
import it.univaq.incipict.ems.sensornetwork.model.Sensor;
import it.univaq.incipict.ems.sensornetwork.model.SensorLog;
import it.univaq.incipict.ems.sensornetwork.service.SensorNetworkService;

@Service
public class SensorNetworkServiceImpl implements SensorNetworkService {

	@Override
	public List<SensorLog> getSensorLogs(List<String> areaCodes) {

		List<SensorLog> sensorLogs = new ArrayList<>();

		Sensor sensor1 = new Sensor("S01", "AQ01", "0001");
		Sensor sensor2 = new Sensor("S02", "AQ01", "0002");
		Sensor sensor3 = new Sensor("S03", "AQ01", "0003");
		Sensor sensor4 = new Sensor("S04", "AQ01", "0004");
		Sensor sensor5 = new Sensor("S04", "AQ01", "0005");
		Sensor sensor6 = new Sensor("S04", "AQ01", "0006");
		Sensor sensor7 = new Sensor("S04", "AQ01", "0007");
		Sensor sensor8 = new Sensor("S04", "AQ01", "0008");
		Sensor sensor9 = new Sensor("S04", "AQ01", "0009");
		Sensor sensor10 = new Sensor("S04", "AQ01", "0010");
		Sensor sensor11 = new Sensor("S04", "AQ01", "0011");
		Sensor sensor12 = new Sensor("S04", "AQ01", "0012");
		Sensor sensor13 = new Sensor("S04", "AQ01", "0013");
		Sensor sensor14 = new Sensor("S04", "AQ01", "0014");
		Sensor sensor15 = new Sensor("S04", "AQ01", "0015");

		sensorLogs.add(new SensorLog(sensor1, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor2, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor3, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor4, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor5, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor6, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor7, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor8, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor9, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor10, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor11, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor12, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor13, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor14, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));
		sensorLogs.add(new SensorLog(sensor15, Date.from(Instant.now()), new AccelerometricalData(1.0, 1.1, 1.2)));

		return sensorLogs;
	}

}
