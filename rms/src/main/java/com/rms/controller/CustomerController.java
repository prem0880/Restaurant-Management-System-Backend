package com.rms.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.dto.CustomerDto;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	static final String DATA_SUCCESS="Customer Data Retrieval is Success!";
	
	    
	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addCustomer(@Valid @RequestBody CustomerDto customerDto){
		return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),customerService.addCustomer(customerDto)),HttpStatus.OK);
	
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<HttpResponse> getAllCustomer(){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),DATA_SUCCESS,customerService.getAllCustomer()),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<HttpResponse> getCustomerById(@PathVariable("id") Long id){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),DATA_SUCCESS,customerService.getCustomerById(id)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<HttpResponse> updateCustomer(@PathVariable("id") Long id,@Valid @RequestBody CustomerDto customerDto){
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),customerService.updateCustomer(id, customerDto)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponse> businessException (BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
		

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<HttpResponse> dataBaseException (DataBaseException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
