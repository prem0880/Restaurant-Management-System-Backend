package com.rms.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.OrderItemDto;
import com.rms.response.HttpResponseStatus;
import com.rms.service.OrderItemService;

@RestController
@RequestMapping("/order-item")
@CrossOrigin("*")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;
	
	private static final Logger logger = LogManager.getLogger(OrderItemController.class);

	/**
	 *@param This method takes order Item DTO object as input
	 *@return This method returns success message if order items area added successfully
     */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addItems(@Valid @RequestBody OrderItemDto orderItemDto) {
		logger.info("Entering addItems method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.CREATED.value(), orderItemService.addItems(orderItemDto)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes order id as input
	 *@return This method returns order Item objects for given id currently in the database with the corresponding id
	 */
	@GetMapping("/{orderId}")
	public ResponseEntity<HttpResponseStatus> getOrderedItems(@PathVariable("orderId") Long orderId) {
		logger.info("Entering getOrderedItems method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.ORDERITEM_FETCH_SUCCESS,
					orderItemService.getOrderedItems(orderId)), HttpStatus.OK);
		
	}
	

}
