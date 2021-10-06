package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.AddressDao;
import com.rms.dto.AddressDto;
import com.rms.entity.Address;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.AddressService;
import com.rms.util.AddressUtil;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

	@Override
	public String addAddress(AddressDto addressDto) {
		logger.debug("Entering addAddress method");
		String result = null;
		try {
			Address address = AddressUtil.toEntity(addressDto);
			boolean stat = addressDao.addAddress(address);
			if (stat) {
				result = ApplicationConstants.ADDRESS_SAVE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<AddressDto> getAddressByPhoneNumber(Long phoneNumber) {
		logger.debug("Entering getAddressByPhoneNumber method");
		try {
			List<Address> addressEntity = addressDao.getAddressByPhoneNumber(phoneNumber);
			if (addressEntity != null) {
				List<AddressDto> addressDto = new ArrayList<>();
				addressEntity.stream().forEach(entity -> addressDto.add(AddressUtil.toDto(entity)));
				return addressDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.ADDRESS_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Long getAddressByCustomerId(Long customerId) {
		logger.debug("Entering getAddressByCustomerId method");

		try {
			Long result = null;
			Address addressEntity = addressDao.getAddressByCustomerId(customerId);
			if (addressEntity != null) {
				result = addressEntity.getId();

				return result;
			} else {
				throw new BusinessLogicException(ApplicationConstants.ADDRESS_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public AddressDto getAddressById(Long id) {
		logger.debug("Entering getAddressById method");
		try {
			Address address = addressDao.getAddressById(id);
			if (address != null) {
				return AddressUtil.toDto(address);
			} else {
				throw new BusinessLogicException(ApplicationConstants.ADDRESS_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

}
