package it.univaq.incipict.ems.earlywarningsystem.client.utils;

import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.earlywarningsystem.client.properties.CoordinationDelegatesProperties;
import it.univaq.incipict.ems.earlywarningsystem.providers.CdEarlywarningsystemPT;
import it.univaq.incipict.ems.earlywarningsystem.providers.CdEarlywarningsystemService;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdBsaPT;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdBsaService;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdEpPT;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdEpService;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdSaePT;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdSaeService;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdShmPT;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.CdShmService;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.ECDPT;
import it.univaq.incipict.ems.earlywarningsystem.utils.cds.ECDService;


@Component
public class CDUtils {

	@Autowired
	private CoordinationDelegatesProperties properties;

	public void prepare(String instanceId) {

		CdEarlywarningsystemService cdEarlywarningsystemService = new CdEarlywarningsystemService();
		CdEarlywarningsystemPT cdEarlywarningsystemPT = cdEarlywarningsystemService.getCdEarlywarningsystemPort();
		((BindingProvider)cdEarlywarningsystemPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.cdEarlyWarningSystemURL);
		cdEarlywarningsystemPT.prepare(instanceId);

		CdBsaService cdBsaService = new CdBsaService();
		CdBsaPT cdBsaPT = cdBsaService.getCdBsaPort();
		((BindingProvider)cdBsaPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.cdBsaURL);
		cdBsaPT.prepare(instanceId);

		CdEpService cdEpService = new CdEpService();
		CdEpPT cdEpPT = cdEpService.getCdEpPort();
		((BindingProvider)cdBsaPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.cdEpURL);
		cdEpPT.prepare(instanceId);

		CdSaeService cdSaeService = new CdSaeService();
		CdSaePT cdSaePT = cdSaeService.getCdSaePort();
		((BindingProvider)cdBsaPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.cdSaeURL);
		cdSaePT.prepare(instanceId);

		CdShmService cdShmService = new CdShmService();
		CdShmPT cdShmPT = cdShmService.getCdShmPort();
		((BindingProvider)cdBsaPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.cdShmURL);
		cdShmPT.prepare(instanceId);

		ECDService ecdService = new ECDService();
		ECDPT ecdpt = ecdService.getECDPort();
		((BindingProvider)cdBsaPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.eCdURL);
		ecdpt.prepare(instanceId);

	}
}
