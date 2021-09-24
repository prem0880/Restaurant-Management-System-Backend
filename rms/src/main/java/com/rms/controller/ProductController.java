package com.rms.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.rms.dto.ProductDto;
import com.rms.entity.Product;
import com.rms.exception.IdNotFoundException;
import com.rms.service.ProductService;



@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProduct(){
		return new ResponseEntity<>(productService.getAllProduct(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		return new ResponseEntity<>(productService.getProductById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/product")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDto productDto){
		
		return new ResponseEntity<>(productService.addProduct(productDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable("id") Long id,@Valid @RequestBody ProductDto productDto){
		
		return new ResponseEntity<>(productService.updateProduct(id, productDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{pid}/category/{cid}/meal/{mid}")
	public ResponseEntity<String> deleteProduct(@PathVariable("pid") Long pid,@Valid @PathVariable("cid") Long cid,@PathVariable("mid") Long mid){
		
		return new ResponseEntity<>(productService.deleteProduct(pid,cid,mid),new HttpHeaders(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	

	
}
