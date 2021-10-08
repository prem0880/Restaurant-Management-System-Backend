package com.rms.dao;

import java.util.List;
import com.rms.entity.Country;

public interface CountryDao {
	
	/**
	 * 
	 * @param country Entity object as input
	 * @return  boolean value
	 */
	boolean addCountry(Country country);

	/**
	 * 
	 * @return  List of Country Entity objects
	 */
	List<Country> getAllCountry();

	/**
	 * 
	 * @param country id as input
	 * @return  success string message
	 */
	String deleteCountry(Long id);

	/**
	 * 
	 * @param country id as input
	 * @param country Entity object
	 * @return	boolean value
	 */
	boolean updateCountry(Long id, Country country);

	/**
	 * 
	 * @param country id as input
	 * @return  country Entity object
	 */
	Country getCountryById(Long id);
}
