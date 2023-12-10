package com.rms.dao;

import java.util.List;

import com.rms.entity.OrderItem;

public interface OrderItemDao {

	/**
	 * 
	 * @param orderItem id as input
	 * @return Sum Of Order Item Prices
	 */
	Double getSumByOrderId(Long orderId);

	/**
	 * 
	 * @param orderItem id as input
	 * @return  List of OrderItem Entity objects
	 */
	List<OrderItem> getOrderedItems(Long orderId);

	/**
	 * 
	 * @param orderItem Entity object
	 * @return  order Item identifier
	 */
	Long saveOrderItem(OrderItem orderItem);
	
	/**
	 * 
	 * @param productId as input
	 * @param orderId as order
	 * @return order Item Entity Object
	 */
	OrderItem checkOrderedItems(Long productId,Long orderId);
	
	/**
	 * 
	 * @param orderItem Entity Object
	 * @return boolean values
	 */
	boolean updateOrderItems(OrderItem orderItem);

}