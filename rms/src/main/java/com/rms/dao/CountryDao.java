package com.rms.dao;

import java.util.List;

import com.rms.entity.Country;

public interface CountryDao {
	public String addCountry(Country country);
	public List<Country> getAllCountry();
}
