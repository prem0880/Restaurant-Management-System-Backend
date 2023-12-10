package com.rms.util;

import com.rms.dto.CountryDto;
import com.rms.entity.Country;

public class CountryUtil {
	
	private CountryUtil() {
		
	}

	public static Country toEntity(CountryDto countryDto) {
		Country country = new Country();
		country.setName(countryDto.getName());
		return country;
	}

	public static CountryDto toDto(Country country) {
		CountryDto countryDto = new CountryDto();
		countryDto.setId(country.getId());
		countryDto.setName(country.getName());
		countryDto.setCreatedOn(country.getCreatedOn());
		countryDto.setUpdatedOn(country.getUpdatedOn());
		return countryDto;
	}
}
