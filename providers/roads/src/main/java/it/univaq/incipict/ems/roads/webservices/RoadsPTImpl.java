package it.univaq.incipict.ems.roads.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.roads.CloseRoadsList;
import it.univaq.incipict.ems.roads.RoadsPT;
import it.univaq.incipict.ems.roads.monitor.LoggerCollector;

@Component(value = "RoadsPTImpl")
public class RoadsPTImpl implements RoadsPT {

	private static Logger logger = LoggerFactory.getLogger(RoadsPTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void closeRoads(CloseRoadsList parameters) {

		logger.info("Roads: received closeRoads request message");
		loggerCollector.collectEnd("Close Roads", parameters.getAlarmCode());
	}

}
