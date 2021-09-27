package com.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.rms.dto.StateDto;
import com.rms.entity.State;
import com.rms.exception.IdNotFoundException;
import com.rms.service.StateService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping("/getState/{countryId}")
	public ResponseEntity<List<State>> getStatesByCountry(@PathVariable("countryId") Long countryId) {
		return new ResponseEntity<>(stateService.getStatesByCountry(countryId),new HttpHeaders(),HttpStatus.OK);
	}

	@PostMapping("/addState")
	public ResponseEntity<String> addState(@RequestBody StateDto stateDto) {
		return new ResponseEntity<>(stateService.addState(stateDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
