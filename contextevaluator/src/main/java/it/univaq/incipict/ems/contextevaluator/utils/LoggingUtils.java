package it.univaq.incipict.ems.contextevaluator.utils;

import it.univaq.incipict.ems.contextevaluator.ContextResponse;

public class LoggingUtils {

	public static String logResponse(ContextResponse response) {
		StringBuilder sb = new StringBuilder();

		response.getCondition().forEach(condition -> {
			sb.append('{').append(condition.getName()).append(':').append(condition.getCurrentCondition()).append('}');
		});
		return sb.toString();
	}

}
