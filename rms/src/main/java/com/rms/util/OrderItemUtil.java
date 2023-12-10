package com.rms.util;

import com.rms.dto.OrderItemDto;
import com.rms.entity.OrderItem;

public class OrderItemUtil {
	
	private OrderItemUtil() {}

	public static OrderItem toEntity(OrderItemDto orderItemDto) {
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(orderItemDto.getProduct());
		orderItem.setQuantity(orderItemDto.getQuantity());
		orderItem.setPrice(orderItemDto.getPrice());
		orderItem.setOrder(orderItemDto.getOrder());
		return orderItem;
	}

	public static OrderItemDto toDto(OrderItem orderItem) {
		OrderItemDto orderItemDto = new OrderItemDto();
		orderItemDto.setId(orderItem.getId());
		orderItemDto.setCreatedOn(orderItem.getCreatedOn());
		orderItemDto.setUpdatedOn(orderItem.getUpdatedOn());
		orderItemDto.setProduct(orderItem.getProduct());
		orderItemDto.setQuantity(orderItem.getQuantity());
		orderItemDto.setPrice(orderItem.getPrice());
		orderItemDto.setOrder(orderItem.getOrder());
		return orderItemDto;
	}

}
