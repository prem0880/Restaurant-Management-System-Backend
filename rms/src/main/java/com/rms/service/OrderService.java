package com.rms.service;

import java.util.List;

import com.rms.dto.OrderDto;

public interface OrderService {
	String addOrder(OrderDto orderDto);

	Long getOrderId(Long customerId);

	OrderDto getOrderById(Long id);

	String updateTotalPrice(Double price, Long orderId);

	String updateOrder(Long orderId, OrderDto orderDto);

	List<OrderDto> getOrderByCustomerId(Long customerId);

	List<OrderDto> getAllOrder();
	
	String updateOrderStatus(Long orderId,String status);
}
