package com.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.StateDao;
import com.rms.dto.StateDto;
import com.rms.entity.State;
import com.rms.service.StateService;
import com.rms.util.StateMapper;

@Service
public class StateServiceImpl implements StateService{

	
	@Autowired
	private StateDao stateDao;
	
	@Override
	public String addState(StateDto stateDto) {
		State state = StateMapper.toEntity(stateDto);
		return stateDao.addState(state);
	}

	@Override
	public List<State> getStatesByCountry(Long id) {
		return stateDao.getStatesByCountry(id);
	}

}
