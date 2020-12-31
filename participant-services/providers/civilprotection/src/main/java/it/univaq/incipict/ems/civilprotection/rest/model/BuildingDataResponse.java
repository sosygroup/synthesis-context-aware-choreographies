package it.univaq.incipict.ems.civilprotection.rest.model;

import it.univaq.incipict.ems.civilprotection.BuildingClass;

public class BuildingDataResponse {

	private String buildingId;

	private String name;

	private Float lat;

	private Float lon;

	private BuildingClass status;

	private Integer peopleCounted;

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public BuildingClass getStatus() {
		return status;
	}

	public void setStatus(BuildingClass status) {
		this.status = status;
	}

	public Integer getPeopleCounted() {
		return peopleCounted;
	}

	public void setPeopleCounted(Integer peopleCounted) {
		this.peopleCounted = peopleCounted;
	}

}
