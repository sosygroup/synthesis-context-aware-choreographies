package it.univaq.incipict.ems.crowding.webservices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.crowding.CrowdingData;
import it.univaq.incipict.ems.crowding.CrowdingDataRequest;
import it.univaq.incipict.ems.crowding.CrowdingDataResponse;
import it.univaq.incipict.ems.crowding.CrowdingPT;
import it.univaq.incipict.ems.crowding.model.Place;
import it.univaq.incipict.ems.crowding.service.CrowdingService;


@Component(value = "CrowdingPTImpl")
public class CrowdingPTImpl implements CrowdingPT {

	private static Logger logger = LoggerFactory.getLogger(CrowdingPTImpl.class);

	@Autowired
	private CrowdingService crowdingService;

	@Override
	public CrowdingDataResponse getCrowdingData(CrowdingDataRequest request) {

		logger.info("Crowding: received getCrowdingData request message");

		List<Place> places = crowdingService.retreiveCrowdingData(request.getAreaCode());

		CrowdingDataResponse response = new CrowdingDataResponse();
		response.setRequestId(request.getRequestId());

		places.forEach(place -> {
			CrowdingData crowdingData = new CrowdingData();
			crowdingData.setAreaCode(place.getAreaCode());
			crowdingData.setPlaceID(place.getId());
			crowdingData.setPeopleCounted(place.getPeopleCount());
			response.getData().add(crowdingData);
		});

		logger.info("Crowding: replied back with counted people number");
		return response;
	}
	

}
