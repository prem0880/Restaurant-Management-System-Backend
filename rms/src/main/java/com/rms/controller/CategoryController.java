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

import com.rms.dto.CategoryDto;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	static final String DATA_SUCCESS = "Category Data Retrieval is Success!";

	@GetMapping("/getAll")
	public ResponseEntity<HttpResponse> getAllCategory() {
		try {
			return new ResponseEntity<>(
					new HttpResponse(HttpStatus.OK.value(), DATA_SUCCESS, categoryService.getAllCategory()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<HttpResponse> getCategoryById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
					new HttpResponse(HttpStatus.OK.value(), DATA_SUCCESS, categoryService.getCategoryById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addCategory(@RequestBody CategoryDto categoryDto) {
		try {
			String message = categoryService.addCategory(categoryDto);
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), message), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<HttpResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
		try {
			String message = categoryService.updateCategory(id, categoryDto);
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), message), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpResponse> deleteCategory(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), categoryService.deleteCategory(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponse> businessException(BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<HttpResponse> dataBaseException(DataBaseException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
