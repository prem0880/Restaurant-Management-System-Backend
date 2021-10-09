package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.AddressDao;
import com.rms.dao.CustomerDao;
import com.rms.dto.AddressDto;
import com.rms.entity.Address;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.IdNotFoundException;
import com.rms.exception.NoRecordFoundException;
import com.rms.service.AddressService;
import com.rms.util.AddressUtil;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Autowired 
	private CustomerDao customerDao;
	
	private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

	@Override
	public String addAddress(AddressDto addressDto) {
		logger.info("Entering addAddress method");
		String result = null;
		try {
			Address address = AddressUtil.toEntity(addressDto);
			boolean stat = addressDao.addAddress(address);
			if (stat) {
				result = ApplicationConstants.ADDRESS_SAVE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<AddressDto> getAddressByPhoneNumber(Long phoneNumber) {
		logger.info("Entering getAddressByPhoneNumber method");
		try {
			List<Address> addressEntity = addressDao.getAddressByPhoneNumber(phoneNumber);
			
			if (CollectionUtils.isEmpty(addressEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.ADDRESS_NOT_FOUND);
			} else {
				List<AddressDto> addressDto = new ArrayList<>();
				addressEntity.stream().forEach(entity -> addressDto.add(AddressUtil.toDto(entity)));
				return addressDto;
			}
			
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Long getAddressByCustomerId(Long customerId) {
		logger.info("Entering getAddressByCustomerId method");

		try {
			
			if (customerDao.getCustomerById(customerId) != null) {
				Long result = null;
				Address addressEntity = addressDao.getAddressByCustomerId(customerId);
				result = addressEntity.getId();
				return result;
				
			} else {
				throw new IdNotFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
			
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public AddressDto getAddressById(Long id) {
		logger.info("Entering getAddressById method");
		try {
			
			Address address = addressDao.getAddressById(id);
			if (address != null) {
				return AddressUtil.toDto(address);
			} else {
				throw new NoRecordFoundException(ApplicationConstants.ADDRESS_NOT_FOUND);
			}
			
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public String updateAddress(Long id, AddressDto addressDto) {
		logger.info("Entering updateAddress method");
		try {
			if(addressDao.getAddressById(id)!=null) {
				String result = null;
				Address address=AddressUtil.toEntity(addressDto);
				boolean flag = addressDao.updateAddress(id, address);
				if (flag) {
					result = ApplicationConstants.CATEGORY_UPDATE_SUCCESS;
				}
				return result;
			}else {
				throw new IdNotFoundException(ApplicationConstants.ADDRESS_NOT_FOUND);
			}
			
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	

}
