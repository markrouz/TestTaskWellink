package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.Order;


public interface OrderDao {

    void save(Order order);

    void update(Order order);

    void delete(Order order);

}
