package com.rms.dao;

import java.util.List;


import com.rms.entity.Product;

public interface ProductDao {

	
	public String deleteProduct(Product product);

	public String updateProduct(Long id, Product product);

	public String addProduct(Product product);

	public Product getProductById(Long id);

	public List<Product> getAllProduct();
	
	
}
