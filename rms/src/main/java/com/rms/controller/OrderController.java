package com.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.dto.OrderDto;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.response.HttpResponse;
import com.rms.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/add")
	public ResponseEntity<HttpResponse> addOrder(@RequestBody OrderDto orderDto){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), orderService.addOrder(orderDto)),  HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/orderId/{customerId}")
	public ResponseEntity<HttpResponse> getOrderId(@PathVariable Long customerId){
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), "Customer Id retrieval success",orderService.getOrderId(customerId)),  HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/id/{orderId}")
	public  ResponseEntity<HttpResponse> getOrderById(@PathVariable("orderId") Long orderId) {
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), "Order Id retrieval success",orderService.getOrderById(orderId)),  HttpStatus.OK);
		}catch(BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update-order/{orderId}")
	public ResponseEntity<HttpResponse> updateOrder(@PathVariable("orderId") Long orderId,@RequestBody OrderDto orderDto) {
		try{
			return new ResponseEntity<>(new HttpResponse(HttpStatus.OK.value(), orderService.updateOrder(orderId, orderDto)),  HttpStatus.OK);
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
