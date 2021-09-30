package com.rms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.AddressDao;
import com.rms.dao.CustomerDao;
import com.rms.dao.OrderDao;
import com.rms.dto.OrderDto;
import com.rms.entity.Address;
import com.rms.entity.Customer;
import com.rms.entity.Order;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.OrderService;
import com.rms.util.OrderUtil;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AddressDao addressDao;
	


	@Override
	public String addOrder(OrderDto orderDto) {

		try{
			String result=null;
			Order order = OrderUtil.toEntity(orderDto);
			Customer customer = customerDao.getCustomerById(orderDto.getCustomer().getId());
			if(customer!=null) {
				order.setCustomer(customer);
				Address address = addressDao.getAddressById(orderDto.getAddress().getId());
				if(address!=null) {
					order.setAddress(address);
					order.setStatus("Pending");
					order.setTotalPrice(0.0);
					boolean flag=orderDao.addOrder(order);
					if(flag) {
						result="Order Creation is Successful";
					}
					return result;
				}else {
					throw new BusinessLogicException("No records Found for Address");
				}
			}
			else {
				throw new BusinessLogicException("No records Found for Customer");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
		
		
	}


	@Override
	public Long getOrderId(Long customerId) {
		try {
			Order order=orderDao.getOrderId(customerId);
			OrderDto orderDto=OrderUtil.toDto(order);
			
			if(orderDto!=null) {
				return orderDto.getId();
			}
			else {
				throw new BusinessLogicException("No records Found for Product");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());		
		
	}
	}


	@Override
	public String updateTotalPrice(Double price, Long orderId) {
		try{
		String result=null;
		boolean flag=orderDao.updateTotalPrice(price, orderId);
		if(flag) {
			result="Order Updation is Successful";
		}
		return result;
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}


	@Override
	public OrderDto getOrderById(Long id) {

		try {
			Order order=orderDao.getOrderById(id);
			if(order!=null) {
				return OrderUtil.toDto(order);
			}
			else {
				throw new BusinessLogicException("No records Found for Orders");
			}
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
		
	}


	@Override
	public String updateOrder(Long orderId, OrderDto orderDto) {
		try{
		String result=null;
		Order order=OrderUtil.toEntity(orderDto);
		boolean flag=orderDao.updateOrder(orderId, order);
		if(flag) {
			result="Order Updation is Successful";
		}
		return result;
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
	
	
	
	
}
