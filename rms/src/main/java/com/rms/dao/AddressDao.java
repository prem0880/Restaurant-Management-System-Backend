package com.rms.dao;

import java.util.List;

import com.rms.entity.Address;

public interface AddressDao {
	/**
	 * @param  address Entity object as input
	 * @return   boolean value
	 */
	boolean addAddress(Address address);

	/**
	 * 
	 * @param  phoneNumber as input
	 * @return   List of address Entity objects for given phoneNumber
	 */
	List<Address> getAddressByPhoneNumber(Long phoneNumber);

	/**
	 * 
	 * @param  customerId as input
	 * @return  address Entity object
	 */
	Address getAddressByCustomerId(Long customerId);

	/**
	 * 
	 * @param  address id as input
	 * @return  address Entity object
	 */
	Address getAddressById(Long id);
	
	/**
	 * 
	 * @param address id as input
	 * @param  address Entity object as input 
	 * @return boolean value
	 */
	boolean updateAddress(Long id,Address address);
}
