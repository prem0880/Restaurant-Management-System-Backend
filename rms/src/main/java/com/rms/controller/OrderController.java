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
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addOrder(@RequestBody OrderDto orderDto) {
		logger.info("Entering addOrder method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), orderService.addOrder(orderDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/order/{customerId}")
	public ResponseEntity<HttpResponseStatus> getOrderId(@PathVariable Long customerId) {
		logger.info("Entering getOrderId method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMERID_FETCH_SUCCESS,
					orderService.getOrderId(customerId)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<HttpResponseStatus> getOrderByCustomerId(@PathVariable Long customerId) {
		logger.info("Entering getOrderByCustomerId method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMERID_FETCH_SUCCESS,
					orderService.getOrderByCustomerId(customerId)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllOrder() {
		logger.info("Entering getAllOrder method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.CUSTOMERID_FETCH_SUCCESS,
					orderService.getAllOrder()), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<HttpResponseStatus> getOrderById(@PathVariable("orderId") Long orderId) {
		logger.info("Entering getOrderById method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.ORDERID_FETCH_SUCCESS,
					orderService.getOrderById(orderId)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<HttpResponseStatus> updateOrder(@PathVariable("orderId") Long orderId,
			@RequestBody OrderDto orderDto) {
		logger.info("Entering updateOrder method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), orderService.updateOrder(orderId, orderDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
