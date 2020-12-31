package it.univaq.incipict.ems.buildingautomation.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.buildingautomation.AlarmInfo;
import it.univaq.incipict.ems.buildingautomation.BuildingautomationPT;
import it.univaq.incipict.ems.buildingautomation.monitor.LoggerCollector;

@Component(value = "BuildingAutomationPTImpl")
public class BuildingautomationPTImpl implements BuildingautomationPT {

	private static Logger logger = LoggerFactory.getLogger(BuildingautomationPTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void signalStopElevators(AlarmInfo parameters) {
		
		logger.info("BuildingAutomation: received signal stop elevators request message (instanceId " + parameters.getAlarmCode() + ")");
		loggerCollector.collectEnd("Signal Stop Elevators", parameters.getAlarmCode());
	}

}
