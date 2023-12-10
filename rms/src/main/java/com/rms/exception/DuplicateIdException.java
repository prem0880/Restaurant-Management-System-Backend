package com.rms.exception;

public class DuplicateIdException extends RuntimeException {
	private static final long serialVersionUID = 1;

	public DuplicateIdException(String msg){
		super(msg);
	}
}