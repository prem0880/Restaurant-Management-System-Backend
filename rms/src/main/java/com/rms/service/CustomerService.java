package com.rms.service;

import java.util.List;

import com.rms.dto.CustomerDto;

public interface CustomerService {
	Long addCustomer(CustomerDto customerDto);
	List<CustomerDto> getAllCustomer();
	CustomerDto getCustomerById(Long id);
	String updateCustomer(Long id, CustomerDto customerDto);
	Long customerLogin(CustomerDto customerDto);
}
