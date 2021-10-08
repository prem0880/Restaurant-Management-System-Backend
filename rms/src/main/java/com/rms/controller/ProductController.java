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
import com.rms.dto.ProductDto;
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	/**
	 *@param This method takes no input
	 *@return This method returns List of product DTO objects along with success message as HttpResponseStatus 
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllProduct() {
		logger.debug("Entering getAllProduct method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.PRODUCT_FETCH_SUCCESS, productService.getAllProduct()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes product id as input
	 *@return This method returns product object for given id currently in the database with the corresponding id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getProductById(@PathVariable Long id) {
		logger.debug("Entering getProductById method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.PRODUCT_FETCH_SUCCESS, productService.getProductById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes product category id and type as input
	 *@return This method returns product object for given id's currently in the database with the corresponding id
	 */
	@GetMapping("/category/{id}/type/{type}")
	public ResponseEntity<HttpResponseStatus> getProductByTypeAndCategory(@PathVariable Long id, @PathVariable String type) {
		logger.debug("Entering getProductByTypeAndCategory method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.PRODUCT_FETCH_SUCCESS,
					productService.getProductByTypeAndCategory(id, type)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes product DTO object as input
	 *@return This method returns success message if product is created successfully
     */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addProduct(@Valid @RequestBody ProductDto productDto) {
		logger.debug("Entering addProduct method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), productService.addProduct(productDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes product object and id as input 
	 *@return This method returns message if product is updated successfully with id
     */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateProduct(@PathVariable("id") Long id,
			@Valid @RequestBody ProductDto productDto) {
		logger.debug("Entering updateProduct method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), productService.updateProduct(id, productDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *@param This method takes product id as input 
	 *@return This method returns success message if product is deleted successfully with id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteProduct(@PathVariable("id") Long id) {
		logger.debug("Entering deleteProduct method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), productService.deleteProduct(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 *@param This method takes product meal id as input
	 *@return This method returns product object for given id currently in the database with the corresponding id
	 */
	@GetMapping("/meal/{id}")
	public ResponseEntity<HttpResponseStatus> getProductByMeal(@PathVariable("id") Long id) {
		logger.debug("Entering getProductByMeal method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.PRODUCT_FETCH_SUCCESS,productService.getProductByMeal(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}


}
