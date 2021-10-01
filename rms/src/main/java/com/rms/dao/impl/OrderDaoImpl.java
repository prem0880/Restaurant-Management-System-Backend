package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.OrderDao;
import com.rms.entity.Order;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	static final String DB_FETCH_ERROR = "Error in Fetching Data from Database";
	static final String ID_NOT_FOUND = "Order not found with id ";
	static final String COULDNT_UPDATE = "Couldn't update Order...";

	@Override
	public boolean addOrder(Order order) {
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
			throw new DataBaseException("Error in Creation");
		}

	}

	@Override
	public Order getOrderId(Long customerId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session
					.createQuery("FROM Order o WHERE o.customer.id=:customerId AND o.status='Pending'", Order.class);
			query.setParameter("customerId", customerId);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}

	}

	@Override
	public boolean updateTotalPrice(Double price, Long orderId) {
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
			throw new DataBaseException(ID_NOT_FOUND + COULDNT_UPDATE);
		}

	}

	@Override
	public Order getOrderById(Long id) {

		try {
			Session session = sessionFactory.getCurrentSession();
			Order order = null;
			order = session.get(Order.class, id);
			return order;
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR + ID_NOT_FOUND + id);
		}

	}

	@Override
	public boolean updateOrder(Long orderId, Order order) {
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Order orderUpdated = null;
			orderUpdated = session.load(Order.class, orderId);
			order.setUpdatedOn(TimeStampUtil.getTimeStamp());
			order.setId(orderId);
			order.setCreatedOn(orderUpdated.getCreatedOn());
			Object value = session.merge(order);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			throw new DataBaseException(ID_NOT_FOUND + COULDNT_UPDATE);
		}

	}

	@Override
	public List<Order> getOrderByCustomerId(Long customerId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session
					.createQuery("FROM Order o WHERE o.customer.id=:customerId AND o.status='SUCCESS'", Order.class);
			query.setParameter("customerId", customerId);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}
	}

	@Override
	public List<Order> getAllOrder() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session.createQuery("FROM Order ", Order.class);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}
	}

}
