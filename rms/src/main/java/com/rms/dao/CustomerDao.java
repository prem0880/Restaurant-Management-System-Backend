package com.rms.dao;

import java.util.List;

import com.rms.entity.Customer;

public interface CustomerDao {
	 boolean addCustomer(Customer customer);
	 List<Customer> getAllCustomer();
	 Customer getCustomerById(Long id);
	 boolean updateCustomer(Long id, Customer customer);
}
