package it.univaq.incipict.ems.smartevacuation.service;

import java.util.List;

import it.univaq.incipict.ems.smartevacuation.EmergencyType;

public interface SmartEvacuationService {

	public String sendEvacuationPlan(EmergencyType emergencyType, List<String> areaCodes);
}
