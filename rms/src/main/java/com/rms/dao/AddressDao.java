package com.rms.dao;

import java.util.List;

import com.rms.entity.Address;

public interface AddressDao {
	boolean addAddress(Address address);

	List<Address> getAddressByPhoneNumber(Long phoneNumber);

	Address getAddressByCustomerId(Long customerId);

	Address getAddressById(Long id);
	
	boolean updateAddress(Long id,Address address);
}
