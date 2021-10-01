package com.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.dto.AddressDto;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.AddressService;

@RestController
@RequestMapping("/address")
@CrossOrigin("http://localhost:4200")
public class AddressController {

	static final String DATA_SUCCESS = "Address Data Retrieval is Success";

	@Autowired
	private AddressService addressService;

	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addAddress(@RequestBody AddressDto addressDto) {
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), addressService.addAddress(addressDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/phoneNumber/{phoneNumber}")
	public ResponseEntity<HttpResponse> getAddressByPhoneNumber(@PathVariable("phoneNumber") Long phoneNumber) {
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), DATA_SUCCESS,
					addressService.getAddressByPhoneNumber(phoneNumber)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/customer/{customerId}")
	public ResponseEntity<HttpResponse> getAddressByCustomerId(@PathVariable("customerId") Long customerId) {
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), DATA_SUCCESS,
					addressService.getAddressByCustomerId(customerId)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<HttpResponse> getAddressById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(
					new HttpResponse(HttpStatus.OK.value(), DATA_SUCCESS, addressService.getAddressById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponse> businessException(BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<HttpResponse> dataBaseException(DataBaseException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}
