package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoHibernateImpl implements OrderDao {

    private SessionFactory sessionFactory;

    public OrderDaoHibernateImpl() {

    }

    @Autowired
    public OrderDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
    }

    @Override
    public void delete(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(order);
    }

}
