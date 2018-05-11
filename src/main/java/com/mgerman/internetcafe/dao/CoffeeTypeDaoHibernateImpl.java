package com.mgerman.internetcafe.dao;


import com.mgerman.internetcafe.domain.CoffeeType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CoffeeTypeDaoHibernateImpl implements CoffeeTypeDao {

    private SessionFactory sessionFactory;

    public CoffeeTypeDaoHibernateImpl() {

    }

    @Autowired
    public CoffeeTypeDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(CoffeeType coffeeType) {
        getCurrentSession().saveOrUpdate(coffeeType);
    }

    @Override
    public void update(CoffeeType coffeeType) {
        getCurrentSession().update(coffeeType);
    }

    @Override
    public void delete(CoffeeType coffeeType) {
        getCurrentSession().delete(coffeeType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CoffeeType> getAll() {
       return getCurrentSession().createQuery("from CoffeeType as ct where ct.disabled = false").list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
