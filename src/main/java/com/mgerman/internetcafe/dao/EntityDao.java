package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.DbEntity;

import java.util.List;

public interface EntityDao {
    //todo getById
    void save(DbEntity dbEntity);
    void update(DbEntity dbEntity);
    void delete(DbEntity dbEntity);
    List<DbEntity> getAll(String entityName);
}
