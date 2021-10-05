package com.rms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.CountryDto;
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.CountryService;

@RestController
@RequestMapping("/country")
@CrossOrigin("*")
public class CountryController {

	@Autowired
	private CountryService countryService;

	private static final Logger logger = LogManager.getLogger(CountryController.class);

	/**
	 *@param returns list of country currently in the database if data exists
	 *@return If no data present,it return empty list
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCountry() {
		logger.info("Entering getAllCountry method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.COUNTRY_FETCH_SUCCESS, countryService.getAllCountry()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes country object as input and returns message if created successfully
     *@return throws exception if error occurs
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCountry(@RequestBody CountryDto countryDto) {
		logger.info("Entering addCountry method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), countryService.addCountry(countryDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes id as input and returns country object currently in the database
	 *@return If no data present,it return empty list
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCountryById(@PathVariable Long id) {
		logger.info("Entering getCountryById method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.COUNTRY_FETCH_SUCCESS, countryService.getCountryById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes country object and id as input and
	 *@return returns message if updated successfully with id
     *@exception throws exception if error occurs
	 */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
		logger.info("Entering updateCountry method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), countryService.updateCountry(id, countryDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes country id as input
	 *@return returns message if deleted successfully with id
	 *@exception throws exception if error occurs
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteCountry(@PathVariable Long id) {
		logger.info("Entering deleteCountry method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), countryService.deleteCountry(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}


}
