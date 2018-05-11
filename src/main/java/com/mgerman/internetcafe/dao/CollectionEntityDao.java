package com.mgerman.internetcafe.dao;

import java.util.List;

public interface CollectionEntityDao<E> extends CommonEntityDao<E>{
    List<E> getAll();
}
