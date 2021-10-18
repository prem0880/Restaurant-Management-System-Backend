package com.rms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
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
		logger.info("Entering getSumByOrderId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Double) session.createQuery("SELECT SUM(o.price) FROM OrderItem o WHERE o.order.id=:orderId")
					.setParameter("orderId", orderId).getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public List<OrderItem> getOrderedItems(Long orderId) {
		logger.info("Entering getOrderedItems method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<OrderItem> query = session.createQuery("FROM OrderItem o WHERE o.order.id=:orderId", OrderItem.class);
			query.setParameter("orderId", orderId);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public Long saveOrderItem(OrderItem orderItem) {
		logger.info("Entering saveOrderItem method");
		try {
			Session session = sessionFactory.getCurrentSession();
			orderItem.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(orderItem);
			session.flush();
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.ORDERITEM_SAVE_ERROR);
		}
	}
	
	@Override
	public OrderItem checkOrderedItems(Long productId,Long orderId) {
		logger.info("Entering checkOrderedItems method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<OrderItem> query = session.createQuery("FROM OrderItem o WHERE o.order.id=:orderId AND o.product.id=:productId ", OrderItem.class);
			query.setParameter("productId", productId);
			query.setParameter("orderId", orderId);
			return query.getSingleResult();
			
		}catch(NoResultException e) {
			return null;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}
	
	@Override
	public boolean updateOrderItems(OrderItem orderItem) {
		logger.info("Entering updateOrderItems method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			OrderItem orderItemUpdated = null;
			orderItemUpdated = session.load(OrderItem.class, orderItem.getId());
			orderItemUpdated.setPrice(orderItem.getPrice());
			orderItemUpdated.setQuantity(orderItem.getQuantity());
			orderItemUpdated.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object value = session.merge(orderItemUpdated);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR+ApplicationConstants.ORDERITEM_SAVE_ERROR);
		}

	}

	

}
