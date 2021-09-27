package com.rms.service;

import java.util.List;

import com.rms.dto.CountryDto;
import com.rms.entity.Country;

public interface CountryService {
	public String addCountry(CountryDto countryDto);

	public List<Country> getAllCountry();

}
