package it.univaq.incipict.ems.civilprotection.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterventionPlanResponse {

	private Date updateDate;

	private List<BuildingDataResponse> highestPriority;

	private List<BuildingDataResponse> mediumPriority;

	private List<BuildingDataResponse> lowestPriority;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<BuildingDataResponse> getHighestPriority() {
		if (this.highestPriority == null) {
			this.highestPriority = new ArrayList<>(); 
		}

		return highestPriority;
	}

	public List<BuildingDataResponse> getMediumPriority() {
		if (this.mediumPriority == null) {
			this.mediumPriority = new ArrayList<>(); 
		}

		return mediumPriority;
	}

	public List<BuildingDataResponse> getLowestPriority() {
		if (this.lowestPriority == null) {
			this.lowestPriority = new ArrayList<>(); 
		}

		return lowestPriority;
	}

}
