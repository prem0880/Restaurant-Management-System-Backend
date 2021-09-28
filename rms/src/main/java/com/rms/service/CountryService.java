package com.rms.service;

import java.util.List;

import com.rms.dto.CountryDto;

public interface CountryService {
	String addCountry(CountryDto countryDto);

	List<CountryDto> getAllCountry();
	
	String deleteCountry(Long id);
	
	String updateCountry(Long id, CountryDto countryDto);
	
	CountryDto getCountryById(Long id);
}
