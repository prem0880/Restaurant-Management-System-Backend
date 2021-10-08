package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.rms.service.ProductService;
import com.rms.util.ProductUtil;

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
			Product product = productDao.getProductById(id);
			return productDao.deleteProduct(product);
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
					throw new BusinessLogicException(ApplicationConstants.MEAL_NOT_FOUND);
				}
			} else {
				throw new BusinessLogicException(ApplicationConstants.CATEGORY_NOT_FOUND);
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
					throw new BusinessLogicException(ApplicationConstants.MEAL_NOT_FOUND);
				}
			} else {
				throw new BusinessLogicException(ApplicationConstants.CATEGORY_NOT_FOUND);
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
				throw new BusinessLogicException(ApplicationConstants.PRODUCT_NOT_FOUND);
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
			if (productEntity != null) {
				List<ProductDto> productDto = new ArrayList<>();
				productEntity.stream().forEach(entity -> productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.PRODUCT_NOT_FOUND);
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
			List<Product> productEntity = productDao.getProductByTypeAndCategory(categoryId, type);
			if (productEntity != null) {
				List<ProductDto> productDto = new ArrayList<>();
				productEntity.stream().forEach(entity -> productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.PRODUCT_NOT_FOUND);
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
			List<Product> productEntity = productDao.getProductByMeal(mealId);
			if (productEntity != null) {
				List<ProductDto> productDto = new ArrayList<>();
				productEntity.stream().forEach(entity -> productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
