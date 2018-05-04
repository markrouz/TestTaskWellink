package com.mgerman;

import com.mgerman.domain.CoffeeEntity;
import com.mgerman.domain.OrderEntity;
import com.mgerman.domain.OrderPositionEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestApp {

    private static final Logger logger = Logger.getLogger(TestApp.class);

    public static void main(String[] args)
    {
        // read configuration and build session factory
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

        SessionFactory sessionFactory = null;

        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            logger.error("cannot create sessionFactory", e);
            System.exit(1);
        }

        // create session, open transaction and save test entity to db
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            //todo понять почему айдишники все равно инкрементируются, а не сбрасываются

            CoffeeEntity testEntity = new CoffeeEntity();
            testEntity.setName("black coffee");
            testEntity.setDisabled(false);
            testEntity.setPrice(new BigInteger("1000"));//todo похоже что BigInteger это такое себе

            CoffeeEntity coffeeEntity = new CoffeeEntity();
            coffeeEntity.setName("dadad");
            coffeeEntity.setDisabled(false);
            coffeeEntity.setPrice(new BigInteger("1488"));

            session.persist(testEntity);
            session.persist(coffeeEntity);

            OrderPositionEntity orderPositionEntity = new OrderPositionEntity();
            orderPositionEntity.setCoffee(testEntity);
            orderPositionEntity.setNumberOfCups(2);

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setCustomerName("Mark");
            orderEntity.setAddress("Demakova");
            orderEntity.setDate(new Date());
            orderEntity.setPrice(new BigInteger("1000"));

            List<OrderPositionEntity> orderPositions = new ArrayList<OrderPositionEntity>();
            orderPositions.add(orderPositionEntity);
            orderEntity.setOrderPositions(orderPositions);
            session.persist(orderEntity);
            tx.commit();

        }
        catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
        }
        finally {
            session.close();
        }

        // clean up
        sessionFactory.close();
    }

}
