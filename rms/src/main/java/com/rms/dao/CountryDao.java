package com.rms.dao;

import java.util.List;
import com.rms.entity.Country;

public interface CountryDao {
	public String addCountry(Country country);
	public List<Country> getAllCountry();
	public String deleteCountry(Long id);
	public String updateCountry(Long id, Country country);
	public Country getCountryById(Long id);
}
