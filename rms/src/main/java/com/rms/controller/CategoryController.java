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

import com.rms.dto.CategoryDto;
import com.rms.entity.Category;
import com.rms.exception.IdNotFoundException;
import com.rms.service.CategoryService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory(){
		return new ResponseEntity<>(categoryService.getAllCategory(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/getCategory/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
		return new ResponseEntity<>(categoryService.getCategoryById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(categoryService.addCategory(categoryDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(categoryService.updateCategory(id,categoryDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id){
		
		return new ResponseEntity<>(categoryService.deleteCategory(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	

}
