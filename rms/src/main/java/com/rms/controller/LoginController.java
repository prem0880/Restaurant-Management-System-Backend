package com.rms.controller;

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
import com.rms.exception.BusinessLogicException;
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
	public ResponseEntity<HttpResponseStatus> saveLogin(@RequestBody LoginDto loginDto) {
		logger.debug("Entering saveLogin method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), loginService.saveLogin(loginDto)),  HttpStatus.OK);
		} catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	*@param This method takes login DTO object as input
	*@return This method returns role of logged in user along with success message 
	*/
	@PostMapping("/credential")
	public ResponseEntity<HttpResponseStatus> checkCredential(@RequestBody LoginDto loginDto) {
		logger.debug("Entering checkCredential method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMER_LOGIN_SUCCESS,loginService.checkCredential(loginDto)),  HttpStatus.OK);
		} catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	*@param This method takes login DTO object as input
	*@return This method returns success message if login is updated successfully
	*/
	@PutMapping("/updatepassword")
	public ResponseEntity<HttpResponseStatus> updatePassword(@RequestBody LoginDto login) { 
		logger.debug("Entering updatedPassword method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),loginService.updateLogin(login.getEmail(), login.getPassword())),  HttpStatus.OK);
		} catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 *@param This method takes customer/admin id as input  
     *@return This method returns role of user along with success message if fetched successfully
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getRoleById(@PathVariable Long id) {
		logger.info("Entering save method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), loginService.getRoleById(id)),  HttpStatus.OK);
		} catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
}
