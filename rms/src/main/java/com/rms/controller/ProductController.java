package com.rms.controller;



import javax.validation.Valid;

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

import com.rms.dto.ProductDto;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.ProductService;



@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	static final String DATA_SUCCESS="Category Data Retrieval is Success!";
	
	@GetMapping("/getAll")
	public ResponseEntity<HttpResponse> getAllProduct(){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),DATA_SUCCESS,productService.getAllProduct()),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<HttpResponse> getProductById(@PathVariable Long id){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),DATA_SUCCESS,productService.getProductById(id)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/category/{id}/type/{type}")
	public ResponseEntity<HttpResponse> getProductByTypeAndCategory(@PathVariable Long id,@PathVariable String type){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),DATA_SUCCESS,productService.getProductByTypeAndCategory(id, type)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addProduct(@Valid @RequestBody ProductDto productDto){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),productService.addProduct(productDto)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<HttpResponse> updateProduct(@PathVariable("id") Long id,@Valid @RequestBody ProductDto productDto){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),productService.updateProduct(id, productDto)),HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpResponse> deleteProduct(@PathVariable("id") Long id){
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),productService.deleteProduct(id)),HttpStatus.OK);
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
