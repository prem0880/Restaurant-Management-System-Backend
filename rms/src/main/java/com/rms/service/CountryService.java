package com.rms.service;

import java.util.List;

import com.rms.dto.CountryDto;

public interface CountryService {
	
	/**
	 * 
	 * @param country DTO object as input
	 * @return  success string message
	 */
	String addCountry(CountryDto countryDto);

	/**
	 * 
	 * @return  List of Country DTO objects
	 */
	List<CountryDto> getAllCountry();

	/**
	 * 
	 * @param country id as input
	 * @return  success string message
	 */
	String deleteCountry(Long id);

	/**
	 * 
	 * @param country id as input
	 * @param country DTO object
	 * @return	 success string message
	 */
	String updateCountry(Long id, CountryDto countryDto);

	/**
	 * 
	 * @param country id as input
	 * @return  country DTO object
	 */
	CountryDto getCountryById(Long id);
}
