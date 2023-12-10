package com.rms.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.CategoryDao;
import com.rms.dao.MealDao;
import com.rms.dao.ProductDao;
import com.rms.dto.ProductDto;
import com.rms.entity.Category;
import com.rms.entity.Meal;
import com.rms.entity.Product;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.IdNotFoundException;
import com.rms.exception.NoRecordFoundException;
import com.rms.service.ProductService;
import com.rms.util.ProductUtil;
import com.rms.util.TimeConverterUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private MealDao mealDao;
	
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Override
	public String deleteProduct(Long id) {
		logger.info("Entering deleteProduct method");
		try {
			if(productDao.getProductById(id)!=null) {
			Product product = productDao.getProductById(id);
			return productDao.deleteProduct(product);
			}
			else {
				throw new IdNotFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateProduct(Long id, ProductDto productDto) {
		logger.info("Entering updateProduct method");
		try {
			String result = null;
			Product product = ProductUtil.toEntity(productDto);
			Category category = categoryDao.getCategoryById(productDto.getCategory().getId());
			if (category != null) {
				Meal meal = mealDao.getMealById(productDto.getMeal().getId());
				if (meal != null) {
					boolean flag = productDao.updateProduct(id, product);
					if (flag) {
						result = ApplicationConstants.PRODUCT_UPDATE_SUCCESS;
					}
					return result;
				} else {
					throw new NoRecordFoundException(ApplicationConstants.MEAL_NOT_FOUND);
				}
			} else {
				throw new NoRecordFoundException(ApplicationConstants.CATEGORY_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addProduct(ProductDto productDto) {
		logger.info("Entering addProduct method");
		try {
			String result = null;
			Product product = ProductUtil.toEntity(productDto);
			Category category = categoryDao.getCategoryById(productDto.getCategory().getId());
			if (category != null) {
				Meal meal = mealDao.getMealById(productDto.getMeal().getId());
				if (meal != null) {
					product.setCategory(category);
					product.setMeal(meal);
					boolean flag = productDao.addProduct(product);
					if (flag) {
						result = ApplicationConstants.PRODUCT_SAVE_SUCCESS;
					}
					return result;
				} else {
					throw new NoRecordFoundException(ApplicationConstants.MEAL_NOT_FOUND);
				}
			} else {
				throw new NoRecordFoundException(ApplicationConstants.CATEGORY_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public ProductDto getProductById(Long id) {
		logger.info("Entering getProductById method");
		try {
			Product product = productDao.getProductById(id);
			if (product != null) {
				return ProductUtil.toDto(product);
			} else {
				throw new IdNotFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<ProductDto> getAllProduct() {
		logger.info("Entering getAllProduct method");
		try {
			List<Product> productEntity = productDao.getAllProduct();
			if (CollectionUtils.isEmpty(productEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
			else {
				List<ProductDto> productDto = new ArrayList<>();
				productEntity.stream().forEach(entity -> productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<ProductDto> getProductByTypeAndCategory(Long categoryId, String type) {
		logger.info("Entering getProductByTypeAndCategory method");
		try {
			
			if(categoryDao.getCategoryById(categoryId)==null) {
				throw new IdNotFoundException(ApplicationConstants.CATEGORY_NOT_FOUND);
			}
			
			List<Product> productEntity = productDao.getProductByTypeAndCategory(categoryId, type);
			if (CollectionUtils.isEmpty(productEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
			else {
				List<ProductDto> productDto = new ArrayList<>();
				productEntity.stream().filter(p->p.getMeal().getName().equals(TimeConverterUtil.getTime(LocalTime.now()))).forEach(entity -> productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<ProductDto> getProductByMeal(Long mealId) {
		logger.info("Entering getProductByTypeAndCategory method");
		try {
			
			if(mealDao.getMealById(mealId)==null) {
				throw new IdNotFoundException(ApplicationConstants.MEAL_NOT_FOUND);
			}
			
			List<Product> productEntity = productDao.getProductByMeal(mealId);
			if (CollectionUtils.isEmpty(productEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
			else {
				List<ProductDto> productDto = new ArrayList<>();
				productEntity.stream().forEach(entity -> productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<ProductDto> getAllProductByMeal() {
		logger.info("Entering getAllProductByMeal method");
		try {
			List<Product> productEntity = productDao.getAllProduct();
			if (CollectionUtils.isEmpty(productEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
			else {
				List<ProductDto> productDto = new ArrayList<>();
				productEntity.stream().filter(p->p.getMeal().getName().equals(TimeConverterUtil.getTime(LocalTime.now()))).forEach(entity -> productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			} 
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
