package com.rms.util;

import com.rms.dto.StateDto;
import com.rms.entity.State;

public class StateMapper {

	public static State toEntity(StateDto stateDto) {
		State state = new State();
		state.setName(stateDto.getName());
		state.setCountry(stateDto.getCountry());
		return state;
	}
	
	
}
