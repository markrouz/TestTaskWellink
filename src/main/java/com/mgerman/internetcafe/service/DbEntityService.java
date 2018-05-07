package com.mgerman.internetcafe.service;

import com.mgerman.internetcafe.dao.EntityDao;
import com.mgerman.internetcafe.domain.DbEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
// todo здесь типа бизнес логика ?
public class DbEntityService {

    @Autowired
    private EntityDao entityDao;

    public void save(DbEntity dbEntity) {
        entityDao.save(dbEntity);
    }

    public void update(DbEntity dbEntity) {
        entityDao.update(dbEntity);
    }

    public void delete(DbEntity dbEntity) {
        entityDao.delete(dbEntity);
    }

    public List<DbEntity> getAll(String entityName) {
        return entityDao.getAll(entityName);
    }

}
