package it.univaq.incipict.ems.earlywarningsystem;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import it.univaq.incipict.ems.earlywarningsystem.client.EarlyWarningSystemClient;
import it.univaq.incipict.ems.earlywarningsystem.client.properties.EarlyWarningSystemProperties;
import it.univaq.incipict.ems.earlywarningsystem.client.utils.CDUtils;

@ComponentScan(basePackages = "it.univaq.incipict.ems.earlywarningsystem.client")
public class EarlyWarningSystemApp  {

	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static Logger logger = LoggerFactory.getLogger(EarlyWarningSystemApp.class);

	@Autowired
	private EarlyWarningSystemClient client;

	@Autowired
	private CDUtils cdUtils;

	@Autowired
	private EarlyWarningSystemProperties properties;

    public static void main(String[] args) {

    	ApplicationContext context = new AnnotationConfigApplicationContext(EarlyWarningSystemApp.class);

    	EarlyWarningSystemApp app = context.getBean(EarlyWarningSystemApp.class);
        app.start(args);

        ((ConfigurableApplicationContext)context).close();
    }

    private void start(String[] args) {

    	logger.info("EarlyWarningSystem: started application");

    	for (int i = 0; i < properties.instances; i++) {
    		String eventCode = RandomStringUtils.random(10, CHARACTERS);

    		logger.info("Preparing CDs...");
    		
    		cdUtils.prepare(eventCode);

    		logger.info("STARTING INSTANCE " + eventCode + " WITH DURATION " + properties.earthquakeDuration + " s");
        	client.signalEvent(eventCode);

        	try {
    			Thread.sleep(properties.earthquakeDuration * 1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}

        	client.signalEndEvent(eventCode);

        	try {
    			Thread.sleep(5000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

}
