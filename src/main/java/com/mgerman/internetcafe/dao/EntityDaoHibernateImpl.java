package com.mgerman.internetcafe.dao;


import com.mgerman.internetcafe.domain.DbEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityDaoHibernateImpl implements EntityDao {

    private SessionFactory sessionFactory;

    public EntityDaoHibernateImpl() {

    }

    @Autowired
    public EntityDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(DbEntity dbEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(dbEntity);
    }

    @Override
    public void update(DbEntity dbEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(dbEntity);
    }

    @Override
    public void delete(DbEntity dbEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(dbEntity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DbEntity> getAll(String entityName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from " + entityName).list();
    }
}
