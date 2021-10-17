package com.rms.service;

import com.rms.dto.LoginDto;

public interface LoginService {
	
	/**
	 * 
	 * @param login DTO object as input
	 * @return  success string message
	 */
	String saveLogin(LoginDto loginDto);
	
	/**
	 * 
	 * @param login DTO object as input
	 * @param password as input
	 * @return	success string message
	 */
	String updatePassword(LoginDto loginDto, String password);
	
	/**
	 * 
	 * @param customer email as input
	 * @return  Login DTO object
	 */
	LoginDto getByEmail(String email);
	
	/**
	 * 
	 * @param login DTO object as input
	 * @return  success string message
	 */
	String checkCredential(LoginDto loginDto);
	
	/**
	 * 
	 * @param login id as input
	 * @return  role of logged in user
	 */
	String getRoleById(Long id);
}
