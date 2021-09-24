package com.rms.util;

import com.rms.dto.CustomerDto;
import com.rms.entity.Customer;

public class CustomerMapper {

	public static Customer toEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setPassword(String.valueOf(customerDto.getPhoneNumber()));
		return customer;
	}
	
}
