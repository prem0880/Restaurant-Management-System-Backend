package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.OrderDao;
import com.rms.entity.Order;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);


	@Override
	public boolean addOrder(Order order) {
		logger.info("Entering addOrder method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			order.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(order);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.ORDER_SAVE_ERROR);
		}

	}

	@Override
	public Order getOrderId(Long customerId) {
		logger.info("Entering getOrderId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session
					.createQuery("FROM Order o WHERE o.customer.id=:customerId AND o.status='Pending'", Order.class);
			query.setParameter("customerId", customerId);
			return query.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}

	}

	@Override
	public boolean updateTotalPrice(Double price, Long orderId) {
		logger.info("Entering updateTotalPrice method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Order updateOrder = null;
			updateOrder = session.load(Order.class, orderId);
			updateOrder.setUpdatedOn(TimeStampUtil.getTimeStamp());
			updateOrder.setId(orderId);
			updateOrder.setTotalPrice(price);
			Object value = session.merge(updateOrder);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.ORDER_NOT_FOUND+ApplicationConstants.ORDER_UPDATE_ERROR);
		}

	}

	@Override
	public Order getOrderById(Long id) {
		logger.info("Entering getOrderById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Order order = null;
			order = session.get(Order.class, id);
			return order;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR+e.getMessage());
		}

	}

	@Override
	public boolean updateOrder(Long orderId, Order order) {
		logger.info("Entering updateOrder method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Order orderUpdated = null;
			orderUpdated = session.load(Order.class, orderId);
			order.setUpdatedOn(TimeStampUtil.getTimeStamp());
		    order.setId(orderId);
			order.setCreatedOn(orderUpdated.getCreatedOn());
			order.setDate(TimeStampUtil.getTimeStamp());
			Object value = session.merge(order);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR+ApplicationConstants.ORDER_UPDATE_ERROR);
		}

	}

	@Override
	public List<Order> getOrderByCustomerId(Long customerId) {
		logger.info("Entering getOrderByCustomerId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session
					.createQuery("FROM Order o WHERE o.customer.id=:customerId AND o.status!='pending'", Order.class);
			query.setParameter("customerId", customerId);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public List<Order> getAllOrder() {
		logger.info("Entering getAllOrder method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session.createQuery("FROM Order ", Order.class);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}
	
	@Override
	public List<Order> getAllSuccessOrder() {
		logger.info("Entering getAllSuccessOrder method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session.createQuery("FROM Order o where o.status='Success'", Order.class);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public Order updateOrderStatus(Long orderId, String status) {
		logger.info("Entering updateOrderStatus method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Order orderUpdated = null;
			orderUpdated = session.load(Order.class, orderId);
			orderUpdated.setUpdatedOn(TimeStampUtil.getTimeStamp());
			orderUpdated.setId(orderId);
			orderUpdated.setStatus(status);
			Order order=(Order) session.merge(orderUpdated);
			session.flush();
			return order;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR+ApplicationConstants.ORDER_UPDATE_ERROR);
		}
	}

}
