package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.CategoryDao;
import com.rms.dto.CategoryDto;
import com.rms.entity.Category;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.CategoryService;
import com.rms.util.CategoryUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);


	@Override
	public String deleteCategory(Long id) {
		logger.debug("Entering deleteCategory method");
		try {
			return categoryDao.deleteCategory(id);
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCategory(Long id, CategoryDto categoryDto) {
		logger.debug("Entering updateCategory method");
		try {
			String result = null;
			Category category = CategoryUtil.toEntity(categoryDto);
			boolean flag = categoryDao.updateCategory(id, category);
			if (flag) {
				result = ApplicationConstants.CATEGORY_UPDATE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addCategory(CategoryDto categoryDto) {
		logger.debug("Entering addCategory method");
		try {
			String result = null;
			Category category = CategoryUtil.toEntity(categoryDto);
			boolean flag = categoryDao.addCategory(category);
			if (flag) {
				result = ApplicationConstants.CATEGORY_SAVE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public CategoryDto getCategoryById(Long id) {
		logger.debug("Entering getCategoryById method");
		try {
			Category category = categoryDao.getCategoryById(id);
			if (category != null) {
				return CategoryUtil.toDto(category);
			} else {
				throw new BusinessLogicException(ApplicationConstants.CATEGORY_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		logger.debug("Entering getAllCategory method");
		try {
			List<Category> categoryEntity = categoryDao.getAllCategory();
			if (categoryEntity != null) {
				List<CategoryDto> categoryDto = new ArrayList<>();
				categoryEntity.stream().forEach(entity -> categoryDto.add(CategoryUtil.toDto(entity)));
				return categoryDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.CATEGORY_NOT_FOUND);
			}

		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
