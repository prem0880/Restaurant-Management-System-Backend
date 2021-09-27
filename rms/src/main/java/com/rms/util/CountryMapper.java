package com.rms.util;

import com.rms.dto.CountryDto;
import com.rms.entity.Country;

public class CountryMapper {

	public static Country toEntity(CountryDto countryDto) {
		Country country = new Country();
		country.setName(countryDto.getName());
		return country;
	}
}
