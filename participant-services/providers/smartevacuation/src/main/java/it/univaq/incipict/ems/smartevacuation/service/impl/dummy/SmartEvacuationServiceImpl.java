package it.univaq.incipict.ems.smartevacuation.service.impl.dummy;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import it.univaq.incipict.ems.smartevacuation.EmergencyType;
import it.univaq.incipict.ems.smartevacuation.service.SmartEvacuationService;

@Service
public class SmartEvacuationServiceImpl implements SmartEvacuationService {

	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Override
	public String sendEvacuationPlan(EmergencyType emergencyType, List<String> areaCodes) {
		
		return RandomStringUtils.random(10, CHARACTERS);
	}

}
