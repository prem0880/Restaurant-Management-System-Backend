package com.rms.dao;

import java.util.List;

import com.rms.entity.Customer;

public interface CustomerDao {
	Long addCustomer(Customer customer);

	List<Customer> getAllCustomer();

	Customer getCustomerById(Long id);

	boolean updateCustomer(Long id, Customer customer);

	Customer getCustomerByEmail(Customer customer);
	
	Long getCustomerByMail(String email);
}
