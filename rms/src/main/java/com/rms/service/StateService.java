package com.rms.service;

import java.util.List;

import com.rms.dto.StateDto;

public interface StateService {
	String addState(StateDto stateDto);
	List<StateDto> getStatesByCountry(Long id);

}
