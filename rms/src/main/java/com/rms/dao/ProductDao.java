package com.rms.dao;

import java.util.List;


import com.rms.entity.Product;

public interface ProductDao {

	
	 String deleteProduct(Product product);

	 boolean updateProduct(Long id, Product product);

	 boolean addProduct(Product product);

	 Product getProductById(Long id);

	 List<Product> getAllProduct();
	
	
}
