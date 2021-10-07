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
import com.rms.dto.MealDto;
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.MealService;

@RestController
@RequestMapping("/meal")
@CrossOrigin("*")
public class MealController {

	@Autowired
	private MealService mealService;
	
	private static final Logger logger = LogManager.getLogger(MealController.class);

	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllMeal() {
		logger.info("Entering getAllMeal method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.MEAL_FETCH_SUCCESS, mealService.getAllMeal()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getMealById(@PathVariable Long id) {
		logger.info("Entering getMealById method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.MEAL_FETCH_SUCCESS, mealService.getMealById(id)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addMeal(@RequestBody MealDto mealDto) {
		logger.info("Entering addMeal method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), mealService.addMeal(mealDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateMeal(@PathVariable Long id, @RequestBody MealDto mealDto) {
		logger.info("Entering updateMeal method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), mealService.updateMeal(id, mealDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteMeal(@PathVariable Long id) {
		logger.info("Entering deleteMeal method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), mealService.deleteMeal(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/name/{meal}")
	public ResponseEntity<HttpResponseStatus> getMealByName(@PathVariable String meal) {
		logger.info("Entering getMealById method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.MEAL_FETCH_SUCCESS, mealService.getMealByName(meal)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
