package com.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.rms.dto.MealDto;
import com.rms.entity.Meal;
import com.rms.exception.IdNotFoundException;
import com.rms.service.MealService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class MealController {

	@Autowired
	private MealService mealService;
	
	@GetMapping("/getAllMeal")
	public ResponseEntity<List<Meal>> getAllMeal(){
		return new ResponseEntity<>(mealService.getAllMeal(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/getMeal/{id}")
	public ResponseEntity<Meal> getMealById(@PathVariable Long id){
		return new ResponseEntity<>(mealService.getMealById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/addMeal")
	public ResponseEntity<String> addMeal(@RequestBody MealDto mealDto){
		return new ResponseEntity<>(mealService.addMeal(mealDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("/updateMeal/{id}")
	public ResponseEntity<String> updateMeal(@PathVariable Long id, @RequestBody MealDto mealDto){
		return new ResponseEntity<>(mealService.updateMeal(id,mealDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMeal/{id}")
	public ResponseEntity<String> deleteMeal(@PathVariable Long id){
		
		return new ResponseEntity<>(mealService.deleteMeal(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
