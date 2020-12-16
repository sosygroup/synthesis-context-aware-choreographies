package it.univaq.incipict.ems.shm.consumer.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univaq.incipict.ems.shm.consumer.repository.MessageRepository;

@Repository
public class MessageRepositoryDummyImpl implements MessageRepository {

	private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryDummyImpl.class);

	static Map<String, String> repository;

	static {
		repository = new HashMap<>();
		logger.info("Initializing SHM repository");
	}


	@Override
	public <T> T get(String messageName, Class<T> t) {

		String messageJson = repository.get(messageName);

		if (messageJson != null) {
			ObjectMapper mapper = new ObjectMapper();

			try {
				return mapper.readValue(messageJson, t);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}


	@Override
	public <T> void store(String messageName, T message) {
		ObjectMapper mapper = new ObjectMapper();
		String messageJson;

		try {
			messageJson = mapper.writeValueAsString(message);
			repository.put(messageName, messageJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
