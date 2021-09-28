package com.rms.exception;

//EXCEPTION CLASS FOR ANY BUSINESS LOGIC EXCEPTIONS

public class BusinessLogicException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessLogicException(String message) {
		super(message);
	}
}