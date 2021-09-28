package com.rms.service.impl;

import java.util.ArrayList;
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
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.ProductService;
import com.rms.util.ProductUtil;

@Service
public class ProductServiceImpl implements ProductService{
	

	@Autowired
	private ProductDao productDao;
	
	@Autowired 
	private CategoryDao categoryDao;
	
	@Autowired
	private MealDao mealDao;
		
	@Override
	public String deleteProduct(Long id) {
		
		try{
			Product product = productDao.getProductById(id);
			return productDao.deleteProduct(product);
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateProduct(Long id, ProductDto productDto) {
		
		try{
			String result=null;
			Product product = ProductUtil.toEntity(productDto);
			Category category=categoryDao.getCategoryById(productDto.getCategory().getId());
			if(category!=null) {
				Meal meal = mealDao.getMealById(productDto.getMeal().getId());
				if(meal!=null) {
					boolean flag=productDao.updateProduct(id, product);
					if(flag) {
						result="Product Updation is Successful";
					}
					return result;
				}else {
					throw new BusinessLogicException("No records Found for Meal");
				}
			}
			else {
				throw new BusinessLogicException("No records Found for Category");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addProduct(ProductDto productDto) {
		
		try{
			String result=null;
			Product product = ProductUtil.toEntity(productDto);
			Category category=categoryDao.getCategoryById(productDto.getCategory().getId());
			if(category!=null) {
				Meal meal = mealDao.getMealById(productDto.getMeal().getId());
				if(meal!=null) {
					product.setCategory(category);
					product.setMeal(meal);
					boolean flag=productDao.addProduct(product);
					if(flag) {
						result="Product Creation is Successful";
					}
					return result;
				}else {
					throw new BusinessLogicException("No records Found for Meal");
				}
			}
			else {
				throw new BusinessLogicException("No records Found for Category");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	
	}

	@Override
	public ProductDto getProductById(Long id) {
		try {
			Product product=productDao.getProductById(id);
			if(product!=null) {
				return ProductUtil.toDto(product);
			}
			else {
				throw new BusinessLogicException("No records Found for Product");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<ProductDto> getAllProduct() {
		try {
			List<Product> productEntity= productDao.getAllProduct();
			if(productEntity!=null) {
				List<ProductDto> productDto=new ArrayList<>();
				productEntity.stream().forEach(entity->productDto.add(ProductUtil.toDto(entity)));
				return productDto;
			}
			else {
				throw new BusinessLogicException("No records Found for Product");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
	
	

}
