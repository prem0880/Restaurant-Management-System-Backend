package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.AddressDao;
import com.rms.dao.CustomerDao;
import com.rms.dao.OrderDao;
import com.rms.dto.OrderDto;
import com.rms.entity.Address;
import com.rms.entity.Customer;
import com.rms.entity.Order;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.IdNotFoundException;
import com.rms.exception.NoRecordFoundException;
import com.rms.service.OrderService;
import com.rms.util.OrderApprovedMailUtil;
import com.rms.util.OrderUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Override
	public String addOrder(OrderDto orderDto) {
		logger.info("Entering addOrder method");

		try {
			String result = null;
			Order order = OrderUtil.toEntity(orderDto);
			Customer customer = customerDao.getCustomerById(orderDto.getCustomer().getId());
			if (customer != null) {
				order.setCustomer(customer);
				Address address = addressDao.getAddressById(orderDto.getAddress().getId());
				if (address != null) {
					order.setAddress(address);
					order.setStatus("Pending");
					order.setTotalPrice(0.0);
					boolean flag = orderDao.addOrder(order);
					if (flag) {
						result =ApplicationConstants.ORDER_SAVE_SUCCESS;
					}
					return result;
				} else {
					throw new BusinessLogicException(ApplicationConstants.ADDRESS_NOT_FOUND);
				}
			} else {
				throw new BusinessLogicException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public Long getOrderId(Long customerId) {
		logger.info("Entering getOrderId method");

		try {
			if(customerDao.getCustomerById(customerId)!=null) {
			Order order = orderDao.getOrderId(customerId);
			OrderDto orderDto = OrderUtil.toDto(order);
			return orderDto.getId();
			}
			else {
				throw new IdNotFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());

		}
	}

	@Override
	public String updateTotalPrice(Double price, Long orderId) {
		logger.info("Entering updateTotalPrice method");

		try {
			Order order=orderDao.getOrderById(orderId);
			if(order!=null) {
			String result = null;
			boolean flag = orderDao.updateTotalPrice(price, orderId);
			if (flag) {
				result = ApplicationConstants.ORDER_UPDATE_SUCCESS;
			}
			return result;
			}
			else {
					throw new IdNotFoundException(ApplicationConstants.ORDER_NOT_FOUND);
				
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public OrderDto getOrderById(Long id) {
		logger.info("Entering getOrderById method");
		try {
			Order order = orderDao.getOrderById(id);
			if (order != null) {
				return OrderUtil.toDto(order);
			} else {
				throw new IdNotFoundException(ApplicationConstants.ORDER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public String updateOrder(Long orderId, OrderDto orderDto) {
		logger.info("Entering updateOrder method");

		try {
			if(orderDao.getOrderById(orderId)!=null) {
			String result = null;
			Order order = OrderUtil.toEntity(orderDto);
			boolean flag = orderDao.updateOrder(orderId, order);
			if (flag) {
				result = ApplicationConstants.ORDER_UPDATE_SUCCESS;
			}
			return result;
			}else {
				throw new IdNotFoundException(ApplicationConstants.ORDER_NOT_FOUND);
			}
			
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<OrderDto> getOrderByCustomerId(Long customerId) {
		logger.info("Entering getOrderByCustomerId method");

		try {
			if(customerDao.getCustomerById(customerId)!=null) {
			List<Order> orderEntity = orderDao.getOrderByCustomerId(customerId);
			List<OrderDto> orderDto = new ArrayList<>();
			orderEntity.stream().forEach(entity -> orderDto.add(OrderUtil.toDto(entity)));
			return orderDto;
			}
			 else {
				throw new IdNotFoundException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<OrderDto> getAllOrder() {
		logger.info("Entering getAllOrder method");
		try {
			List<Order> orderEntity = orderDao.getAllOrder();
			if (CollectionUtils.isEmpty(orderEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.ORDER_NOT_FOUND);
			}
			else {
				List<OrderDto> orderDto = new ArrayList<>();
				orderEntity.stream().forEach(entity -> orderDto.add(OrderUtil.toDto(entity)));
				return orderDto;
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}
	
	@Override
	public List<OrderDto> getAllSuccessOrder() {
		logger.info("Entering getAllSuccessOrder method");
		try {
			List<Order> orderEntity = orderDao.getAllSuccessOrder();
			if (CollectionUtils.isEmpty(orderEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.ORDER_NOT_FOUND);
			}
			else {
				List<OrderDto> orderDto = new ArrayList<>();
				orderEntity.stream().forEach(entity -> orderDto.add(OrderUtil.toDto(entity)));
				return orderDto;
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateOrderStatus(Long orderId, String status) {
		logger.info("Entering updateOrderStatus method");
		try {
			if(orderDao.getOrderById(orderId)!=null) {
			String result = null;
			Order order= orderDao.updateOrderStatus(orderId, status);
			if (order!=null) {
				result = ApplicationConstants.ORDER_UPDATE_STATUS+" "+status;
				OrderApprovedMailUtil.orderConfirmation(javaMailSender, order);
			}
			return result;
			}
			else {
				throw new IdNotFoundException(ApplicationConstants.ORDER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
