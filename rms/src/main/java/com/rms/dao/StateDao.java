package com.rms.dao;

import java.util.List;

import com.rms.entity.State;

public interface StateDao {
	public String addState(State state);
	public List<State> getStatesByCountry(Long countryId);

}
