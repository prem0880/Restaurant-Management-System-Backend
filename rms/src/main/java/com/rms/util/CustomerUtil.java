package com.rms.util;

import com.rms.dto.CustomerDto;
import com.rms.entity.Customer;

public class CustomerUtil {

	public static Customer toEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setPassword(String.valueOf(customerDto.getPhoneNumber()));
		return customer;
	}
	
	public static CustomerDto toDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		customerDto.setPassword(String.valueOf(customer.getPhoneNumber()));
		return customerDto;
	}
	
}
