package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.rms.service.OrderService;
import com.rms.util.OrderUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AddressDao addressDao;
	
	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Override
	public String addOrder(OrderDto orderDto) {
		logger.debug("Entering addOrder method");

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
		logger.debug("Entering getOrderId method");

		try {
			Order order = orderDao.getOrderId(customerId);
			OrderDto orderDto = OrderUtil.toDto(order);

			if (orderDto != null) {
				return orderDto.getId();
			} else {
				throw new BusinessLogicException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());

		}
	}

	@Override
	public String updateTotalPrice(Double price, Long orderId) {
		logger.debug("Entering updateTotalPrice method");

		try {
			String result = null;
			boolean flag = orderDao.updateTotalPrice(price, orderId);
			if (flag) {
				result = ApplicationConstants.ORDER_UPDATE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public OrderDto getOrderById(Long id) {
		logger.debug("Entering getOrderById method");
		try {
			Order order = orderDao.getOrderById(id);
			if (order != null) {
				return OrderUtil.toDto(order);
			} else {
				throw new BusinessLogicException(ApplicationConstants.ORDER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public String updateOrder(Long orderId, OrderDto orderDto) {
		logger.debug("Entering updateOrder method");

		try {
			String result = null;
			Order order = OrderUtil.toEntity(orderDto);
			boolean flag = orderDao.updateOrder(orderId, order);
			if (flag) {
				result = ApplicationConstants.ORDER_UPDATE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<OrderDto> getOrderByCustomerId(Long customerId) {
		logger.debug("Entering getOrderByCustomerId method");

		try {
			List<Order> orderEntity = orderDao.getOrderByCustomerId(customerId);
			if (orderEntity != null) {
				List<OrderDto> orderDto = new ArrayList<>();
				orderEntity.stream().forEach(entity -> orderDto.add(OrderUtil.toDto(entity)));
				return orderDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.CUSTOMER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<OrderDto> getAllOrder() {
		logger.debug("Entering getAllOrder method");
		try {
			List<Order> orderEntity = orderDao.getAllOrder();
			if (orderEntity != null) {
				List<OrderDto> orderDto = new ArrayList<>();
				orderEntity.stream().forEach(entity -> orderDto.add(OrderUtil.toDto(entity)));
				return orderDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.ORDER_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateOrderStatus(Long orderId, String status) {
		logger.debug("Entering updateOrderStatus method");
		try {
			String result = null;
			boolean flag = orderDao.updateOrderStatus(orderId, status);
			if (flag) {
				result = ApplicationConstants.ORDER_UPDATE_STATUS+" "+status;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
