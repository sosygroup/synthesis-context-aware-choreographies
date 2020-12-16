package it.univaq.incipict.mid1.sia.endpoints.client.impl;

import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.sia.endpoints.cds.model.CdBsaSetInvocationAddressService;
import it.univaq.incipict.ems.sia.endpoints.cds.model.CdDroneSetInvocationAddressService;
import it.univaq.incipict.ems.sia.endpoints.cds.model.CdEarlywarningsystemSetInvocationAddressService;
import it.univaq.incipict.ems.sia.endpoints.cds.model.CdEpSetInvocationAddressService;
import it.univaq.incipict.ems.sia.endpoints.cds.model.CdSaeSetInvocationAddressService;
import it.univaq.incipict.ems.sia.endpoints.cds.model.CdShmSetInvocationAddressService;
import it.univaq.incipict.ems.sia.endpoints.cds.model.ECDSetInvocationAddressService;
import it.univaq.incipict.ems.sia.endpoints.cds.model.SetInvocationAddressPT;
import it.univaq.incipict.mid1.sia.endpoints.client.properties.SiaEndpointsClientProperties;

@Component
public class SiaEndpointsClientImpl {

	@Autowired
	private SiaEndpointsClientProperties properties;

	public void setConfigurationAddress() {

		SetInvocationAddressPT pt;

		// CD_BSA
		CdBsaSetInvocationAddressService cdBsaSetInvocationAddressService = new CdBsaSetInvocationAddressService();
		pt = cdBsaSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cdbsa.sia"));

		pt.setInvocationAddress("BsaProvider", properties.getValue("prosumers.bsa.provider"));
		pt.setInvocationAddress("BsaConsumer", properties.getValue("prosumers.bsa.consumer"));
		pt.setInvocationAddress("BuildingAutomation", properties.getValue("providers.buildingautomation"));
		pt.setInvocationAddress("Energy", properties.getValue("providers.energy"));

		// CD_Drone
		CdDroneSetInvocationAddressService cdDroneSetInvocationAddressService = new CdDroneSetInvocationAddressService();
		pt = cdDroneSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cddrone.sia"));

		pt.setInvocationAddress("cdShm", properties.getValue("cds.cdshm"));


		// CD_EarlyWarningSystem
		CdEarlywarningsystemSetInvocationAddressService cdEarlywarningsystemSetInvocationAddressService = new CdEarlywarningsystemSetInvocationAddressService();
		pt = cdEarlywarningsystemSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cdearlywarningsystem.sia"));

		pt.setInvocationAddress("cdSae", properties.getValue("cds.cdsae"));
		pt.setInvocationAddress("cdShm", properties.getValue("cds.cdshm"));
		pt.setInvocationAddress("cdSaeCoordination", properties.getValue("cds.cdsae.coordination"));


		// CD_EP
		CdEpSetInvocationAddressService cdEpSetInvocationAddressService = new CdEpSetInvocationAddressService();
		pt = cdEpSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cdep.sia"));

		pt.setInvocationAddress("EpProvider", properties.getValue("prosumers.ep.provider"));
		pt.setInvocationAddress("EpConsumer", properties.getValue("prosumers.ep.consumer"));
		pt.setInvocationAddress("CivilProtection", properties.getValue("providers.civilprotection"));
		pt.setInvocationAddress("Crowding", properties.getValue("providers.crowding"));


		// CD_SAE
		CdSaeSetInvocationAddressService cdSaeSetInvocationAddressService = new CdSaeSetInvocationAddressService();
		pt = cdSaeSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cdsae.sia"));

		pt.setInvocationAddress("SaeProvider", properties.getValue("prosumers.sae.provider"));
		pt.setInvocationAddress("SaeConsumer", properties.getValue("prosumers.sae.consumer"));
		pt.setInvocationAddress("cdBsa", properties.getValue("cds.cdbsa"));
		pt.setInvocationAddress("cdShmCoordination", properties.getValue("cds.cdshm.coordination"));
		pt.setInvocationAddress("CityAlarming", properties.getValue("providers.cityalarming"));
		pt.setInvocationAddress("SmartEvacuation", properties.getValue("providers.smartevacuation"));


		// CD_SHM
		CdShmSetInvocationAddressService cdShmSetInvocationAddressService = new CdShmSetInvocationAddressService();
		pt = cdShmSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cdshm.sia"));

		pt.setInvocationAddress("ShmProvider", properties.getValue("prosumers.shm.provider"));
		pt.setInvocationAddress("ShmConsumer", properties.getValue("prosumers.shm.consumer"));
		pt.setInvocationAddress("cdBsa", properties.getValue("cds.cdbsa"));
		pt.setInvocationAddress("cdEp", properties.getValue("cds.cdep"));
		pt.setInvocationAddress("eCD", properties.getValue("cds.ecd"));
		pt.setInvocationAddress("Roads", properties.getValue("providers.roads"));
		pt.setInvocationAddress("SensorNetwork", properties.getValue("providers.sensornetwork"));
		pt.setInvocationAddress("DroneFleetController", properties.getValue("providers.dronefleetcontroller"));


		// eCD
		ECDSetInvocationAddressService ecdSetInvocationAddressService = new ECDSetInvocationAddressService();
		pt = ecdSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.ecd.sia"));

		pt.setInvocationAddress("cdShm", properties.getValue("cds.cdshm"));
		pt.setInvocationAddress("cdEp", properties.getValue("cds.cdep"));
		pt.setInvocationAddress("ContextEvaluator", properties.getValue("contextevaluator"));

	}
}
