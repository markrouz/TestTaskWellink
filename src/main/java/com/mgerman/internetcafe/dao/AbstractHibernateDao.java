package com.mgerman.internetcafe.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class AbstractHibernateDao<T> implements CollectionEntityDao<T> {


    private Class<T> clazz;

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

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }
}
