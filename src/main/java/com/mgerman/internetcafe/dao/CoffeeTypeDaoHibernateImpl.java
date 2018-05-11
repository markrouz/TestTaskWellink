package com.mgerman.internetcafe.dao;


import com.mgerman.internetcafe.domain.CoffeeType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CoffeeTypeDaoHibernateImpl extends AbstractHibernateDao<CoffeeType> implements CoffeeTypeDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<CoffeeType> getAll() {
       return getCurrentSession().createQuery("from CoffeeType as ct where ct.disabled = false").list();
    }

}
