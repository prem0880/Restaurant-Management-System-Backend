package com.rms.exceptionhandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.DuplicateIdException;
import com.rms.exception.IdNotFoundException;
import com.rms.exception.NoRecordFoundException;
import com.rms.response.HttpResponseStatus;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler 
{
	
	private static final Logger logger = LogManager.getLogger(RestResponseEntityExceptionHandler.class);

	
	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> businessException(BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<HttpResponseStatus> dataBaseException(DataBaseException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<HttpResponseStatus> userNotFound(IdNotFoundException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<HttpResponseStatus> noRecordFound(NoRecordFoundException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<HttpResponseStatus> userNotFound(DuplicateIdException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CONFLICT.value(), e.getMessage()),
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<HttpResponseStatus> invalidInputArgumentsFound(MethodArgumentTypeMismatchException e) {
	logger.error(e.getMessage());
	return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(),"Invalid Input Type"),
	HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponseStatus> handleValidationExceptions(MethodArgumentNotValidException ex) {
	Map<String, String> errors = new HashMap<>();
	List<String > l=new ArrayList<>();
	ex.getBindingResult().getAllErrors().forEach(error -> {
	String fieldName = error.getObjectName();
	String errorMessage = error.getDefaultMessage();
	l.add(errorMessage);
	errors.put(fieldName, errorMessage);
	});
	return new ResponseEntity<>(
			new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(),l.get(0)),
			HttpStatus.UNPROCESSABLE_ENTITY);

	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<HttpResponseStatus> inputMismatch(HttpMessageNotReadableException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}
		
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponseStatus> internalServerErrorFound(Exception e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}



}
