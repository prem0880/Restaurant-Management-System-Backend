package com.rms.dao;

import java.util.List;



import com.rms.entity.OrderItem;

public interface OrderItemDao {
	
	Double getSumByOrderId(Long orderId);
	
	List<OrderItem> getOrderedItems(Long orderId);
	
	Long saveOrderItem(OrderItem orderItem);
	
}