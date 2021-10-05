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
import com.rms.dao.OrderItemDao;
import com.rms.entity.OrderItem;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class OrderItemDaoImpl implements OrderItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(OrderItemDaoImpl.class);


	@Override
	public Double getSumByOrderId(Long orderId) {
		logger.trace("Entering getSumByOrderId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Double) session.createQuery("SELECT SUM(o.price) FROM OrderItem o WHERE o.order.id=:orderId")
					.setParameter("orderId", orderId).getSingleResult();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public List<OrderItem> getOrderedItems(Long orderId) {
		logger.trace("Entering getOrderedItems method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<OrderItem> query = session.createQuery("FROM OrderItem o WHERE o.order.id=:orderId", OrderItem.class);
			query.setParameter("orderId", orderId);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public Long saveOrderItem(OrderItem orderItem) {
		logger.trace("Entering saveOrderItem method");
		try {
			Session session = sessionFactory.getCurrentSession();
			orderItem.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(orderItem);
			session.flush();
			return value;
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.ORDERITEM_SAVE_ERROR);
		}
	}

}
