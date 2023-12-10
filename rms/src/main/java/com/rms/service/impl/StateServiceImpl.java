package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.CountryDao;
import com.rms.dao.StateDao;
import com.rms.dto.StateDto;
import com.rms.entity.State;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.IdNotFoundException;
import com.rms.exception.NoRecordFoundException;
import com.rms.service.StateService;
import com.rms.util.StateUtil;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateDao stateDao;
	
	@Autowired
	private CountryDao countryDao;
	
	private static final Logger logger = LogManager.getLogger(StateServiceImpl.class);


	@Override
	public String addState(StateDto stateDto) {
		logger.info("Entering addState method");
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
		logger.info("Entering getStatesByCountry method");
		try {
			if(countryDao.getCountryById(id)==null) {
				throw new IdNotFoundException(ApplicationConstants.COUNTRY_NOT_FOUND);
			}
			List<State> stateEntity = stateDao.getStatesByCountry(id);
			if (CollectionUtils.isEmpty(stateEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.STATE_NOT_FOUND);
			}
			else {
				List<StateDto> stateDto = new ArrayList<>();
				stateEntity.stream().forEach(entity -> stateDto.add(StateUtil.toDto(entity)));
				return stateDto;
			} 
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}
}
