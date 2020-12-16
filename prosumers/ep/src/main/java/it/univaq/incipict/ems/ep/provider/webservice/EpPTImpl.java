package it.univaq.incipict.ems.ep.provider.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.ep.BuildingDataList;
import it.univaq.incipict.ems.ep.EpPT;
import it.univaq.incipict.ems.ep.consumer.service.MessageStoringService;
import it.univaq.incipict.ems.ep.monitor.LoggerCollector;

@Component(value = "EpPTImpl")
public class EpPTImpl implements EpPT {

	Logger logger = LoggerFactory.getLogger(EpPTImpl.class);

	@Autowired
	private MessageStoringService service;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void sendBuildingData(BuildingDataList request) {

		logger.info("EP: received sendBuildingData request message");
		service.storeSendBuildingDataRequest(request);
		loggerCollector.collectEnd("Send Building Data", request.getAlarmCode());
	}

	@Override
	public void sendMissingDataAcknowledgement(BuildingDataList request) {

		logger.info("EP: received sendMissingDataAcknowledgement request message");
		loggerCollector.collectEnd("Send Missing Data Acknowledgement", request.getAlarmCode());
	}

	@Override
	public void updateBuildingData(BuildingDataList request) {

		logger.info("EP: received updateBuildingData request message");
		loggerCollector.collectEnd("Update Building Data", request.getAlarmCode());
	}

}
