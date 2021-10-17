package com.rms.service;

import java.util.List;

import com.rms.dto.OrderDto;

public interface OrderService {
	
	/**
	 * 
	 * @param order DTO object as input
	 * @return  success string message
	 */
	String addOrder(OrderDto orderDto);

	
	/**
	 * 
	 * @param customer id as input
	 * @return  order identifier
	 */
	Long getOrderId(Long customerId);

	/**
	 * 
	 * @param order id as input
	 * @return  order DTO object
	 */
	OrderDto getOrderById(Long id);

	/**
	 * 
	 * @param product price as input
	 * @param order id as input
	 * @return	 success string message
	 */
	String updateTotalPrice(Double price, Long orderId);

	/**
	 * 
	 * @param order id as input
	 * @param order DTO object
	 * @return	 success string message
	 */
	String updateOrder(Long orderId, OrderDto orderDto);

	/**
	 * 
	 * @param customer id as input
	 * @return  List of order DTO objects
	 */
	List<OrderDto> getOrderByCustomerId(Long customerId);

	/**
	 * 
	 * @return  List of order DTO objects
	 */
	List<OrderDto> getAllOrder();
	
	/**
	 * 
	 * @return  List of order DTO objects
	 */
	List<OrderDto> getAllSuccessOrder();
	
	/**
	 * 
	 * @param order id as input
	 * @param order status string
	 * @return	success string message
	 */
	String updateOrderStatus(Long orderId,String status);
}
