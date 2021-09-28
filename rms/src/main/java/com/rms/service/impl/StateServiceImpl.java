package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.StateDao;
import com.rms.dto.StateDto;
import com.rms.entity.State;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.StateService;
import com.rms.util.StateUtil;

@Service
public class StateServiceImpl implements StateService{

	
	@Autowired
	private StateDao stateDao;
	
	@Override
	public String addState(StateDto stateDto) {
		try{
			String result=null;
			State state = StateUtil.toEntity(stateDto);
			boolean flag=stateDao.addState(state);
			if(flag) {
				result="State Creation is Successful";
			}
			return result;
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<StateDto> getStatesByCountry(Long id) {
		try {
			List<State> stateEntity=stateDao.getStatesByCountry(id);
			if(stateEntity!=null) {
				List<StateDto> stateDto=new ArrayList<>();
				stateEntity.stream().forEach(entity->stateDto.add(StateUtil.toDto(entity)));
				return stateDto;
			}
			else {
				throw new BusinessLogicException("No records Found for State");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
}
