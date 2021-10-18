package com.rms.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.OrderDto;
import com.rms.response.HttpResponseStatus;
import com.rms.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);

	/**
	 *@param This method takes order DTO object as input
	 *@return This method returns success message if order is created successfully
     */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addOrder( @RequestBody OrderDto orderDto) {
		logger.info("Entering addOrder method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), orderService.addOrder(orderDto)),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes customer id as input
	 *@return This method returns order id for given customer id currently in the database with the corresponding id
	 */
	@GetMapping("/order/{customerId}")
	public ResponseEntity<HttpResponseStatus> getOrderId(@PathVariable Long customerId) {
		logger.info("Entering getOrderId method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMERID_FETCH_SUCCESS,
					orderService.getOrderId(customerId)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes customer id as input
	 *@return This method returns order object for given customer id currently in the database with the corresponding id
	 */
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<HttpResponseStatus> getOrderByCustomerId(@PathVariable Long customerId) {
		logger.info("Entering getOrderByCustomerId method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMERID_FETCH_SUCCESS,
					orderService.getOrderByCustomerId(customerId)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes no input
	 *@return This method returns List of order objects along with success message as HttpResponseStatus 
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllOrder() {
		logger.info("Entering getAllOrder method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.ORDER_FETCH_SUCCESS,
					orderService.getAllOrder()), HttpStatus.OK);
		
	}
	
	/**
	 *@param This method takes no input
	 *@return This method returns List of order objects along with success message as HttpResponseStatus 
	 */
	@GetMapping("/success")
	public ResponseEntity<HttpResponseStatus> getAllSuccessOrder() {
		logger.info("Entering getAllSuccessOrder method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.ORDER_FETCH_SUCCESS,
					orderService.getAllSuccessOrder()), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes order id as input
	 *@return This method returns order object for given id currently in the database with the corresponding id
	 */
	@GetMapping("/{orderId}")
	public ResponseEntity<HttpResponseStatus> getOrderById(@PathVariable("orderId") Long orderId) {
		logger.info("Entering getOrderById method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.ORDERID_FETCH_SUCCESS,
					orderService.getOrderById(orderId)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes order object and id as input 
	 *@return This method returns message if order is updated successfully with id
     */
	@PutMapping("/{orderId}")
	public ResponseEntity<HttpResponseStatus> updateOrder(@PathVariable("orderId") Long orderId,
			@RequestBody OrderDto orderDto) {
		logger.info("Entering updateOrder method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), orderService.updateOrder(orderId, orderDto)),
					HttpStatus.OK);
		
	}
	
	/**
	 *@param This method takes order status and id as input 
	 *@return This method returns message if meal is updated successfully with id
     */
	@PutMapping("/{orderId}/{status}")
	public ResponseEntity<HttpResponseStatus> updateOrderStatus(@PathVariable("orderId") Long orderId,@PathVariable("status") String status) {
		logger.info("Entering updateOrderStatus method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), orderService.updateOrderStatus(orderId, status)),
					HttpStatus.OK);
		
	}
	
	
}
