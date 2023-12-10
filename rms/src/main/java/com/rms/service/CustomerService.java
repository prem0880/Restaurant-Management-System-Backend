package com.rms.service;

import java.util.List;

import com.rms.dto.CustomerDto;

public interface CustomerService {
	
	/**
	 * 
	 * @param customer DTO object as input
	 * @return  customer DTO object
	 */
	CustomerDto addCustomer(CustomerDto customerDto);

	/**
	 * 
	 * @return  List of Customer DTO objects
	 */
	List<CustomerDto> getAllCustomer();

	/**
	 * 
	 * @param customer id as input
	 * @return  customer DTO object
	 */
	CustomerDto getCustomerById(Long id);

	/**
	 * 
	 * @param customer id as input
	 * @param customer DTO object
	 * @return	 success string message
	 */
	String updateCustomer(Long id, CustomerDto customerDto);	
	
	/**
	 * 
	 * @param customer email as input
	 * @return  customer id
	 */
	Long getCustomerByMail(String email);
	
	
	/**
	 * 
	 * @param customer phone Number as input
	 * @return  customer id
	 */
	Long getCustomerByPhone(Long phoneNumber);
	
}
