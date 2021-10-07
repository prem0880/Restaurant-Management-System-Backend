package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.StateDao;
import com.rms.dto.StateDto;
import com.rms.entity.State;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.StateService;
import com.rms.util.StateUtil;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateDao stateDao;
	
	private static final Logger logger = LogManager.getLogger(StateServiceImpl.class);


	@Override
	public String addState(StateDto stateDto) {
		logger.debug("Entering addState method");
		try {
			String result = null;
			State state = StateUtil.toEntity(stateDto);
			boolean flag = stateDao.addState(state);
			if (flag) {
				result = ApplicationConstants.STATE_SAVE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<StateDto> getStatesByCountry(Long id) {
		logger.debug("Entering getStatesByCountry method");
		try {
			List<State> stateEntity = stateDao.getStatesByCountry(id);
			if (stateEntity != null) {
				List<StateDto> stateDto = new ArrayList<>();
				stateEntity.stream().forEach(entity -> stateDto.add(StateUtil.toDto(entity)));
				return stateDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.STATE_FETCH_SUCCESS);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}
}
