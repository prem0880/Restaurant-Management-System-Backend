package com.rms.service;

import java.util.List;

import com.rms.dto.StateDto;
import com.rms.entity.State;

public interface StateService {
	public String addState(StateDto stateDto);
	public List<State> getStatesByCountry(Long id);

}
