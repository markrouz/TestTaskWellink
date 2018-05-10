package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.OrderPosition;

public interface OrderPositionDao {

    void save(OrderPosition orderPosition);

    void update(OrderPosition orderPosition);

    void delete(OrderPosition orderPosition);

 }
