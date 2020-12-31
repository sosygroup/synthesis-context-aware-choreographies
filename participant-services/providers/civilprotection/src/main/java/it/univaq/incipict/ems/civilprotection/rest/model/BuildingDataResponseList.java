package it.univaq.incipict.ems.civilprotection.rest.model;

import java.util.ArrayList;
import java.util.List;

public class BuildingDataResponseList {

	private List<BuildingDataResponse> buildingData;

	public List<BuildingDataResponse> getBuildingData() {
		if (this.buildingData == null) {
			buildingData = new ArrayList<BuildingDataResponse>();
		}
		return buildingData;
	}

}
