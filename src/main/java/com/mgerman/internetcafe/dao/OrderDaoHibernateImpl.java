package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoHibernateImpl extends AbstractHibernateDao<Order> implements OrderDao {


    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getAll() {
        return getCurrentSession().createQuery("from Order").list();
    }
}
