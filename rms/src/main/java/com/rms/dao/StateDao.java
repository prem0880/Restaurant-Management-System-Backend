package com.rms.dao;

import java.util.List;

import com.rms.entity.State;

public interface StateDao {
	
	/**
	 * 
	 * @param state Entity object as input
	 * @return boolean value
	 */
	boolean addState(State state);

	/**
	 * 
	 * @param country id as input
	 * @return  List of State Entity objects
	 */
	List<State> getStatesByCountry(Long countryId);

}
