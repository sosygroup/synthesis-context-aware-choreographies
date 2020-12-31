package it.univaq.incipict.ems.crowding.service.impl.dummy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.crowding.model.Place;
import it.univaq.incipict.ems.crowding.service.CrowdingService;

@Service
public class CrowdingServiceImpl implements CrowdingService {

	@Override
	public List<Place> retreiveCrowdingData(List<String> areaCodes) {

		List<Place> places = new ArrayList<>();

		places.add(new Place("0001", "AQ01", (int) Math.round(Math.random()*100)));
		places.add(new Place("0002", "AQ01", (int) Math.round(Math.random()*100)));
		places.add(new Place("0003", "AQ01", (int) Math.round(Math.random()*100)));
		places.add(new Place("0004", "AQ01", (int) Math.round(Math.random()*100)));
		places.add(new Place("0005", "AQ02", (int) Math.round(Math.random()*100)));
		places.add(new Place("0006", "AQ02", (int) Math.round(Math.random()*100)));
		places.add(new Place("0007", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0008", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0009", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0010", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0011", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0012", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0013", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0014", "AQ03", (int) Math.round(Math.random()*100)));
		places.add(new Place("0015", "AQ03", (int) Math.round(Math.random()*100)));

		return places;
	}

}
