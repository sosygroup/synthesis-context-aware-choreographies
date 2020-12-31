package it.univaq.incipict.ems.drone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import it.univaq.incipict.ems.drone.client.DroneClient;
import it.univaq.incipict.ems.seismographicnetwork.providers.DroneConfiguration;

@ComponentScan(basePackages = "it.univaq.incipict.ems.drone.client")
public class DroneApp  {

	private static Logger logger = LoggerFactory.getLogger(DroneApp.class);

	@Autowired
	private DroneClient client;

    public static void main(String[] args) {

    	ApplicationContext context = new AnnotationConfigApplicationContext(DroneApp.class);

    	DroneApp app = context.getBean(DroneApp.class);
        app.start(args);

        ((ConfigurableApplicationContext)context).close();

    }

    public void start(String[] args) {

    	logger.info("Drone: started application");

    	DroneConfiguration configuration = client.getDroneConfiguration();

    	if (configuration.isLiveImages()) {

    		logger.info("Drone: sending live photogrammetry");
    		client.sendLivePhotogrammetry(configuration.getFlightId(), configuration.getCheckpoints());
    		
    	} else {

    		logger.info("Drone: sending checkpoint description");
    		client.sendCheckpointDescription(configuration.getFlightId(), configuration.getCheckpoints());

    	}

    	client.sendLogs();
    }

}
