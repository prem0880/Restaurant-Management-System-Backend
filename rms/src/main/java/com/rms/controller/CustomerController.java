package com.rms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.rms.entity.Customer;
import com.rms.exception.IdNotFoundException;
import com.rms.service.CustomerService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	    
	@PostMapping("/customer")
	public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDto customerDto){
		return new ResponseEntity<>(customerService.addCustomer(customerDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		return new ResponseEntity<>(customerService.getAllCustomer(),new HttpHeaders(),HttpStatus.OK);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
		return new ResponseEntity<>(customerService.getCustomerById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id,@Valid @RequestBody CustomerDto customerDto){
		return new ResponseEntity<>(customerService.updateCustomer(id, customerDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
