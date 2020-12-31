package it.univaq.incipict.ems.energy.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.energy.BuildingList;
import it.univaq.incipict.ems.energy.EnergyPT;
import it.univaq.incipict.ems.energy.monitor.LoggerCollector;

@Component(value = "EnergyPTImpl")
public class EnergyPTImpl implements EnergyPT {

	private static Logger logger = LoggerFactory.getLogger(EnergyPTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void shutdownPlants(BuildingList parameters) {
		
		logger.info("Energy: received shutdownPlants request message (InstanceId " + parameters.getAlarmCode() + ")");
		loggerCollector.collectEnd("Shutdown Plants", parameters.getAlarmCode());
	}

}
