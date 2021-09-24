package com.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.CustomerDao;
import com.rms.dto.CustomerDto;
import com.rms.entity.Customer;
import com.rms.service.CustomerService;
import com.rms.util.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public String addCustomer(CustomerDto customerDto) {
		Customer customer = CustomerMapper.toEntity(customerDto);
		return customerDao.addCustomer(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerDao.getAllCustomer();
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerDao.getCustomerById(id);
	}

	@Override
	public String updateCustomer(Long id, CustomerDto customerDto) {
		Customer customer = CustomerMapper.toEntity(customerDto);
		return customerDao.updateCustomer(id,customer);

	}

}
