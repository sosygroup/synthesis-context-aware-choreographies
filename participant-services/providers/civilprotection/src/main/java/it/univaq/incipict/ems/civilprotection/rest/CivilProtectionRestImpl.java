package it.univaq.incipict.ems.civilprotection.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.civilprotection.InterventionPlan;
import it.univaq.incipict.ems.civilprotection.PlaceInfo;
import it.univaq.incipict.ems.civilprotection.domain.Building;
import it.univaq.incipict.ems.civilprotection.domain.BuildingInfo;
import it.univaq.incipict.ems.civilprotection.rest.model.BuildingDataResponse;
import it.univaq.incipict.ems.civilprotection.rest.model.BuildingDataResponseList;
import it.univaq.incipict.ems.civilprotection.rest.model.InterventionPlanResponse;
import it.univaq.incipict.ems.civilprotection.service.CivilProtectionService;

@Component(value = "CivilProtectionRestImpl")
public class CivilProtectionRestImpl {

	@Autowired
	CivilProtectionService service;
	
	@GET
	@Path("/buildingData/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuildingData() {
		List<BuildingInfo> buildingInfoList = service.getBuildingData();

		BuildingDataResponseList response = new BuildingDataResponseList();

		buildingInfoList.forEach(buildingInfo -> {
			BuildingDataResponse buildingDataResponse = new BuildingDataResponse();

			buildingDataResponse.setBuildingId(buildingInfo.getBuilding().getId());
			buildingDataResponse.setStatus(buildingInfo.getBuildingClass());
			buildingDataResponse.setPeopleCounted(buildingInfo.getPeopleCounted());

			Building building = service.getBuilding(buildingInfo.getBuilding().getId());
			if (building != null) {				
				buildingDataResponse.setName(building.getName());
				buildingDataResponse.setLat(building.getLat());
				buildingDataResponse.setLon(building.getLon());
			}

			response.getBuildingData().add(buildingDataResponse);
		});

		return Response.ok(response).build();
	}

	@GET
	@Path("/interventionPlan/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInterventionPlan() {
		InterventionPlan interventionPlan = service.getInterventionPlan();

		InterventionPlanResponse response = new InterventionPlanResponse();
		response.setUpdateDate(new Date());

		if (interventionPlan.getHighestPriority() != null) {
			response.getHighestPriority().addAll(interventionPlan.getHighestPriority().getPlaceInfos()
					.stream().map(info -> toBuildingDataResponse(info)).collect(Collectors.toList()));
		}

		if (interventionPlan.getMediumPriority() != null) {
			response.getMediumPriority().addAll(interventionPlan.getMediumPriority().getPlaceInfos()
					.stream().map(info -> toBuildingDataResponse(info)).collect(Collectors.toList()));
		}

		if (interventionPlan.getLowestPriority() != null) {
			response.getLowestPriority().addAll(interventionPlan.getLowestPriority().getPlaceInfos()
					.stream().map(info -> toBuildingDataResponse(info)).collect(Collectors.toList()));
		}
		
		return Response
				.ok(response).build();
	}

	private BuildingDataResponse toBuildingDataResponse(PlaceInfo buildingInfo) {
		BuildingDataResponse buildingDataResponse = new BuildingDataResponse();

		buildingDataResponse.setBuildingId(buildingInfo.getPlaceID());
		buildingDataResponse.setStatus(buildingInfo.getBuildingClass());
		buildingDataResponse.setPeopleCounted(buildingInfo.getPeopleCounted());

		Building building = service.getBuilding(buildingInfo.getPlaceID());

		if (building != null) {				
			buildingDataResponse.setName(building.getName());
			buildingDataResponse.setLat(building.getLat());
			buildingDataResponse.setLon(building.getLon());
		}

		return buildingDataResponse;
	}

}
