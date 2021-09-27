package com.rms.dao;

import java.util.List;
import com.rms.entity.Address;

public interface AddressDao {
	public String addAddress(Address address);
	public List<Address> getAddressByPhoneNumber(Long phoneNumber);
}
