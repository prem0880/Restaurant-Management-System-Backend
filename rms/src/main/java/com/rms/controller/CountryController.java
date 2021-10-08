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
	 *@param  This method takes no input 
	 *@return This method returns list of country currently in the database if data exists.If no data present,it return empty list
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCountry() {
		logger.debug("Entering getAllCountry method");
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
	 *@param This method takes country DTO object as input
     *@return This method returns success message if country is created successfully
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCountry(@RequestBody CountryDto countryDto) {
		logger.debug("Entering addCountry method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), countryService.addCountry(countryDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes country id as input
	 *@return This method returns country object for given id currently in the database.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCountryById(@PathVariable Long id) {
		logger.debug("Entering getCountryById method");
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
	 *@param This method takes country DTO object and id as input
	 *@return This method returns success message if country is updated successfully with id
     */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
		logger.debug("Entering updateCountry method");
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
	 *@return This method returns success message if country is deleted successfully with id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteCountry(@PathVariable Long id) {
		logger.debug("Entering deleteCountry method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), countryService.deleteCountry(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}


}
