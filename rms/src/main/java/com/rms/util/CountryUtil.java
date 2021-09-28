package com.rms.util;

import com.rms.dto.CountryDto;
import com.rms.entity.Country;

public class CountryUtil {

	public static Country toEntity(CountryDto countryDto) {
		Country country = new Country();
		country.setName(countryDto.getName());
		return country;
	}
	
	public static CountryDto toDto(Country country) {
		CountryDto countryDto = new CountryDto();
		countryDto.setName(country.getName());
		return countryDto;
	}
}
