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
import com.rms.dto.CategoryDto;
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	private static final Logger logger = LogManager.getLogger(CategoryController.class);

	
	/**
	 *@return returns list of category currently in the database if data exists,If no data present,it return empty list
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCategory() {
		logger.info("Entering getAllCategory method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CATEGORY_FETCH_SUCCESS, categoryService.getAllCategory()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 *@param This method takes id as input
	 *@return returns category object currently in the database with the corresponding id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCategoryById(@PathVariable Long id) {
		logger.info("Entering getCategoryById method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CATEGORY_FETCH_SUCCESS, categoryService.getCategoryById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes category object as input
	 *@return returns message if created successfully
     *@exception throws exception if error occurs
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCategory(@RequestBody CategoryDto categoryDto) {
		logger.info("Entering addCategory method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), categoryService.addCategory(categoryDto)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes category object and id as input 
	 *@return returns message if updated successfully with id
     *@exception throws exception if error occurs
	 */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
		logger.info("Entering updateCategory method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), categoryService.updateCategory(id, categoryDto)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes category id as input 
	 *@return returns message if deleted successfully with id
	 *@exception throws exception if error occurs
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteCategory(@PathVariable Long id) {
		logger.info("Entering deleteCategory method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), categoryService.deleteCategory(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}


}
