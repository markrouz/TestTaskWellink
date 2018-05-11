package com.mgerman.internetcafe.dao;

import com.mgerman.internetcafe.domain.CoffeeType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class AbstractHibernateDao<T> implements CollectionEntityDao<T> {

    @Autowired
    private SessionFactory sessionFactory;


    public void save(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }


    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }



    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
