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
import com.rms.dto.AddressDto;
import com.rms.response.HttpResponseStatus;
import com.rms.service.AddressService;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	private static final Logger logger = LogManager.getLogger(AddressController.class);


	/**
	 *@param This method takes Address DTO Object as input from request body
	 *@return This method returns success message if address is added successfully as HttpResponseStatus
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addAddress(@Valid @RequestBody AddressDto addressDto) {
		
		logger.info("Entering addAddress method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), addressService.addAddress(addressDto)),
					HttpStatus.OK);
		
	}
	
	/**
	 *@param This method takes phone number as input and returns list of addresses currently in the database with the corresponding number
	 *@return This method returns address data along with success message as HttpResponseStatus 
	 */
	@GetMapping("/phoneNumber/{phoneNumber}")
	public ResponseEntity<HttpResponseStatus> getAddressByPhoneNumber(@PathVariable("phoneNumber") Long phoneNumber) {
		logger.info("Entering getAddressByPhoneNumber method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.ADDRESS_FETCH_SUCCESS,
					addressService.getAddressByPhoneNumber(phoneNumber)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes customer id as input and returns list of addresses currently in the database with the corresponding number
	 *@return This method returns address data of given id along with success message as HttpResponseStatus 
	 */
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<HttpResponseStatus> getAddressByCustomerId(@PathVariable("customerId") Long customerId) {
		logger.info("Entering getAddressByCustomerId method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.ADDRESS_FETCH_SUCCESS,
					addressService.getAddressByCustomerId(customerId)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes address id as input and returns list of addresses currently in the database with the corresponding number
	 *@return This method returns address data for given id along with success message as HttpResponseStatus 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getAddressById(@PathVariable("id") Long id) {
		logger.info("Entering getAddressById method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.ADDRESS_FETCH_SUCCESS, addressService.getAddressById(id)),
					HttpStatus.OK);
		
	}
	
	/**
	 *@param This method takes address id and address DTO object as input 
	 *@return This method returns success message for given id as HttpResponseStatus 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateAddress(@PathVariable Long id,@Valid @RequestBody AddressDto addressDto) {
		
		logger.info("Entering updateAddress method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.ADDRESS_UPDATE_SUCCESS, addressService.updateAddress(id, addressDto)),
					HttpStatus.OK);
		
	}
	
}
