package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.CustomerDao;
import com.rms.dto.CustomerDto;
import com.rms.entity.Customer;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.CustomerService;
import com.rms.util.CustomerUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public String addCustomer(CustomerDto customerDto) {
		try{
			String result=null;
			Customer customer = CustomerUtil.toEntity(customerDto);
			boolean flag=customerDao.addCustomer(customer);
			if(flag) {
				result="Customer Creation is Successful";
			}
			return result;
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<CustomerDto> getAllCustomer() {
		try {
			List<Customer> customerEntity=customerDao.getAllCustomer();	
			if(customerEntity!=null) {
				List<CustomerDto> customerDto=new ArrayList<>();
				customerEntity.stream().forEach(entity->customerDto.add(CustomerUtil.toDto(entity)));
				return customerDto;
			}
			else {
				throw new BusinessLogicException("No records Found for Customer");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public CustomerDto getCustomerById(Long id) {
		try{
			Customer customer=customerDao.getCustomerById(id);
			if(customer!=null) {
				return CustomerUtil.toDto(customer);
			}else {
				throw new BusinessLogicException("No records Found for Customer");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCustomer(Long id, CustomerDto customerDto) {
		try{
			String result=null;
			Customer customer = CustomerUtil.toEntity(customerDto);
			boolean flag=customerDao.updateCustomer(id,customer);
			if(flag) {
				result="Customer Updation is Successful";
			}
			return result;
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

}
