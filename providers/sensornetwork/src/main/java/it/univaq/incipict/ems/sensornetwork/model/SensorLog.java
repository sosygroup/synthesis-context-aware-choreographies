package it.univaq.incipict.ems.sensornetwork.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorLog {

	private Sensor sensor;

	private Date timestamp;

	private AccelerometricalData accelerometricalData;

}
