package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.CoffeeType;

import java.util.List;

public interface CoffeeTypeDao {

    void save(CoffeeType coffeeType);

    void update(CoffeeType coffeeType);

    void delete(CoffeeType coffeeType);

    List<CoffeeType> getAllAvailableCoffeeTypes();

}
