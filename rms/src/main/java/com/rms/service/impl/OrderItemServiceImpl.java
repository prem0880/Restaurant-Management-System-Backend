package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.OrderDao;
import com.rms.dao.OrderItemDao;
import com.rms.dao.ProductDao;
import com.rms.dto.OrderItemDto;
import com.rms.entity.Order;
import com.rms.entity.OrderItem;
import com.rms.entity.Product;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.OrderItemService;
import com.rms.util.OrderItemUtil;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	private static final Logger logger = LogManager.getLogger(OrderItemServiceImpl.class);


	@Override
	public String addItems(OrderItemDto orderItemDto) {
		logger.trace("Entering addItems method");
		try {
			String result = null;
			OrderItem orderItem = OrderItemUtil.toEntity(orderItemDto);
			Product product = productDao.getProductById(orderItem.getProduct().getId());
			if (product != null) {
				orderItem.setProduct(product);
				Order order = orderDao.getOrderById(orderItem.getOrder().getId());
				if (order != null) {
					orderItem.setOrder(order);
					orderItem.setPrice((product.getPrice() * orderItem.getQuantity()) + product.getTax());
					orderItemDao.saveOrderItem(orderItem);
					Double sum = null;
					sum = orderItemDao.getSumByOrderId(order.getId());
					if (orderServiceImpl.updateTotalPrice(sum, order.getId()) != null) {
						result =ApplicationConstants.ORDERITEM_SAVE_SUCCESS;
					}
					return result;
				} else {
					throw new BusinessLogicException(ApplicationConstants.ORDER_NOT_FOUND);
				}
			} else {
				throw new BusinessLogicException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<OrderItemDto> getOrderedItems(Long orderId) {
		logger.trace("Entering getOrderedItems method");
		try {
			List<OrderItem> orderItemList = orderItemDao.getOrderedItems(orderId);
			if (orderItemList != null) {
				List<OrderItemDto> orderItemDto = new ArrayList<>();
				orderItemList.stream().forEach(entity -> orderItemDto.add(OrderItemUtil.toDto(entity)));
				return orderItemDto;
			} else {
				throw new BusinessLogicException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

}