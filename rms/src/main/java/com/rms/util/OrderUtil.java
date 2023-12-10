package com.rms.util;

import com.rms.dto.OrderDto;
import com.rms.entity.Order;

public class OrderUtil {
	
	private OrderUtil() {}

	public static Order toEntity(OrderDto orderDto) {
		Order order = new Order();
		order.setAddress(orderDto.getAddress());
		order.setCustomer(orderDto.getCustomer());
		order.setModeOfPayment(orderDto.getModeOfPayment());
		order.setStatus(orderDto.getStatus());
		order.setTotalPrice(orderDto.getTotalPrice());
		return order;
	}

	public static OrderDto toDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setAddress(order.getAddress());
		orderDto.setDate(order.getDate());
		orderDto.setCustomer(order.getCustomer());
		orderDto.setModeOfPayment(order.getModeOfPayment());
		orderDto.setOrderItem(order.getOrderItem());
		orderDto.setStatus(order.getStatus());
		orderDto.setTotalPrice(order.getTotalPrice());
		orderDto.setCreatedOn(order.getCreatedOn());
		orderDto.setUpdatedOn(order.getUpdatedOn());
		return orderDto;
	}

}
