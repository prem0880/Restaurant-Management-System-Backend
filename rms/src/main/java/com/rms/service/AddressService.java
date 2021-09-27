package com.rms.service;

import java.util.List;
import com.rms.dto.AddressDto;
import com.rms.entity.Address;

public interface AddressService {
	public String addAddress(AddressDto addressDto);
	public List<Address> getAddressByPhoneNumber(Long phoneNumber);
}
