package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.OrderPosition;
import org.springframework.stereotype.Repository;


@Repository
public class OrderPositionDaoHibernateImpl extends AbstractHibernateDao<OrderPosition> implements OrderPositionDao {

    OrderPositionDaoHibernateImpl() {
        setClazz(OrderPosition.class);
    }
}
