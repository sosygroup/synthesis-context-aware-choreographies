package it.univaq.incipict.mid1.sia.endpoints.manager.services;
		
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import it.univaq.incipict.mid1.sia.endpoints.manager.model.SetInvocationAddress;
import it.univaq.incipict.mid1.sia.endpoints.manager.model.SetInvocationAddressResponse;
import it.univaq.incipict.mid1.sia.endpoints.manager.util.SetInvocationAddressUtils;
import it.univaq.incipict.mid1.sia.endpoints.manager.util.Utils;
import net.sf.saxon.dom.NodeWrapper;

public class SiaEndpointsManagerService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SiaEndpointsManagerService.class);
	
	public static Document storeParticipantAddress(String callingParticipantName, NodeWrapper setInvocationAddressNodeWrapper){
		
		LOGGER.info("CALLED storeParticipantAddress ON Sia Endpoints Manager by " + callingParticipantName);

		SetInvocationAddress setInvocationAddress = (SetInvocationAddress) Utils.unmarshallNodeWrapperToObject(setInvocationAddressNodeWrapper, SetInvocationAddress.class);

		SetInvocationAddressUtils.storeParticipantArtifactEndpointData(callingParticipantName, setInvocationAddress.getParticipantName(), setInvocationAddress.getAddress());
		return Utils.createDocumentFromObject(new SetInvocationAddressResponse());
	}

	public static Document storeSimpleParticipantAddress(String callingParticipantName, String participantName, String invocationAddress){
		
		LOGGER.info("CALLED storeParticipantAddress ON Sia Endpoints Manager by " + callingParticipantName);

		SetInvocationAddressUtils.storeParticipantArtifactEndpointData(callingParticipantName, participantName, invocationAddress);
		return Utils.createDocumentFromObject(new SetInvocationAddressResponse());
	}

	public static String getParticipantAddress(String callingParticipantName, String searchingParticipantName, String defaultWSDLAddress){
		
		LOGGER.info("CALLED getParticipantAddress ON Sia Endpoints Manager by " + callingParticipantName + " to get " + searchingParticipantName + " endpoint");

		String participantAddressRetrieved = SetInvocationAddressUtils.getParticipantArtifactEndpointAddress(callingParticipantName, searchingParticipantName);

		if (participantAddressRetrieved != null) {
//			if (!callingParticipantName.equals(searchingParticipantName)) {
//				//CD to CD
//				if (participantAddressRetrieved.contains("/ode")) {
//					String participantAddress_part1 = participantAddressRetrieved.substring(0, participantAddressRetrieved.lastIndexOf('/'));
//					String participantAddress_part2 = defaultWSDLAddress.substring(defaultWSDLAddress.lastIndexOf('/'));
//					return participantAddress_part1 + participantAddress_part2;	
//				} else { //CD to provider e.g., personalweatherstations
//					String participantAddress_part1 = participantAddressRetrieved.substring(0, participantAddressRetrieved.lastIndexOf('/'));
//					String participantAddress_part2 = getPathUrl(defaultWSDLAddress);
//					return participantAddress_part1 + participantAddress_part2;
//				}
//			} else { //CD to prosumer
//				String participantFromNameWithoutSpace = callingParticipantName.replace(" ", "").toLowerCase();
//				String participantAddress_part1 = participantAddressRetrieved.substring(0, participantAddressRetrieved.lastIndexOf('/'));
//				//String participantAddress_part2 = participantToAddress.substring(participantToAddress.toLowerCase().indexOf(participantFromNameWithoutSpace));
//				String participantAddress_part2 = getPathUrl(defaultWSDLAddress);
//				return participantAddress_part1 + participantAddress_part2;
//			}
			return participantAddressRetrieved;
		} else {
			return defaultWSDLAddress;
		}
	}	

//	private static String getPathUrl(String participantToAddress) {
//		try {
//			URI uri = URI.create(participantToAddress);
//			return uri.getPath();
//		} catch (Exception e) {
//			return "";
//		}
//	}
			
}
