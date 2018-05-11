package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoHibernateImpl implements OrderDao {

    private SessionFactory sessionFactory;

    public OrderDaoHibernateImpl() {

    }

    @Autowired
    public OrderDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private org.hibernate.classic.Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Order order) {
        getCurrentSession().saveOrUpdate(order);
    }

    @Override
    public void update(Order order) {
        getCurrentSession().update(order);
    }

    @Override
    public void delete(Order order) {
        getCurrentSession().delete(order);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getAll() {
        return getCurrentSession().createQuery("from Order").list();
    }
}
