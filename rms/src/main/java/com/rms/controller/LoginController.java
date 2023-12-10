package com.rms.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.LoginDto;
import com.rms.response.HttpResponseStatus;
import com.rms.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);

	/**
	 *@param This method takes login DTO object as input
     *@return This method returns success message if login is created successfully
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> saveLogin(@Valid @RequestBody LoginDto loginDto) {
		logger.info("Entering saveLogin method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), loginService.saveLogin(loginDto)),  HttpStatus.OK);
		
	}
	
	/**
	*@param This method takes login DTO object as input
	*@return This method returns role of logged in user along with success message 
	*/
	@PostMapping("/credential")
	public ResponseEntity<HttpResponseStatus> checkCredential(@Valid @RequestBody LoginDto loginDto) {
		logger.info("Entering checkCredential method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMER_LOGIN_SUCCESS,loginService.checkCredential(loginDto)),  HttpStatus.OK);
		
	}
	
	/**
	*@param This method takes login DTO object as input
	*@return This method returns success message if login is updated successfully
	*/
	@PutMapping("/{password}")
	public ResponseEntity<HttpResponseStatus> updatePassword(@Valid @RequestBody LoginDto loginDto,@PathVariable String password) { 
		logger.info("Entering updatePassword method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),loginService.updatePassword(loginDto,password)),  HttpStatus.OK);
		
	}
	
	/**
	 *@param This method takes customer/admin id as input  
     *@return This method returns role of user along with success message if fetched successfully
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getRoleById(@PathVariable Long id) {
		logger.info("Entering getRoleById method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), loginService.getRoleById(id)),  HttpStatus.OK);
		
	}
	
	
	
}
