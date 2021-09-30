package com.rms.service;

import java.util.List;

import com.rms.dto.OrderItemDto;

public interface OrderItemService {
	String addItems(OrderItemDto orderItemDto);
	List<OrderItemDto> getOrderedItems(Long orderId);
	
}
