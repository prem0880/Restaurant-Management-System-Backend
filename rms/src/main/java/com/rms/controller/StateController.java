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
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.StateService;

@RestController
@RequestMapping("/state")
@CrossOrigin("*")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	private static final Logger logger = LogManager.getLogger(StateController.class);

	@GetMapping("/{countryId}")
	public ResponseEntity<HttpResponseStatus> getStatesByCountry(@PathVariable("countryId") Long countryId) {
		logger.info("Entering getStatesByCountry method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.STATE_FETCH_SUCCESS,
					stateService.getStatesByCountry(countryId)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("")
	public ResponseEntity<HttpResponseStatus> addState(@RequestBody StateDto stateDto) {
		logger.info("Entering addState method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), stateService.addState(stateDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
