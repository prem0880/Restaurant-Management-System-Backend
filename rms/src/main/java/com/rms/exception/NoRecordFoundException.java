package com.rms.exception;

public class NoRecordFoundException extends RuntimeException{
	private static final long serialVersionUID = 1;

	public NoRecordFoundException(String msg) {
		super(msg);
	}
}