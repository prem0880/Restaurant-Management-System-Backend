package com.rms.service;

import com.rms.dto.AddressDto;

public interface AddressService {
	
	/**
	 * @param  addressDto object as input
	 * @return   success string message
	 */
	String addAddress(AddressDto addressDto);
	
	
	/**
	 * 
	 * @param  phoneNumber as input
	 * @return address DTO object for given phoneNumber
	 */
	AddressDto getAddressByPhoneNumber(Long phoneNumber);

	/**
	 * 
	 * @param  customerId as input
	 * @return  address id
	 */
	Long getAddressByCustomerId(Long customerId);

	/**
	 * 
	 * @param  address id as input
	 * @return  address DTO object
	 */
	AddressDto getAddressById(Long id);
	
	/**
	 * 
	 * @param address id as input
	 * @param  address DTO object as input 
	 * @return success message
	 */
	String updateAddress(Long id,AddressDto addressDto);
}
