package com.rms.util;

import com.rms.dto.AddressDto;
import com.rms.entity.Address;

public class AddressUtil {
	
	
	private AddressUtil() {
		
	}

	public static Address toEntity(AddressDto addressDto) {
		Address address = new Address();
		address.setAddressLine(addressDto.getAddressLine());
		address.setCity(addressDto.getCity());
		address.setCustomer(addressDto.getCustomer());
		address.setPincode(addressDto.getPincode());
		address.setState(addressDto.getState());
		return address;
	}

	public static AddressDto toDto(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.setId(address.getId());
		addressDto.setAddressLine(address.getAddressLine());
		addressDto.setCity(address.getCity());
		addressDto.setCustomer(address.getCustomer());
		addressDto.setPincode(address.getPincode());
		addressDto.setState(address.getState());
		addressDto.setCreatedOn(address.getCreatedOn());
		addressDto.setUpdatedOn(address.getUpdatedOn());
		return addressDto;
	}

}
