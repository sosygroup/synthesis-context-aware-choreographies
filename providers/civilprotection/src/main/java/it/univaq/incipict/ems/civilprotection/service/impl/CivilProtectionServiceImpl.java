package it.univaq.incipict.ems.civilprotection.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.civilprotection.BuildingData;
import it.univaq.incipict.ems.civilprotection.BuildingDataList;
import it.univaq.incipict.ems.civilprotection.InterventionPlan;
import it.univaq.incipict.ems.civilprotection.Notification;
import it.univaq.incipict.ems.civilprotection.PlaceInfo;
import it.univaq.incipict.ems.civilprotection.domain.Building;
import it.univaq.incipict.ems.civilprotection.domain.BuildingInfo;
import it.univaq.incipict.ems.civilprotection.domain.NotificationList;
import it.univaq.incipict.ems.civilprotection.repository.MessageRepository;
import it.univaq.incipict.ems.civilprotection.service.CivilProtectionService;

@Service
public class CivilProtectionServiceImpl implements CivilProtectionService {

	private static final String BUILDING_FILE = "places_db.txt";

	private static Map<String, Building> buildings;

	@Autowired
	MessageRepository messageRepository;

	@Override
	public void storeBuildingData(BuildingDataList buildingDataList) {
		messageRepository.store("buildingDataList", buildingDataList);
	}

	@Override
	public void storeInterventionPlan(InterventionPlan interventionPlan) {
		messageRepository.store("interventionPlan", interventionPlan);
	}

	@Override
	public InterventionPlan getInterventionPlan() {

		InterventionPlan interventionPlan = messageRepository.get("interventionPlan", InterventionPlan.class);

		return interventionPlan != null ? interventionPlan : new InterventionPlan();
	}

	@Override
	public List<BuildingInfo> getBuildingData() {

		InterventionPlan interventionPlan = messageRepository.get("interventionPlan", InterventionPlan.class);

		List<BuildingInfo> buildingInfo;
		

		if (interventionPlan == null) {
			BuildingDataList buildingDataList = messageRepository.get("buildingDataList", BuildingDataList.class);
			buildingInfo = buildingDataList.getBuildingData().stream().map(building -> toBuildingInfo(building)).collect(Collectors.toList());

		} else {
			buildingInfo = new ArrayList<>();
			buildingInfo.addAll(interventionPlan.getHighestPriority().getPlaceInfos()
					.stream().map(place -> toBuildingInfo(place)).collect(Collectors.toList()));
			buildingInfo.addAll(interventionPlan.getMediumPriority().getPlaceInfos()
					.stream().map(place -> toBuildingInfo(place)).collect(Collectors.toList()));
			buildingInfo.addAll(interventionPlan.getLowestPriority().getPlaceInfos()
					.stream().map(place -> toBuildingInfo(place)).collect(Collectors.toList()));
		}

		return buildingInfo;
	}

	private static BuildingInfo toBuildingInfo(PlaceInfo place) {

		Building building = new Building();
		building.setId(place.getPlaceID());

		BuildingInfo buildingInfo = new BuildingInfo();

		buildingInfo.setBuilding(building);
		buildingInfo.setBuildingClass(place.getBuildingClass());
		buildingInfo.setPeopleCounted(place.getPeopleCounted());
		return buildingInfo;
	}

	private static BuildingInfo toBuildingInfo(BuildingData buildingData) {
		Building building = new Building();
		building.setId(buildingData.getBuildingID());

		BuildingInfo buildingInfo = new BuildingInfo();

		buildingInfo.setBuilding(building);
		buildingInfo.setBuildingClass(buildingData.getStatus());
		return buildingInfo;

	}

	@Override
	public Building getBuilding(String buildingId) {

		if (buildings == null) {
			buildings = new HashMap<>();

			try (Scanner scanner = new Scanner(new ClassPathResource(BUILDING_FILE).getFile())) {

				while (scanner.hasNextLine()) {
					String buildingLine = scanner.nextLine();
					String[] infos = buildingLine.split("\t");

					if (infos.length == 4) {
						Building building = new Building();
						building.setAreaCode(infos[0]);
						building.setId(infos[1]);
						building.setName(infos[3]);

						String[] coordinates = infos[2].split(", ");
						building.setLat(Float.parseFloat(coordinates[0]));
						building.setLon(Float.parseFloat(coordinates[1]));

						buildings.put(building.getId(), building);
					}
				}

				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return buildings.get(buildingId);
	}

	@Override
	public void updateInterventionPlan(InterventionPlan interventionPlanUpdate) {

		InterventionPlan interventionPlan = messageRepository.get("interventionPlan", InterventionPlan.class);

		if (interventionPlan == null) {
			interventionPlan = new InterventionPlan();
		}

		interventionPlan.getHighestPriority().getPlaceInfos().addAll(interventionPlanUpdate.getHighestPriority().getPlaceInfos());
		interventionPlan.getMediumPriority().getPlaceInfos().addAll(interventionPlanUpdate.getMediumPriority().getPlaceInfos());
		interventionPlan.getLowestPriority().getPlaceInfos().addAll(interventionPlanUpdate.getLowestPriority().getPlaceInfos());

		messageRepository.store("interventionPlan", interventionPlan);
	}

	@Override
	public void storeNotification(Notification notification) {
		
		NotificationList notificationList = messageRepository.get("notifications", NotificationList.class);

		if (notificationList == null) {
			notificationList = new NotificationList();
		}

		notificationList.getNotifications().add(notification);
		messageRepository.store("notifications", notificationList);
		
	}

	@Override
	public List<Notification> getNotifications() {

		NotificationList notificationList = messageRepository.get("notifications", NotificationList.class);

		messageRepository.store("notifications", new NotificationList());
		return notificationList.getNotifications();
	}

}
