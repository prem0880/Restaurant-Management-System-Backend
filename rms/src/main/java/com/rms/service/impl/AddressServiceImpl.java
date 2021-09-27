package com.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.AddressDao;
import com.rms.dto.AddressDto;
import com.rms.entity.Address;
import com.rms.service.AddressService;
import com.rms.util.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public String addAddress(AddressDto addressDto) {
		Address address = AddressMapper.toEntity(addressDto);
		return addressDao.addAddress(address);
	}

	@Override
	public List<Address> getAddressByPhoneNumber(Long phoneNumber) {
		return addressDao.getAddressByPhoneNumber(phoneNumber);
	}

}
