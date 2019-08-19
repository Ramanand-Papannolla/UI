package com.owner.reconnect.exception;

public class AlreadyPlotExistedException extends ReconnectException {
	private static final long serialVersionUID = 1L;

	public AlreadyPlotExistedException() {
	}

	public AlreadyPlotExistedException(String message) {
		super(message);
	}

}
