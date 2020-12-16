package it.univaq.incipict.ems.drone.client.exception;

public class DroneClientException extends RuntimeException{

	private static final long serialVersionUID = -6223624353516670973L;

	public DroneClientException() {
		super();
	}

	public DroneClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DroneClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public DroneClientException(String message) {
		super(message);
	}

	public DroneClientException(Throwable cause) {
		super(cause);
	}

}
