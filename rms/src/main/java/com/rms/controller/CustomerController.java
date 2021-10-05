package com.rms.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.CustomerDto;
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	/**
	 *@param This method takes customer object as input and returns message if created successfully
     *@return throws exception if error occurs
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
		logger.info("Entering addCustomer method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.CUSTOMER_SAVE_SUCCESS,
					customerService.addCustomer(customerDto)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * 
	 * @param This method takes customer object as input,checks credential if found matches in database
	 * @return success message or exception
	 */
	@PostMapping("/login")
	public ResponseEntity<HttpResponseStatus> customerLogin(@RequestBody CustomerDto customerDto) {
		logger.info("Entering customerLogin method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.CUSTOMER_LOGIN_SUCCESS,
					customerService.customerLogin(customerDto)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param returns list of customer currently in the database if data exists
	 *@return If no data present,it return empty list
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCustomer() {
		logger.info("Entering getAllCustomer method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMER_FETCH_SUCCESS, customerService.getAllCustomer()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 *@param This method takes id as input and returns customer object currently in the database
	 *@return If no data present,it return empty list
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCustomerById(@PathVariable("id") Long id) {
		logger.info("Entering getCustomerById method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.CUSTOMER_FETCH_SUCCESS, customerService.getCustomerById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 *@param This method takes customer object and id as input and returns message if updated successfully with id
     *@return throws exception if error occurs
	 */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateCustomer(@PathVariable("id") Long id,
			@Valid @RequestBody CustomerDto customerDto) {
		logger.info("Entering updateCustomer method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), customerService.updateCustomer(id, customerDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
