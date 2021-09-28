package com.rms.dao;

import java.util.List;
import com.rms.entity.Country;

public interface CountryDao {
	 boolean addCountry(Country country);
	 List<Country> getAllCountry();
	 String deleteCountry(Long id);
	 boolean updateCountry(Long id, Country country);
	 Country getCountryById(Long id);
}
