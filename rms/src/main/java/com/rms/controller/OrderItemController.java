package com.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.dto.OrderItemDto;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.OrderItemService;


@RestController
@RequestMapping("/order-item")
@CrossOrigin("http://localhost:4200")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addItems(@RequestBody OrderItemDto orderItemDto)
	{
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),orderItemService.addItems(orderItemDto)),  HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get-items/{orderId}")
	public ResponseEntity<HttpResponse> getOrderedItems(@PathVariable("orderId") Long orderId)
	{
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(),"Order Items Retrieval is Success",orderItemService.getOrderedItems(orderId)),  HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<HttpResponse> businessException (BusinessLogicException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
		

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<HttpResponse> dataBaseException (DataBaseException e) {
		return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	

}
