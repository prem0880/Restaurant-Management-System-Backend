package com.rms.dao;

import com.rms.entity.Order;

public interface OrderDao {

	boolean addOrder(Order order);
	Order getOrderId(Long customerId);
	Order getOrderById(Long id);
	boolean updateTotalPrice(Double price, Long orderId);
	boolean updateOrder(Long orderId, Order order);
}
