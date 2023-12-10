package com.rms.exception;

public class IdNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1;

	public IdNotFoundException(String msg){
		super(msg);
	}

}