package it.univaq.incipict.ems.crowding.service;

import java.util.List;

import it.univaq.incipict.ems.crowding.model.Place;

public interface CrowdingService {

	public List<Place> retreiveCrowdingData(List<String> areaCodes);
}
