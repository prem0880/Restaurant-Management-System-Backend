package com.rms.dao;

import java.util.List;

import com.rms.entity.Customer;

public interface CustomerDao {
	public String addCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(Long id);
	public String updateCustomer(Long id, Customer customer);
}
