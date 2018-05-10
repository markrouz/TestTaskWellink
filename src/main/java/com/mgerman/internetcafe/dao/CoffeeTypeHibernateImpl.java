package com.mgerman.internetcafe.dao;


import com.mgerman.internetcafe.domain.CoffeeType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class CoffeeTypeHibernateImpl implements CoffeeTypeDao {

    private SessionFactory sessionFactory;

    public CoffeeTypeHibernateImpl() {

    }

    @Autowired
    public CoffeeTypeHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(CoffeeType coffeeType) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(coffeeType);
    }

    @Override
    public void update(CoffeeType coffeeType) {
        Session session = sessionFactory.getCurrentSession();
        session.update(coffeeType);
    }

    @Override
    public void delete(CoffeeType coffeeType) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(coffeeType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CoffeeType> getAllAvailableCoffeeTypes() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from CoffeeType as ct where ct.disabled = false").list();
    }


}
