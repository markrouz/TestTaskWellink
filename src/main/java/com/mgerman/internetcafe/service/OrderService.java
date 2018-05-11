package com.mgerman.internetcafe.service;

import com.mgerman.internetcafe.dao.CoffeeTypeDao;
import com.mgerman.internetcafe.dao.OrderDao;
import com.mgerman.internetcafe.domain.CoffeeType;
import com.mgerman.internetcafe.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public void save(Order order) {
        orderDao.save(order);
    }

    public void update(Order order) {
        orderDao.update(order);
    }

    public void delete(Order order) {
        orderDao.delete(order);
    }

}
