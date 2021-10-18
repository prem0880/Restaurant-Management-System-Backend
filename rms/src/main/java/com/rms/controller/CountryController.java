package com.rms.controller;

import javax.validation.Valid;

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
		logger.info("Entering getAllCountry method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.COUNTRY_FETCH_SUCCESS, countryService.getAllCountry()),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes country DTO object as input
     *@return This method returns success message if country is created successfully
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCountry(@Valid @RequestBody CountryDto countryDto) {
		logger.info("Entering addCountry method");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), countryService.addCountry(countryDto)),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes country id as input
	 *@return This method returns country object for given id currently in the database.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCountryById(@Valid @PathVariable Long id) {
		logger.info("Entering getCountryById method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.COUNTRY_FETCH_SUCCESS, countryService.getCountryById(id)),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes country DTO object and id as input
	 *@return This method returns success message if country is updated successfully with id
     */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateCountry(@PathVariable Long id,@Valid @RequestBody CountryDto countryDto) {
		logger.info("Entering updateCountry method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), countryService.updateCountry(id, countryDto)),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes country id as input
	 *@return This method returns success message if country is deleted successfully with id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteCountry(@PathVariable Long id) {
		logger.info("Entering deleteCountry method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), countryService.deleteCountry(id)),
					HttpStatus.OK);
		
	}
	

	
}
