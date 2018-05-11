package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.OrderPosition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderPositionDaoHibernateImpl extends AbstractHibernateDao<OrderPosition> implements OrderPositionDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderPosition> getAll() {
        return getCurrentSession().createQuery("from OrderPosition ").list();
    }
}
