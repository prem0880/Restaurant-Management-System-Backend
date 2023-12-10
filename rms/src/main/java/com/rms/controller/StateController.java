package com.rms.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.StateDto;
import com.rms.response.HttpResponseStatus;
import com.rms.service.StateService;

@RestController
@RequestMapping("/state")
@CrossOrigin("*")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	private static final Logger logger = LogManager.getLogger(StateController.class);

	/**
	 *@param This method takes country id as input
	 *@return This method returns List of State DTO objects for given country id currently in the database 
	 */
	@GetMapping("/{countryId}")
	public ResponseEntity<HttpResponseStatus> getStatesByCountry(@PathVariable("countryId") Long countryId) {
		logger.info("Entering getStatesByCountry method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.STATE_FETCH_SUCCESS,
					stateService.getStatesByCountry(countryId)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes state DTO object as input
	 *@return This method returns success message if state is created successfully
     */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addState(@RequestBody StateDto stateDto) {
		logger.info("Entering addState method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), stateService.addState(stateDto)),
					HttpStatus.OK);
		
	}
	


}
