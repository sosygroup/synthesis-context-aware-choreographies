package it.univaq.incipict.ems.cityalarming.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.cityalarming.AlarmInfo;
import it.univaq.incipict.ems.cityalarming.CityalarmingPT;
import it.univaq.incipict.ems.cityalarming.monitor.LoggerCollector;

@Component(value = "CityalarmingPTImpl")
public class CityalarmingPTImpl implements CityalarmingPT {

	private static Logger logger = LoggerFactory.getLogger(CityalarmingPTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void activateAlarm(AlarmInfo parameters) {

		logger.info("CityAlarming: received activate alarm request message (InstanceId " + parameters.getAlarmCode() + ")");
		loggerCollector.collectEnd("Activate Alarm", parameters.getAlarmCode());
	}

}
