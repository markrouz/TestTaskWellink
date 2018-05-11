package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.OrderPosition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderPositionDaoHibernateImpl implements OrderPositionDao {

    private SessionFactory sessionFactory;

    public OrderPositionDaoHibernateImpl() {

    }

    @Autowired
    public OrderPositionDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(OrderPosition orderPosition) {
        getCurrentSession().saveOrUpdate(orderPosition);
    }

    @Override
    public void update(OrderPosition orderPosition) {
        getCurrentSession().update(orderPosition);
    }

    @Override
    public void delete(OrderPosition orderPosition) {
        getCurrentSession().delete(orderPosition);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderPosition> getAll() {
        return getCurrentSession().createQuery("from OrderPosition ").list();
    }
}
