package com.rms.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.CategoryDao;
import com.rms.dao.MealDao;
import com.rms.dao.ProductDao;
import com.rms.dto.ProductDto;
import com.rms.entity.Category;
import com.rms.entity.Meal;
import com.rms.entity.Product;
import com.rms.exception.IdNotFoundException;
import com.rms.service.ProductService;
import com.rms.util.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService{
	
	static final String ID_NOT_FOUND="Product not found with id ";
	static final String CATEGORY_NOT_FOUND="Category not found with id";
	static final String MEAL_NOT_FOUND="Meal not found with id";

	@Autowired
	private ProductDao productDao;
	
	@Autowired 
	private CategoryDao categoryDao;
	
	@Autowired
	private MealDao mealDao;
	


	
	@Override
	public String deleteProduct(Long id,Long categoryId,Long mealId) {
		
		Product product = productDao.getProductById(id);
		Category category=categoryDao.getCategoryById(categoryId);
		Meal meal = mealDao.getMealById(mealId);
		
		if(product == null) {
			throw new IdNotFoundException(ID_NOT_FOUND);
		}
		if(category == null){
			throw new IdNotFoundException(CATEGORY_NOT_FOUND);
		}
		if(meal == null){
			throw new IdNotFoundException(MEAL_NOT_FOUND);
		}
		return productDao.deleteProduct(product);
	}

	@Override
	public String updateProduct(Long id, ProductDto productDto) {
		
		Product product = ProductMapper.toEntity(productDto);	
		Category category=categoryDao.getCategoryById(productDto.getCategory().getId());
		Meal meal = mealDao.getMealById(productDto.getMeal().getId());
	
		if(category == null)
		{
			throw new IdNotFoundException(CATEGORY_NOT_FOUND);
		}
		if(meal == null)
		{
			throw new IdNotFoundException(MEAL_NOT_FOUND);
		}
		return productDao.updateProduct(id, product);
	}

	@Override
	public String addProduct(ProductDto productDto) {
		
		Product product = ProductMapper.toEntity(productDto);
		Category category=categoryDao.getCategoryById(productDto.getCategory().getId());
		Meal meal = mealDao.getMealById(productDto.getMeal().getId());
		
		if(category == null)
		{
			throw new IdNotFoundException(CATEGORY_NOT_FOUND);
		}
		if(meal == null)
		{
			throw new IdNotFoundException(MEAL_NOT_FOUND);
		}
		product.setCategory(category);
		product.setMeal(meal);
		return productDao.addProduct(product);
	
	}

	@Override
	public Product getProductById(Long id) {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getAllProduct() {
		return productDao.getAllProduct();
	}
	
	

}
