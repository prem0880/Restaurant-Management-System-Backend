package com.rms.dao;


import com.rms.entity.Login;

public interface LoginDao {
	
	/**
	 * 
	 * @param login Entity object as input
	 * @return  boolean value
	 */
	boolean saveLogin(Login login);
	
	
	/**
	 * 
	 * @param email string as input
	 * @param password as input
	 * @return boolean value
	 */
	boolean updateLogin(String email, String password);
	
	/**
	 * 
	 * @param customer email as input
	 * @return  Login Entity object
	 */
	Login getByEmail(String email);
	
	/**
	 * 
	 * @param customer email as input
	 * @return  Login Entity object
	 */
	Login getLoginByMail(String email);
	
	/**
	 * 
	 * @param login id as input
	 * @return  role of logged in user
	 */
	String getRoleById(Long id);
	

	
}
