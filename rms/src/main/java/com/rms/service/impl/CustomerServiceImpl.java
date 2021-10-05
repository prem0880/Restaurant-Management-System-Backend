package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.constants.ApplicationConstants;
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
	
	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);


	@Override
	public Long addCustomer(CustomerDto customerDto) {
		logger.trace("Entering addCustomer method");
		try {
			Customer customer = CustomerUtil.toEntity(customerDto);
			customer.setPassword(String.valueOf(customer.getPhoneNumber()));
			return customerDao.addCustomer(customer);
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<CustomerDto> getAllCustomer() {
		logger.trace("Entering getAllCustomer method");
		try {
			List<Customer> customerEntity = customerDao.getAllCustomer();
			if (customerEntity != null) {
				List<CustomerDto> customerDto = new ArrayList<>();
				customerEntity.stream().forEach(entity -> customerDto.add(CustomerUtil.toDto(entity)));
				return customerDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public CustomerDto getCustomerById(Long id) {
		logger.trace("Entering getCustomerById method");
		try {
			Customer customer = customerDao.getCustomerById(id);
			if (customer != null) {
				return CustomerUtil.toDto(customer);
			} else {
				throw new BusinessLogicException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCustomer(Long id, CustomerDto customerDto) {
		logger.trace("Entering updateCustomer method");
		try {
			String result = null;
			Customer customer = CustomerUtil.toEntity(customerDto);
			boolean flag = customerDao.updateCustomer(id, customer);
			if (flag) {
				result = ApplicationConstants.CUSTOMER_UPDATE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public Long customerLogin(CustomerDto customerDto) {
		logger.trace("Entering customerLogin method");
		try {
			Long result = null;
			Customer customer = CustomerUtil.toEntity(customerDto);
			Customer customerEntity = customerDao.getCustomerByEmail(customer);
			if (customerEntity != null && customerEntity.getPassword().equals(customer.getPassword())) {
				result = customerEntity.getId();
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
