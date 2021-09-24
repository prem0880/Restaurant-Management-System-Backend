package com.rms.service;

import java.util.List;

import com.rms.dto.CustomerDto;
import com.rms.entity.Customer;

public interface CustomerService {
	public String addCustomer(CustomerDto customerDto);
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(Long id);
	public String updateCustomer(Long id, CustomerDto customerDto);
}
