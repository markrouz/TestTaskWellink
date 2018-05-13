package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoHibernateImpl extends AbstractHibernateDao<Order> implements OrderDao {

    OrderDaoHibernateImpl() {
        setClazz(Order.class);
    }

}
