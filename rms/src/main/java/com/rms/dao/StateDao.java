package com.rms.dao;

import java.util.List;

import com.rms.entity.State;

public interface StateDao {
	boolean addState(State state);

	List<State> getStatesByCountry(Long countryId);

}
