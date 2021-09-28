package com.rms.exception;

//EXCEPTION CLASS FOR ANY DATABASE SERVICE EXCEPTIONS.

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataBaseException(String message) {
		super(message);
	}
}