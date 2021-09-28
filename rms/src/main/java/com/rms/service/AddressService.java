package com.rms.service;

import java.util.List;
import com.rms.dto.AddressDto;

public interface AddressService {
	String addAddress(AddressDto addressDto);
	List<AddressDto> getAddressByPhoneNumber(Long phoneNumber);
}
