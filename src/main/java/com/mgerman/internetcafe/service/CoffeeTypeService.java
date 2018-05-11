package com.mgerman.internetcafe.service;

import com.mgerman.internetcafe.dao.CoffeeTypeDao;
import com.mgerman.internetcafe.dao.CollectionEntityDao;
import com.mgerman.internetcafe.dao.CommonEntityDao;
import com.mgerman.internetcafe.domain.CoffeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoffeeTypeService {

    @Autowired
    CoffeeTypeDao coffeeTypeDao;

    public void save(CoffeeType coffeeType) {
        coffeeTypeDao.save(coffeeType);
    }

    public void update(CoffeeType coffeeType) {
        coffeeTypeDao.update(coffeeType);
    }

    public void delete(CoffeeType coffeeType) {
        coffeeTypeDao.delete(coffeeType);
    }

    public List<CoffeeType> getAllAvailableCoffeeTypes() {
        return coffeeTypeDao.getAll();
    }
}
