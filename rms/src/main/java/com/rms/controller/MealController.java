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
import com.rms.dto.MealDto;
import com.rms.response.HttpResponseStatus;
import com.rms.service.MealService;

@RestController
@RequestMapping("/meal")
@CrossOrigin("*")
public class MealController {

	@Autowired
	private MealService mealService;
	
	private static final Logger logger = LogManager.getLogger(MealController.class);

	/**
	 *@param This method takes no input
	 *@return This method returns List of meal objects along with success message as HttpResponseStatus 
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllMeal() {
		logger.info("Entering getAllMeal method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.MEAL_FETCH_SUCCESS, mealService.getAllMeal()),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes meal id as input
	 *@return This method returns meal object for given id currently in the database with the corresponding id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getMealById(@PathVariable Long id) {
		logger.info("Entering getMealById method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.MEAL_FETCH_SUCCESS, mealService.getMealById(id)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes meal DTO object as input
	 *@return This method returns success message if meal is created successfully
     */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addMeal(@Valid @RequestBody MealDto mealDto) {
		logger.info("Entering addMeal method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), mealService.addMeal(mealDto)),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes meal object and id as input 
	 *@return This method returns message if meal is updated successfully with id
     */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateMeal(@PathVariable Long id,@Valid @RequestBody MealDto mealDto) {
		logger.info("Entering updateMeal method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), mealService.updateMeal(id, mealDto)),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes meal id as input 
	 *@return This method returns success message if meal is deleted successfully with id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteMeal(@PathVariable Long id) {
		logger.info("Entering deleteMeal method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), mealService.deleteMeal(id)),
					HttpStatus.OK);
		
	}
	
	/**
	 *@param This method takes meal name as input 
	 *@return This method returns meal object along with success message 
	 */
	@GetMapping("/name/{meal}")
	public ResponseEntity<HttpResponseStatus> getMealByName(@PathVariable String meal) {
		logger.info("Entering getMealByName method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.MEAL_FETCH_SUCCESS, mealService.getMealByName(meal)), HttpStatus.OK);
		
	}
	

}
