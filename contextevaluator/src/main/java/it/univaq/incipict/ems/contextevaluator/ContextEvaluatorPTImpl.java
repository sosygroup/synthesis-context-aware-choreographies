package it.univaq.incipict.ems.contextevaluator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import it.univaq.incipict.ems.contextevaluator.utils.LoggingUtils;

@Component(value = "ContextEvaluatorPTImpl")
@PropertySource("classpath:context.properties")
public class ContextEvaluatorPTImpl implements ContextEvaluatorPT {

	private final Logger logger = LoggerFactory.getLogger(ContextEvaluatorPTImpl.class);

	@Value("${context.latency}")
	private Integer latency;

	@Value("${context.datarate}")
	private Integer dataRate;

	@Override
	public ContextResponse getChoreographyContext(ContextRequest request) {

		logger.info("Context Evaluator: evaluating choreography context " + request.getContextName());

		if ("DroneReconnaissance Context".equals(request.getContextName())) {

			// Evaluate conditions for context "DroneReconnaissance":
			ContextResponse response = new ContextResponse();
			response.setContext(request.getContextName());

			Condition condition = new Condition();
			condition.setName("Network Maximum Available Data Rate");
			condition.setCurrentCondition(Integer.toString(dataRate));
			response.getCondition().add(condition);

			condition = new Condition();
			condition.setName("Network Latency");
			condition.setCurrentCondition(Integer.toString(latency));
			response.getCondition().add(condition);

			logger.info("Context Evaluator: evaluated context " + LoggingUtils.logResponse(response));

			return response;

		} else {
			throw new RuntimeException("Context name unknown: " + request.getContextName());
		}
	}

}
