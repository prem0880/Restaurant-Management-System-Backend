package com.rms.service;

import java.util.List;

import com.rms.dto.StateDto;

public interface StateService {
	
	/**
	 * 
	 * @param state DTO object as input
	 * @return  success string message
	 */
	String addState(StateDto stateDto);

	
	/**
	 * 
	 * @param country id as input
	 * @return  List of State DTO objects
	 */
	List<StateDto> getStatesByCountry(Long id);

}
