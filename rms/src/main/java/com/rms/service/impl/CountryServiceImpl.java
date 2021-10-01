package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.CountryDao;
import com.rms.dto.CountryDto;
import com.rms.entity.Country;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.CountryService;
import com.rms.util.CountryUtil;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	public String addCountry(CountryDto countryDto) {
		try {
			String result = null;
			Country country = CountryUtil.toEntity(countryDto);
			boolean flag = countryDao.addCountry(country);
			if (flag) {
				result = "Country Creation is Successful";
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<CountryDto> getAllCountry() {
		try {
			List<Country> countryEntity = countryDao.getAllCountry();
			if (countryEntity != null) {
				List<CountryDto> countryDto = new ArrayList<>();
				countryEntity.stream().forEach(entity -> countryDto.add(CountryUtil.toDto(entity)));
				return countryDto;
			} else {
				throw new BusinessLogicException("No records Found for Country");
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteCountry(Long id) {
		try {
			return countryDao.deleteCountry(id);
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCountry(Long id, CountryDto countryDto) {
		try {
			String result = null;
			Country country = CountryUtil.toEntity(countryDto);
			boolean flag = countryDao.updateCountry(id, country);
			if (flag) {
				result = "Country Updation is Successful";
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public CountryDto getCountryById(Long id) {
		try {
			Country country = countryDao.getCountryById(id);
			if (country != null) {
				return CountryUtil.toDto(country);
			} else {
				throw new BusinessLogicException("No records Found for Country");
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
}
