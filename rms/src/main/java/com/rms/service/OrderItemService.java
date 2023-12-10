package com.rms.service;

import java.util.List;

import com.rms.dto.OrderItemDto;

public interface OrderItemService {
	
	/**
	 * 
	 * @param orderItem DTO object as input
	 * @return  success string message
	 */
	String addItems(OrderItemDto orderItemDto);

	/**
	 * 
	 * @param orderItem id as input
	 * @return  List of OrderItem DTO objects
	 */
	List<OrderItemDto> getOrderedItems(Long orderId);

}
