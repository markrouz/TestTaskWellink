package com.mgerman.internetcafe.dao;

public interface CommonEntityDao<E> {

    void save(E entity);

    void update(E entity);

    void delete(E entity);

}
