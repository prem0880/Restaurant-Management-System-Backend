package com.rms.util;

import com.rms.dto.CustomerDto;
import com.rms.entity.Customer;

public class CustomerUtil {

	
	private CustomerUtil() {
		
	}
	public static Customer toEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setPassword(customerDto.getPassword());
		return customer;
	}

	public static CustomerDto toDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		customerDto.setPassword(customer.getPassword());
		customerDto.setCreatedOn(customer.getCreatedOn());
		customerDto.setUpdatedOn(customer.getUpdatedOn());
		return customerDto;
	}

}
