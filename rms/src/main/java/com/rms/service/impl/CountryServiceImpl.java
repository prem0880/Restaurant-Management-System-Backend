package com.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.CountryDao;
import com.rms.dto.CountryDto;
import com.rms.entity.Country;
import com.rms.service.CountryService;
import com.rms.util.CountryMapper;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired 
	private CountryDao countryDao;
	
	@Override
	public String addCountry(CountryDto countryDto) {
		Country country = CountryMapper.toEntity(countryDto);
		return countryDao.addCountry(country);
	}

	@Override
	public List<Country> getAllCountry() {
		return countryDao.getAllCountry();
	}

	@Override
	public String deleteCountry(Long id) {
		return countryDao.deleteCountry(id);
	}

	@Override
	public String updateCountry(Long id, CountryDto countryDto) {
		Country country  = CountryMapper.toEntity(countryDto);
		return countryDao.updateCountry(id, country);
	}

	@Override
	public Country getCountryById(Long id) {
		return countryDao.getCountryById(id);
	}
}
