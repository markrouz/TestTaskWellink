package com.mgerman.internetcafe.service;

import com.mgerman.internetcafe.dao.OrderPositionDao;
import com.mgerman.internetcafe.domain.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderPositionService {

    @Autowired
    OrderPositionDao orderPositionDao;

    public void save(OrderPosition orderPosition) {
        orderPositionDao.save(orderPosition);
    }

    public void update(OrderPosition orderPosition) {
        orderPositionDao.update(orderPosition);
    }

    public void delete(OrderPosition orderPosition) {
        orderPositionDao.delete(orderPosition);
    }

}
