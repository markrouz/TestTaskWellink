import com.mgerman.internetcafe.dao.EntityDaoHibernateImpl;
import com.mgerman.internetcafe.domain.CoffeeEntity;
import com.mgerman.internetcafe.domain.DbEntity;
import com.mgerman.internetcafe.domain.OrderEntity;
import com.mgerman.internetcafe.domain.OrderPositionEntity;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//todo тестовая база?
public class CrudTest {

    @Test
    public void test() {
        CoffeeEntity simpleBlackCoffee = new CoffeeEntity();
        simpleBlackCoffee.setName("BlackCoffee");
        simpleBlackCoffee.setPrice(100.0);
        simpleBlackCoffee.setDisabled(false);

        CoffeeEntity americanoCoffee = new CoffeeEntity();
        americanoCoffee.setName("americano");
        americanoCoffee.setPrice(150.0);
        americanoCoffee.setDisabled(false);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        EntityDaoHibernateImpl entityDaoHibernate = (EntityDaoHibernateImpl) applicationContext.getBean("entityDaoHibernateImpl");
        //todo попачечно сделать?
        entityDaoHibernate.save(simpleBlackCoffee);
        entityDaoHibernate.save(americanoCoffee);

        //todo и все таки это такое себе (но зато типа без дублирования)
        List<DbEntity> coffeeEntities = entityDaoHibernate.getAll("CoffeeEntity");
        assertNotNull(coffeeEntities);
        assertEquals(2, coffeeEntities.size());
        assertEquals(simpleBlackCoffee.getName(), ((CoffeeEntity) coffeeEntities.get(0)).getName());
        assertEquals(americanoCoffee.getName(), ((CoffeeEntity) coffeeEntities.get(1)).getName());

        OrderPositionEntity orderPositionEntity = new OrderPositionEntity();
        orderPositionEntity.setCoffee(americanoCoffee);
        orderPositionEntity.setNumberOfCups(2);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerName("Mark");
        orderEntity.setAddress("Demakova");
        orderEntity.setDate(new Date());
        orderEntity.setPrice(1000.0);

        List<OrderPositionEntity> orderPositions = new ArrayList<OrderPositionEntity>();
        orderPositions.add(orderPositionEntity);
        orderEntity.setOrderPositions(orderPositions);
        entityDaoHibernate.save(orderEntity);

        List<DbEntity> orderEntitiesFromDb = entityDaoHibernate.getAll("OrderEntity");
        assertEquals(orderEntity.getCustomerName(), ((OrderEntity)orderEntitiesFromDb.get(0)).getCustomerName());

        List<DbEntity> orderPositionsFromDb = entityDaoHibernate.getAll("OrderPositionEntity");
        assertEquals(orderEntity.getOrderPositions().get(0).getCoffee().getName(), ((OrderPositionEntity)orderPositionsFromDb.get(0)).getCoffee().getName());

        entityDaoHibernate.delete(orderEntity);

        entityDaoHibernate.delete(simpleBlackCoffee);
        entityDaoHibernate.delete(americanoCoffee);
        coffeeEntities = entityDaoHibernate.getAll("CoffeeEntity");
        assertEquals(0, coffeeEntities.size());

    }


}
