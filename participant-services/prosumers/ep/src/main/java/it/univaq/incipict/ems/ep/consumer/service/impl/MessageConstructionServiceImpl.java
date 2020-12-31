package it.univaq.incipict.ems.ep.consumer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.ep.BuildingDataList;
import it.univaq.incipict.ems.ep.CrowdingData;
import it.univaq.incipict.ems.ep.CrowdingDataResponse;
import it.univaq.incipict.ems.ep.InterventionPlan;
import it.univaq.incipict.ems.ep.InterventionPlan.HighestPriority;
import it.univaq.incipict.ems.ep.InterventionPlan.LowestPriority;
import it.univaq.incipict.ems.ep.InterventionPlan.MediumPriority;
import it.univaq.incipict.ems.ep.Notification;
import it.univaq.incipict.ems.ep.PlaceInfo;
import it.univaq.incipict.ems.ep.consumer.repository.MessageRepository;
import it.univaq.incipict.ems.ep.consumer.service.MessageConstructionService;

@Service
public class MessageConstructionServiceImpl implements MessageConstructionService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public List<String> getAreaCodes() {

		List<String> result = new ArrayList<>();
		result.add("AQ01");
		return result;
	}

	@Override
	public InterventionPlan getInterventionPlan() {

		logger.info("EP (consumer): retreiving buildingData and crowdingData");
		BuildingDataList buildingDataList = messageRepository.get("buildingData", BuildingDataList.class);
		List<CrowdingData> crowdingDataList = messageRepository.get("crowdingData", CrowdingDataResponse.class).getData();

		InterventionPlan interventionPlan = new InterventionPlan();
		initInterventionPlan(interventionPlan);

		interventionPlan.setAlarmCode(buildingDataList.getAlarmCode());

		Map<String, PlaceInfo> placeMap = new HashMap<>();

		buildingDataList.getBuildingData().forEach(data -> {

			if (!placeMap.containsKey(data.getBuildingID())) {

				PlaceInfo placeInfo = new PlaceInfo();
				placeInfo.setPlaceID(data.getBuildingID());
				placeInfo.setBuildingClass(data.getStatus());
				placeMap.put(data.getBuildingID(), placeInfo);
			}
		});

		crowdingDataList.forEach(data -> {
			if (!placeMap.containsKey(data.getPlaceID())) {

				PlaceInfo placeInfo = new PlaceInfo();
				placeInfo.setPlaceID(data.getPlaceID());
				placeInfo.setPeopleCounted(data.getPeopleCounted());
				placeMap.put(data.getPlaceID(), placeInfo);
			} else {
				placeMap.get(data.getPlaceID()).setPeopleCounted(data.getPeopleCounted());
			}
		});

		placeMap.values().forEach(place -> {
			switch (place.getBuildingClass()) {
			case F:
				interventionPlan.getHighestPriority().getPlaceInfos().add(place);
				break;
			case D:
			case E:
				if (place.getPeopleCounted()!= null && place.getPeopleCounted() >= 10) {
					interventionPlan.getHighestPriority().getPlaceInfos().add(place);
				} else {
					interventionPlan.getMediumPriority().getPlaceInfos().add(place);
				}
				break;
			case C:
				if (place.getPeopleCounted() != null && place.getPeopleCounted() >= 40) {
					interventionPlan.getHighestPriority().getPlaceInfos().add(place);
				} else if (place.getPeopleCounted() == null || place.getPeopleCounted() < 10){
					interventionPlan.getLowestPriority().getPlaceInfos().add(place);
				} else {
					interventionPlan.getMediumPriority().getPlaceInfos().add(place);
				}
				break;
			case B:
			case A:
				if (place.getPeopleCounted() != null && place.getPeopleCounted() >= 40) {
					interventionPlan.getMediumPriority().getPlaceInfos().add(place);
					break;
				}
			default:
				interventionPlan.getLowestPriority().getPlaceInfos().add(place);
				break;
			}
		});

		return interventionPlan;
	}

	private static void initInterventionPlan(InterventionPlan interventionPlan) {
		interventionPlan.setHighestPriority(new HighestPriority());
		interventionPlan.setMediumPriority(new MediumPriority());
		interventionPlan.setLowestPriority(new LowestPriority());
	}

	@Override
	public BuildingDataList getBuildingDataList() {

		logger.info("EP (consumer): retreiving buildingData");
		return messageRepository.get("buildingData", BuildingDataList.class);
	}

	@Override
	public Notification getNotificationMessage() {

		logger.info("EP (consumer): retreiving notificationMessage");
		BuildingDataList buildingDataList = messageRepository.get("missingData", BuildingDataList.class);
		Notification notification = new Notification();

		notification.setAlarmCode(buildingDataList.getAlarmCode());

		if (buildingDataList != null) {			
			StringBuilder sb = new StringBuilder();
			buildingDataList.getBuildingData().forEach(b -> sb.append(b.getBuildingID()).append(","));
			notification.setType("Missing data");
			notification.setText(sb.toString());
		}

		return notification;
	}

	@Override
	public InterventionPlan getInterventionPlanUpdate() {

		logger.info("EP (consumer): retreiving buildingDataUpdate and BuildingData");
		BuildingDataList buildingDataUpdateList = messageRepository.get("buildingDataUpdate", BuildingDataList.class);

		InterventionPlan interventionPlan = new InterventionPlan();
		initInterventionPlan(interventionPlan);

		BuildingDataList buildingDataList = messageRepository.get("buildingData", BuildingDataList.class);

		interventionPlan.setAlarmCode(buildingDataUpdateList.getAlarmCode());

		buildingDataUpdateList.getBuildingData().forEach(buildingData -> {

			buildingDataList.getBuildingData().add(buildingData);

			PlaceInfo placeInfo = new PlaceInfo();
			placeInfo.setPlaceID(buildingData.getBuildingID());
			placeInfo.setBuildingClass(buildingData.getStatus());
			
			switch (buildingData.getStatus()) {

			case E:
			case F:
				interventionPlan.getHighestPriority().getPlaceInfos().add(placeInfo);
				break;
			case D:
			case C:
				interventionPlan.getMediumPriority().getPlaceInfos().add(placeInfo);
				break;
			case B:
			case A:
				interventionPlan.getLowestPriority().getPlaceInfos().add(placeInfo);
				break;
			}
		});

		return interventionPlan;
	}


}
