package it.univaq.incipict.ems.shm.consumer.repository;

public interface MessageRepository {

	public <T> T get(String messageName, Class<T> t);

	public <T> void store(String messageName, T object);
}
