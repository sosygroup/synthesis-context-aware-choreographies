package it.univaq.incipict.ems.earlywarningsystem.client;

public interface EarlyWarningSystemClient {

	public void signalEvent(String eventCode);

	public void signalEndEvent(String eventCode);
}
