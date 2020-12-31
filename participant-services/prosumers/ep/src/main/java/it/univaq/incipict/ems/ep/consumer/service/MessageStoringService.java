package it.univaq.incipict.ems.ep.consumer.service;

import it.univaq.incipict.ems.ep.BuildingDataList;
import it.univaq.incipict.ems.ep.CrowdingDataResponse;

public interface MessageStoringService {

	public void storeSendBuildingDataRequest(BuildingDataList buildingData);

	public void storeGetCrowdingDataResponse(CrowdingDataResponse crowdingData);

	public void storeUpdateBuildingDataRequest(BuildingDataList buildingData);

	public void storeSendMissingDataAcknowledgementRequest(BuildingDataList buildingData);
}
