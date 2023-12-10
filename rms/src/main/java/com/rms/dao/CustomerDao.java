package com.rms.dao;

import java.util.List;

import com.rms.entity.Customer;

public interface CustomerDao {
	
	/**
	 * 
	 * @param customer Entity object as input
	 * @return  customer identifier
	 */
	Long addCustomer(Customer customer);

	/**
	 * 
	 * @return  List of Customer Entity objects
	 */
	List<Customer> getAllCustomer();

	/**
	 * 
	 * @param customer id as input
	 * @return  customer Entity object
	 */
	Customer getCustomerById(Long id);

	/**
	 * 
	 * @param customer id as input
	 * @param customer Entity object
	 * @return	boolean value
	 */
	boolean updateCustomer(Long id, Customer customer);

	/**
	 * 
	 * @param customer Entity Object as input
	 * @return  customer Entity object
	 */
	Customer getCustomerByEmail(Customer customer);
	
	/**
	 * 
	 * @param customer phone Number as input
	 * @return  customer Entity object
	 */
	Long getCustomerByPhone(Long phoneNumber);
	
	/**
	 * 
	 * @param customer email as input
	 * @return  customer id
	 */
	Long getCustomerByMail(String email);
}
