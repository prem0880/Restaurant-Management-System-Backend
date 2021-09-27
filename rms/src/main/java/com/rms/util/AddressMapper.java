package com.rms.util;

import com.rms.dto.AddressDto;
import com.rms.entity.Address;

public class AddressMapper {

	public static Address toEntity(AddressDto addressDto) {
		Address address = new Address();
		address.setAddressLine(addressDto.getAddressLine());
		address.setCity(addressDto.getCity());
		address.setCustomer(addressDto.getCustomer());
		address.setPincode(addressDto.getPincode());
		address.setState(addressDto.getState());
		return address;
	}
	
}
