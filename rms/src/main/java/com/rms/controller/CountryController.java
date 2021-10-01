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

import com.rms.dto.CountryDto;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.CountryService;

@RestController
@RequestMapping("/country")
@CrossOrigin("http://localhost:4200")
public class CountryController {

	@Autowired
	private CountryService countryService;

	static final String DATA_SUCCESS = "Country Data Retrieval is Success!";

	@GetMapping("/getAll")
	public ResponseEntity<HttpResponse> getAllCountry() {
		try {
			return new ResponseEntity<>(
					new HttpResponse(HttpStatus.OK.value(), DATA_SUCCESS, countryService.getAllCountry()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addCountry(@RequestBody CountryDto countryDto) {
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), countryService.addCountry(countryDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<HttpResponse> getCountryById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
					new HttpResponse(HttpStatus.OK.value(), DATA_SUCCESS, countryService.getCountryById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<HttpResponse> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
		try {
			return new ResponseEntity<>(
					new HttpResponse(HttpStatus.OK.value(), countryService.updateCountry(id, countryDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpResponse> deleteCountry(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), countryService.deleteCountry(id)),
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
