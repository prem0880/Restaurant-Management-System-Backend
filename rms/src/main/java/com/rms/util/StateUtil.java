package com.rms.util;

import com.rms.dto.StateDto;
import com.rms.entity.State;

public class StateUtil {
	
	private StateUtil() {
		
	}

	public static State toEntity(StateDto stateDto) {
		State state = new State();
		state.setName(stateDto.getName());
		state.setCountry(stateDto.getCountry());
		return state;
	}

	public static StateDto toDto(State state) {
		StateDto stateDto = new StateDto();
		stateDto.setId(state.getId());
		stateDto.setName(state.getName());
		stateDto.setCountry(state.getCountry());
		stateDto.setCreatedOn(state.getCreatedOn());
		stateDto.setUpdatedOn(state.getUpdatedOn());
		return stateDto;
	}

}
