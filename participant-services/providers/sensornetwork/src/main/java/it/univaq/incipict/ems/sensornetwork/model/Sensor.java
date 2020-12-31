package it.univaq.incipict.ems.sensornetwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

	private String id;

	private String areaCode;

	private String buildingId;

}
