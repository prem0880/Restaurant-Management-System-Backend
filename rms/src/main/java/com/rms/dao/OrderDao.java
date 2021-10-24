package com.rms.dao;

import java.util.List;

import com.rms.entity.Order;

public interface OrderDao {

	/**
	 * 
	 * @param order Entity object as input
	 * @return boolean value
	 */
	boolean addOrder(Order order);

	/**
	 * 
	 * @param customer id as input
	 * @return  order Entity object
	 */
	Order getOrderId(Long customerId);

	/**
	 * 
	 * @param order id as input
	 * @return  order Entity object
	 */
	Order getOrderById(Long id);

	/**
	 * 
	 * @param product price as input
	 * @param order id as input
	 * @return boolean value
	 */
	boolean updateTotalPrice(Double price, Long orderId);

	/**
	 * 
	 * @param order id as input
	 * @param order Entity object
	 * @return boolean value
	 */
	boolean updateOrder(Long orderId, Order order);

	/**
	 * 
	 * @param customer id as input
	 * @return  List of order Entity objects
	 */
	List<Order> getOrderByCustomerId(Long customerId);

	/**
	 * 
	 * @return  List of order Entity objects
	 */
	List<Order> getAllOrder();
	
	/**
	 * 
	 * @return  List of order Entity objects 
	 */
	List<Order> getAllSuccessOrder();
	
	/**
	 * 
	 * @param order id as input
	 * @param order status string
	 * @return Order Entity Object
	 */
	Order updateOrderStatus(Long orderId,String status);
}
