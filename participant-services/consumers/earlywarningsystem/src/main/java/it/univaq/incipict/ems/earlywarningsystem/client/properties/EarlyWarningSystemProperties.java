package it.univaq.incipict.ems.earlywarningsystem.client.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class EarlyWarningSystemProperties {

	@Value("${targetendpoint}")
	public String cdEarlyWarningSystemURL;

	@Value("${earthquake.duration}")
	public Integer earthquakeDuration;

	@Value("${instances}")
	public Integer instances;

}
