package it.univaq.incipict.ems.civilprotection.domain;

import it.univaq.incipict.ems.civilprotection.BuildingClass;

public class BuildingInfo {

	private Building building;

	private BuildingClass buildingClass;

	private Integer peopleCounted;

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public BuildingClass getBuildingClass() {
		return buildingClass;
	}

	public void setBuildingClass(BuildingClass buildingClass) {
		this.buildingClass = buildingClass;
	}

	public Integer getPeopleCounted() {
		return peopleCounted;
	}

	public void setPeopleCounted(Integer peopleCounted) {
		this.peopleCounted = peopleCounted;
	}

}
