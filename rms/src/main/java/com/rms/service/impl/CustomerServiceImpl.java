package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.CustomerDao;
import com.rms.dto.CustomerDto;
import com.rms.entity.Customer;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.IdNotFoundException;
import com.rms.exception.NoRecordFoundException;
import com.rms.service.CustomerService;
import com.rms.util.CustomerUtil;
import com.rms.util.MailPasswordUtil;
import com.rms.util.RandomPassword;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);


	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {
		logger.info("Entering addCustomer method");
		try {
			Customer customer = CustomerUtil.toEntity(customerDto);
			customer.setPassword(RandomPassword.getRandomPassword());
			MailPasswordUtil.sendPassword(javaMailSender, customer);
			return CustomerUtil.toDto(customerDao.getCustomerById(customerDao.addCustomer(customer)));
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<CustomerDto> getAllCustomer() {
		logger.info("Entering getAllCustomer method");
		try {
			List<Customer> customerEntity = customerDao.getAllCustomer();
			if (CollectionUtils.isEmpty(customerEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
			else {
				List<CustomerDto> customerDto = new ArrayList<>();
				customerEntity.stream().forEach(entity -> customerDto.add(CustomerUtil.toDto(entity)));
				return customerDto;
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public CustomerDto getCustomerById(Long id) {
		logger.info("Entering getCustomerById method");
		try {
			Customer customer = customerDao.getCustomerById(id);
			if (customer != null) {
				return CustomerUtil.toDto(customer);
			}else {
				throw new NoRecordFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
			
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCustomer(Long id, CustomerDto customerDto) {
		logger.info("Entering updateCustomer method");
		try {
			if(customerDao.getCustomerById(id)!=null) {
			String result = null;
			Customer customer = CustomerUtil.toEntity(customerDto);
			boolean flag = customerDao.updateCustomer(id, customer);
			if (flag) {
				result = ApplicationConstants.CUSTOMER_UPDATE_SUCCESS;
			}
			return result;
			}
			else {
				throw new IdNotFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public Long getCustomerByMail(String email) {
		logger.info("Entering getCustomerByMail method");
		try {
			Long id=customerDao.getCustomerByMail(email);
			if (customerDao.getCustomerById(id)!=null) {
				return id;
			}else {
				throw new NoRecordFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}
	
	@Override
	public Long getCustomerByPhone(Long phoneNumber) {
		logger.info("Entering getCustomerByPhone method");
		try {
			Long id=customerDao.getCustomerByPhone(phoneNumber);
			if (customerDao.getCustomerById(id)!=null) {
				return id;
			}else {
				throw new NoRecordFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}



}
