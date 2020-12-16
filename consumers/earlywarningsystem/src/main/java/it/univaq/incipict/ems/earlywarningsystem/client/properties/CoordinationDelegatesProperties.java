package it.univaq.incipict.ems.earlywarningsystem.client.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CoordinationDelegatesProperties {

	@Value("${targetendpoint}")
	public String cdEarlyWarningSystemURL;

	@Value("${cds.cdbsa}")
	public String cdBsaURL;

	@Value("${cds.cdep}")
	public String cdEpURL;

	@Value("${cds.cdsae}")
	public String cdSaeURL;

	@Value("${cds.cdshm}")
	public String cdShmURL;

	@Value("${cds.ecd}")
	public String eCdURL;
}
