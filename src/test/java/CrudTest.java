import com.mgerman.internetcafe.dao.EntityDaoHibernateImpl;
import com.mgerman.internetcafe.domain.CoffeeEntity;
import com.mgerman.internetcafe.domain.DbEntity;
import com.mgerman.internetcafe.domain.OrderEntity;
import com.mgerman.internetcafe.domain.OrderPositionEntity;
import com.mgerman.internetcafe.service.DbEntityService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CrudTest {

    @Test
    public void test() {
        CoffeeEntity simpleBlackCoffee = new CoffeeEntity();
        simpleBlackCoffee.setName("BlackCoffee");
        simpleBlackCoffee.setPrice(100.0);
        simpleBlackCoffee.setDisabled(false);

        CoffeeEntity americanoCoffee = new CoffeeEntity();
        americanoCoffee.setName("BlackCoffeeWithMilk");
        americanoCoffee.setPrice(150.0);
        americanoCoffee.setDisabled(false);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //EntityDaoHibernateImpl entityDaoHibernate = (EntityDaoHibernateImpl) applicationContext.getBean("entityDaoHibernateImpl");
        DbEntityService dbEntityService = (DbEntityService) applicationContext.getBean("dbEntityService");

        dbEntityService.save(simpleBlackCoffee);
        dbEntityService.save(americanoCoffee);

        List<DbEntity> coffeeEntities = dbEntityService.getAll("CoffeeEntity");
        assertNotNull(coffeeEntities);
        assertEquals(4, coffeeEntities.size());
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
        dbEntityService.save(orderEntity);

        List<DbEntity> orderEntitiesFromDb = dbEntityService.getAll("OrderEntity");
        assertEquals(orderEntity.getCustomerName(), ((OrderEntity)orderEntitiesFromDb.get(0)).getCustomerName());

        List<DbEntity> orderPositionsFromDb = dbEntityService.getAll("OrderPositionEntity");
        assertEquals(orderEntity.getOrderPositions().get(0).getCoffee().getName(), ((OrderPositionEntity)orderPositionsFromDb.get(0)).getCoffee().getName());

        dbEntityService.delete(orderEntity);

        dbEntityService.delete(simpleBlackCoffee);
        dbEntityService.delete(americanoCoffee);
        coffeeEntities = dbEntityService.getAll("CoffeeEntity");
        assertEquals(0, coffeeEntities.size());

    }


}
