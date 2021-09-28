package com.rms.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.MealService;

@RestController
@RequestMapping("/meal")
@CrossOrigin("http://localhost:4200")
public class MealController {

	@Autowired
	private MealService mealService;
	
	static final String DATA_SUCCESS="Meal Data Retrieval is Success!";

	@GetMapping("/getAll")
	public ResponseEntity<HttpResponse> getAllMeal(){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),DATA_SUCCESS,mealService.getAllMeal()),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<HttpResponse> getMealById(@PathVariable Long id){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),DATA_SUCCESS,mealService.getMealById(id)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addMeal(@RequestBody MealDto mealDto){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),mealService.addMeal(mealDto)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<HttpResponse> updateMeal(@PathVariable Long id, @RequestBody MealDto mealDto){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),mealService.updateMeal(id,mealDto)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpResponse> deleteMeal(@PathVariable Long id){
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),mealService.deleteMeal(id)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponse> businessException (BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
		

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<HttpResponse> dataBaseException (DataBaseException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
